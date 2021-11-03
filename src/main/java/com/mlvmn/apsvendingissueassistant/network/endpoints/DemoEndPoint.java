/*
 * The class that creates HttpRequest objects for the demo end points on Access 
 * Power Systems.
 */
package com.mlvmn.apsvendingissueassistant.network.endpoints;

import com.mlvmn.apsvendingissueassistant.resources.Settings;
import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;
import org.json.JSONObject;

/**
 *
 * @author tejum
 */
public class DemoEndPoint extends EndPoint{
    
    //Host for all demo end points
    private static final String HOST_DEMO = "https://thirdparty.api.accesspower.ng";
    
    
    public DemoEndPoint(){
        accessCode = "";
    }

    /**
     * This method creates the HttpRequest object for the login end point
     * @return HttpRequest object
     */
    @Override
    public HttpRequest login() {
        
        //request builder
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        
        //create the end point's URI
        builder.uri(URI.create(HOST_DEMO.concat("/api/login")));
        
        //retrieve login credentials, username, password and authorization code
        String[] credentials = Settings.getSettings().retrieveCredentials();
        
        //set the request's headers, authorization and content-type
        builder.setHeader(AUTHORIZATION, credentials[2]);
        builder.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        
        //build the request's JSON body, with the username and password key and 
        //value pairs
        JSONObject body = new JSONObject();
        body.putOnce("username", credentials[0]);
        body.put("password", credentials[1]);
        
        //set the request's body and timeout duration
        builder.POST(HttpRequest.BodyPublishers.ofString(body.toString()));
        builder.timeout(Duration.ofMinutes(3));
        
        //build and return the HttpRequest object
        return builder.build();
    }
    
    /**
     * This method creates the HttpRequest object for the end point used for 
     * obtaining the balance in the wallet of a vendor.
     * @return HttpRequest object
     */
    @Override
    protected HttpRequest balance() {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        
        builder.uri(URI.create(HOST_DEMO.concat("/api/wallet/balance")));
        builder.setHeader(AUTHORIZATION, accessCode);
        builder.setHeader(APPLICATION_JSON, CONTENT_TYPE);
        builder.GET();
        
        return builder.build();
    }
    
}
