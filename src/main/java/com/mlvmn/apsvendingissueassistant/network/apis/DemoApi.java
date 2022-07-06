/*
 * The class that creates HttpRequest objects for the demo end points on Access 
 * Power Systems.
 */
package com.mlvmn.apsvendingissueassistant.network.apis;

import static com.mlvmn.apsvendingissueassistant.network.apis.Api.AUTHORIZATION;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.google.gson.JsonObject;

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
     * @param credentials the value of credentials
     * @param loginAuthToken the value of loginAuthToken
     */
    @Override
    public HttpRequest login(com.google.gson.JsonObject credentials, String loginAuthToken) {
        
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

    @Override
    public HttpRequest validateMeterNumber(String accessCode, String meterNum) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        
        builder.uri(URI.create(HOST_DEMO.concat("/api/meters/search")));
        builder.setHeader(AUTHORIZATION, accessCode);
        builder.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        
        JsonObject body = new JsonObject();
        body.addProperty(METER_NO, meterNum);
        
        builder.POST(HttpRequest.BodyPublishers.ofString(body.toString()));
        
        return builder.build();
        
    }

    @Override
    public HttpRequest newTransaction(String accessCode, String meterNum, double amount, String phoneNum) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        
        builder.uri(URI.create(HOST_DEMO.concat("/api/transactions/new")));
        builder.setHeader(AUTHORIZATION, accessCode);
        builder.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        
        JsonObject body = new JsonObject();
        body.addProperty(METER_NO, meterNum);
        body.addProperty("amount", amount);
        body.addProperty("gsmNo", phoneNum);
        
        builder.POST(HttpRequest.BodyPublishers.ofString(body.toString()));
        
        return builder.build();
        
    }

    @Override
    public HttpRequest vendTransaction(String accessCode, String transRef) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        
        builder.uri(URI.create(HOST_DEMO.concat("/api/transactions/pay")));
        builder.header(AUTHORIZATION, accessCode);
        builder.header(CONTENT_TYPE, APPLICATION_JSON);
        
        JsonObject body = new JsonObject();
        body.addProperty("transactionReference", transRef);
        
        builder.POST(HttpRequest.BodyPublishers.ofString(body.toString()));
        
        return builder.build();
    }

    @Override
    public HttpRequest rebuidRequestWithNewAuthToken(HttpRequest oldRequest, String accessCode) {
        URI uri = oldRequest.uri();
        Map<String, List<String>> headers = oldRequest.headers().map();
        Optional<HttpRequest.BodyPublisher> bodyPublisher = oldRequest.bodyPublisher();
        String httpMethod = oldRequest.method();
        
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        builder.uri(uri);
        headers.keySet().forEach(headerName -> {
            if(headerName.equals(AUTHORIZATION)){
                builder.header(headerName, accessCode);
            } else{
                builder.header(headerName, headers.get(headerName).get(0));
            }
        });
        builder.method(httpMethod, bodyPublisher.orElse(HttpRequest.BodyPublishers.noBody()));
        
        return builder.build();
    }
    
}
