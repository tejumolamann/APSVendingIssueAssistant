/*
 * The vending controller, it controls the vending operations of the program.
 */
package com.mlvmn.apsvendingissueassistant.engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mlvmn.apsvendingissueassistant.errors.AccessPowerException;
import com.mlvmn.apsvendingissueassistant.network.apis.DemoApi;
import com.mlvmn.apsvendingissueassistant.network.apis.Api;
import com.mlvmn.apsvendingissueassistant.network.apis.LiveApi;
import com.mlvmn.apsvendingissueassistant.resources.Settings;
import com.mlvmn.apsvendingissueassistant.vending.BalanceSummary;
import com.mlvmn.apsvendingissueassistant.vending.Meter;
import com.mlvmn.apsvendingissueassistant.vending.NewTransaction;
import com.mlvmn.apsvendingissueassistant.vending.PaidTransaction;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

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
    private VendControl() {
        changeVendType(Settings.getSettings().retrieveDemoLiveState());

        //Get the previously stored authorization token
        authToken = Settings.getSettings().retrieveAuthToken();

        HttpClient.Builder builder = HttpClient.newBuilder();
        builder.connectTimeout(Duration.ofMinutes(3));
        client = builder.build();
    }

    /**
     * Method retrieves an instance of the class
     *
     * @return instance of VendControl class
     */
    public static VendControl getInstance() {
        if (controller == null) {
            controller = new VendControl();
        }
        return controller;
    }

    /**
     * This method handles the switch between the live and demo endpoints of
     * Access Power System's API.
     *
     * @param vendState Boolean, true for live vends, false for demo vends.
     */
    public void changeVendType(boolean vendState) {

        Settings.getSettings().storeDemoLiveState(vendState);

        if (vendState) {
            anApi = new LiveApi();
        } else {
            anApi = new DemoApi();
        }
    }

    //This method handle obtaining the authorization code for all subsequent API 
    //calls
    private void login() throws IOException, InterruptedException, AccessPowerException {
        String[] creds = Settings.getSettings().retrieveCredentials();

        //up - username and password
        JsonObject up = new JsonObject();
        up.addProperty("username", creds[0]);
        up.addProperty("password", creds[1]);

        HttpRequest request = anApi.login(up, creds[2]);

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);
        final String token = "token";

        if (jsonResponse.has(token)) {
            String aToken = jsonResponse.get(token).getAsString();
            Settings.getSettings().storeAuthToken(aToken);
            authToken = aToken;
        } else {
            throw new AccessPowerException("Unable to obtain access token");
        }
    }

    /**
     *
     * @return the com.mlvmn.apsvendingissueassistant.vending.BalanceSummary
     * @throws IOException
     * @throws InterruptedException
     * @throws com.mlvmn.apsvendingissueassistant.errors.AccessPowerException
     */
    public BalanceSummary getBalance() throws IOException, InterruptedException, AccessPowerException {

        JsonObject rawBal = doWorkCheckLogin(anApi.balance(authToken));
        
        if (isError(rawBal)) {
            throw new AccessPowerException(getErrorMessage(rawBal));
        }
        
        Gson gson = new GsonBuilder().serializeNulls().create();

        return gson.fromJson(rawBal, BalanceSummary.class);
    }

    public Meter validateMeterNumber(String meterNum) throws InterruptedException, IOException, AccessPowerException {
        JsonObject meterDetails = doWorkCheckLogin(anApi.validateMeterNumber(authToken, meterNum));
        
        if (isError(meterDetails)) {
            throw new AccessPowerException(getErrorMessage(meterDetails));
        }

        Gson gson = new GsonBuilder().serializeNulls().create();

        return gson.fromJson(meterDetails, Meter.class);
    }

    /**
     * 
     * @param meterNum the value of meterNum
     * @param amount the value of amount
     * @param phoneNum the value of phoneNum
     * @param deductServiceCharge the value of deductServiceCharge
     * @throws InterruptedException
     * @throws IOException
     * @throws AccessPowerException
     */
    public NewTransaction newTransaction(String meterNum, double amount, String phoneNum, boolean deductServiceCharge) throws InterruptedException, IOException, AccessPowerException {
        double vendAmount;
        
        if (deductServiceCharge) {
            vendAmount = amount - Double.parseDouble(Settings.getSettings().retrieveServiceCharge());
        } else {
            vendAmount = amount;
        }
        
        
        JsonObject transaction = doWorkCheckLogin(anApi.newTransaction(authToken, meterNum, vendAmount, phoneNum));

        //Sometimes a meter number needs to be polled from AEDC's servers before 
        //transaction can take place and a simple validation of the meter number 
        //is all that's required
        if (isError(transaction)) {
            
            if (getErrorMessage(transaction).equals("There was an error loading your customer info. Please try again")) {
                Meter meter = validateMeterNumber(meterNum);
                newTransaction(meter.getMeterNo(), amount, phoneNum, deductServiceCharge);
            } else{
                //If any other error occurred inform the user
                throw new AccessPowerException(getErrorMessage(transaction));
            }
        }

        Gson gson = new GsonBuilder().serializeNulls().create();
        NewTransaction newTransaction = gson.fromJson(transaction, NewTransaction.class);
        
        if (deductServiceCharge) {
            newTransaction.setServiceCharge(Double.parseDouble(Settings.getSettings().retrieveServiceCharge()));
        }

        return newTransaction;
    }

    public PaidTransaction vendTransaction(String transRef) throws InterruptedException, IOException, AccessPowerException {
        JsonObject vend = doWorkCheckLogin(anApi.vendTransaction(authToken, transRef));
        
        if (isError(vend)) {
            throw new AccessPowerException(getErrorMessage(vend));
        }

        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.fromJson(vend, PaidTransaction.class);
    }
    
    public PaidTransaction vendTransaction(NewTransaction newTransaction) throws InterruptedException, IOException, AccessPowerException {
        JsonObject vend = doWorkCheckLogin(anApi.vendTransaction(authToken, newTransaction.getTransactionReference()));
        
        if (isError(vend)) {
            throw new AccessPowerException(getErrorMessage(vend));
        }

        Gson gson = new GsonBuilder().serializeNulls().create();
        PaidTransaction paidTransaction = gson.fromJson(vend, PaidTransaction.class);
        
        paidTransaction.setServiceCharge(newTransaction.getServiceCharge());
        
        return paidTransaction;
    }

    //This private method takes care of repetitive work ensuring that each 
    //request call is authorised. If a response to a request is expired or 
    //missing an authorization token it calls the login method to obtain a new 
    //token for subsequent requests till the token expires.
    private JsonObject doWorkCheckLogin(HttpRequest request) throws InterruptedException, IOException, AccessPowerException {

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new AccessPowerException("Something went wrong with our server."
                    + "\nPlease check it or try again later.");
        }

        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonObject json = gson.fromJson(response.body(), JsonObject.class);

        //if response message is an error message
        if (isError(json)) {

            //Check if it has to do with authorization
            if ("invalid session token. please login first".equals(getErrorMessage(json))
                    || "session token header not found".equals(getErrorMessage(json))
                    || "session token has expired. please login".equals(getErrorMessage(json))) {

                login();
                anApi.rebuidRequestWithNewAuthToken(request, authToken);
                doWorkCheckLogin(request);
            }
        }

        return json;
    }

    /**
     * This method checks if the JSON object in the response body is an error
     * response, signifying a failure to retrieve a successful jsonObjResponse.
     *
     * @param jsonObjResponse String
     * @return boolean, true if the response message is an error, false if it is
     * not.
     */
    public boolean isError(JsonObject jsonObjResponse) {

        JsonElement isError = jsonObjResponse.get("isError");

        return isError != null && isError.getAsBoolean();
    }

    /**
     * This method retrieves the error message from the body of a JSON error
     * response.
     *
     * @param jsonError
     * @return String character representing details of the error of an API
     * call.
     */
    public String getErrorMessage(JsonObject jsonError) {
        return jsonError.get("error").getAsString();
    }

}
