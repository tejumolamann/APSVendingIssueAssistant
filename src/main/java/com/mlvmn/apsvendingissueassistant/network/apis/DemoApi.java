/*
 * The class that creates HttpRequest objects for the demo end points on Access 
 * Power Systems.
 */
package com.mlvmn.apsvendingissueassistant.network.apis;

import java.net.URI;
import java.net.http.HttpRequest;
import org.json.JSONObject;

/**
 *
 * @author tejum
 */
public class DemoApi extends Api{
    
    //Host for all demo end points
    private static final String HOST_DEMO = "https://thirdparty.api.accesspower.ng";
    
    
    public DemoApi(){
        
    }

    /**
     * This method creates the HttpRequest object for the login end point.
     * @param credentials JSONObject of the username and password
     * @param loginAuthToken String characters supplied by Access Power Systems
     * @return HttpRequest object
     */
    @Override
    public HttpRequest login(JSONObject credentials, String loginAuthToken) {
        
        //request builder
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        
        //create the end point's URI
        builder.uri(URI.create(HOST_DEMO.concat("/api/login")));
        
        //set the request's headers, authorization and content-type
        builder.setHeader(AUTHORIZATION, loginAuthToken);
        builder.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        
        //set the request's body and timeout duration
        builder.POST(HttpRequest.BodyPublishers.ofString(credentials.toString()));
        
        //build and return the HttpRequest object
        return builder.build();
    }
    
    /**
     * This method creates the HttpRequest object for the end point used for 
     * obtaining the balance in the wallet of a vendor.
     * @param accessCode String of characters received upon successful login that 
     * authorizes the request of an endpoint
     * @return HttpRequest object
     */
    @Override
    public HttpRequest balance(String accessCode) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        
        builder.uri(URI.create(HOST_DEMO.concat("/api/wallet/balance")));
        builder.setHeader(AUTHORIZATION, accessCode);
        builder.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        builder.GET();
        
        return builder.build();
    }
    
}