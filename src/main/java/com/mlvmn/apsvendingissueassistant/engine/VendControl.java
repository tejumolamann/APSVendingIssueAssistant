/*
 * The vending controller, it controls the vending operations of the program.
 */
package com.mlvmn.apsvendingissueassistant.engine;

import com.mlvmn.apsvendingissueassistant.network.apis.DemoApi;
import com.mlvmn.apsvendingissueassistant.network.apis.Api;
import com.mlvmn.apsvendingissueassistant.network.apis.LiveApi;
import com.mlvmn.apsvendingissueassistant.resources.Settings;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author tejum
 */
public final class VendControl {
    
    private static VendControl controller;
    
    //An API, either demo or live, it depends on the user's selection
    private Api anApi;
    
    //HttpClient to handle all API calls
    private final HttpClient client;
    
    //Authorization token, retrieved upon a successful call to login, it is used 
    //to authenticate API calls
    private String authToken;
    
    //Constructor
    private VendControl(){
        changeVendType(Settings.getSettings().retrieveDemoLiveState());
        
        //Get the previously stored authorization token
        authToken = Settings.getSettings().retrieveAuthToken();
        
        HttpClient.Builder builder = HttpClient.newBuilder();
        builder.connectTimeout(Duration.ofMinutes(3));
        client = builder.build();
    }
    
    /**
     * Method retrieves an instance of the class
     * @return instance of VendControl class
     */
    public static VendControl getInstance(){
        if(controller == null)
            controller = new VendControl();
        return controller;
    }

    /**
     * This method handles the switch between the live and demo endpoints of 
     * Access Power System's API.
     * @param vendState Boolean, true for live vends, false for demo vends.
     */
    public void changeVendType(boolean vendState){
        
        Settings.getSettings().storeDemoLiveState(vendState);
        
        if(vendState){
            anApi = new LiveApi();
        } else{
            anApi = new DemoApi();
        }
    }
    
    //This method handle obtaining the authorization code for all subsequent API 
    //calls
    private void login() throws IOException, InterruptedException {
        String[] creds = Settings.getSettings().retrieveCredentials();
        
        //up - username and password
        JSONObject up = new JSONObject();
        up.put("username", creds[0]);
        up.put("password", creds[1]);
        
        
        HttpRequest request = anApi.login(up, creds[2]);
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        JSONObject jsonBody = new JSONObject(response.body());
        
        if (jsonBody.has("token")) {
            Settings.getSettings().storeAuthToken(jsonBody.getString("token"));
            authToken = jsonBody.getString("token");
        }
    }
    
    /**
     * This Method handles the API call to retrieve the balance in a vendor's 
     * wallet.
     * @return String representing the balance in the wallet
     * @throws IOException thrown if something is wrong with the internet connection
     * @throws InterruptedException thrown if call to the API interrupted before 
     * the call could complete.
     */
    public String getBalance() throws IOException, InterruptedException, JSONException{
        
        String rawBal = doWorkCheckLogin(anApi.balance(authToken));            
        
        return rawBal;
    }

    //This private method takes care of repetitive work ensuring that each 
    //request call is authorised. If a response to a request is expired or 
    //missing an authorization token it calls the login method to obtain a new 
    //token for subsequent requests till the token expires.
    private String doWorkCheckLogin(HttpRequest req) throws InterruptedException, IOException {
        
        String rawString = "";
        
        boolean retryRequest;
        
        do {
            
            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
            
            rawString = res.body();
            
            //if response message is an error message
            if(isError(rawString)){
                
                //Check if it has to do with authorization
                if(
                        "invalid session token. please login first".equals(getErrorMessage(rawString)) || 
                        "session token header not found".equals(getErrorMessage(rawString)))
                {
                    login();
                    retryRequest = true;
                }
                
                //if the error does not have to do authorization end the loop to 
                //pass the message to be handled elsewhere
                else{
                    retryRequest = false;
                }
            } 
            
            //if there are no errors, end the loop
            else {
                retryRequest = false;
            }
        } while (retryRequest);
        
        return rawString;
    }

    /**
     * This method checks if the JSON object in the response body is an error 
     * response, signifying a failure to retrieve a successful result.
     * @param result String 
     * @return boolean, true if the response message is an error, false if it is not.
     */
    public boolean isError(String result) {
        JSONObject jsonRes = new JSONObject(result);
        
        boolean status;
        
        if(jsonRes.has("isError")){
            
            status = jsonRes.getBoolean("isError");
        } else{
            status = false;
        }
        
        return status;
    }

    /**
     * This method retrieves the error message from the body of a JSON error response.
     * @param result Error message
     * @return String character representing details of the error of an API call.
     */
    public String getErrorMessage(String result) {
        JSONObject jsonRes = new JSONObject(result);
        
        return jsonRes.optString("error", "Unknown Error!");
    }
    
}
