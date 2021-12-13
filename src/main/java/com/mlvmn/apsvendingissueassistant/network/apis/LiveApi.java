/*
 * The class that creates HttpRequest objects for the live end points on Access 
 * Power Systems.
 */
package com.mlvmn.apsvendingissueassistant.network.apis;

import static com.mlvmn.apsvendingissueassistant.network.apis.Api.APPLICATION_JSON;
import static com.mlvmn.apsvendingissueassistant.network.apis.Api.AUTHORIZATION;
import static com.mlvmn.apsvendingissueassistant.network.apis.Api.CONTENT_TYPE;
import com.mlvmn.apsvendingissueassistant.resources.Settings;
import java.net.URI;
import java.net.http.HttpRequest;
import org.json.JSONObject;

/**
 *
 * @author tejum
 */
public class LiveApi extends Api{
    
    private static final String HOST_LIVE = "http://3.14.166.23";
    private static final String MACHINE = "Machine";
    
    private String machineId;
    
    public LiveApi(){
        machineId = Settings.getSettings().retrieveMachineID();
    }

    @Override
    public HttpRequest login(JSONObject credentials, String loginAuthToken) {
        //request builder
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        
        //create the end point's URI
        builder.uri(URI.create(HOST_LIVE.concat("/api/login")));
        
        //set the request's headers, authorization and content-type
        builder.setHeader(AUTHORIZATION, loginAuthToken);
        builder.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        builder.setHeader(MACHINE, machineId);
        
        //set the request's body and timeout duration
        builder.POST(HttpRequest.BodyPublishers.ofString(credentials.toString()));
        
        //build and return the HttpRequest object
        return builder.build();
    }

    @Override
    public HttpRequest.Builder balance(String accessCode) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        
        builder.uri(URI.create(HOST_LIVE.concat("/api/wallet")));
        
        builder.setHeader(AUTHORIZATION, accessCode);
        builder.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        builder.setHeader(MACHINE, machineId);
        
        builder.GET();
        
        return builder;
    }

    @Override
    public HttpRequest.Builder validateMeterNumber(String accessCode, String meterNum) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        
        builder.uri(URI.create(HOST_LIVE.concat("/api/meter")));
        
        builder.setHeader(AUTHORIZATION, accessCode);
        builder.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        builder.setHeader(MACHINE, machineId);
        
        JSONObject body = new JSONObject();
        body.put(METER_NO, meterNum);
        
        builder.POST(HttpRequest.BodyPublishers.ofString(body.toString()));
        
        return builder;
    }

    @Override
    public HttpRequest.Builder newTransaction(String accessCode, String meterNum, double amount, String phoneNum) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        
        builder.uri(URI.create(HOST_LIVE.concat("/api/transaction")));
        
        builder.setHeader(AUTHORIZATION, accessCode);
        builder.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        builder.setHeader(MACHINE, machineId);
        
        JSONObject body = new JSONObject();
        body.put(METER_NO, meterNum);
        body.put("amount", amount);
        body.put("gsmNo", phoneNum);
        
        builder.POST(HttpRequest.BodyPublishers.ofString(body.toString()));
        
        return builder;
    }

    @Override
    public HttpRequest.Builder vendTransaction(String accessCode, String transRef) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        
        builder.uri(URI.create(HOST_LIVE.concat("/api/transaction/pay")));
        
        builder.header(AUTHORIZATION, accessCode);
        builder.header(CONTENT_TYPE, APPLICATION_JSON);
        builder.setHeader(MACHINE, machineId);
        
        builder.POST(HttpRequest.BodyPublishers.ofString(new JSONObject().put("transactionReference", transRef).toString()));
        
        return builder;
    }
    
}
