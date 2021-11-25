/*
 * The class that creates HttpRequest objects for the live end points on Access 
 * Power Systems.
 */
package com.mlvmn.apsvendingissueassistant.network.apis;

import java.net.http.HttpRequest;
import org.json.JSONObject;

/**
 *
 * @author tejum
 */
public class LiveApi extends Api{
    
    private static final String HOST_LIVE = "http://3.14.166.23";
    
    public LiveApi(){
        
    }

    @Override
    public HttpRequest login(JSONObject credentials, String loginAuthToken) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HttpRequest balance(String accessCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HttpRequest validateMeterNumber(String accessCode, String meterNum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HttpRequest newTransaction(String accessCode, String meterNum, double amount, String phoneNum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HttpRequest vendTransaction(String accessCode, String transRef) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
