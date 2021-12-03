/*
 * The superclass for the end points where demo and live endpoints take their 
 * root from.
 */
package com.mlvmn.apsvendingissueassistant.network.apis;

import java.net.http.HttpRequest;
import org.json.JSONObject;

/**
 *
 * @author tejum
 */
public abstract class Api {

    //Reuseable constants 
    protected static final String AUTHORIZATION = "Authorization";
    protected static final String APPLICATION_JSON = "application/json";
    protected static final String CONTENT_TYPE = "Content-Type";
    protected static final String METER_NO = "meterNo";

    protected Api() {
    }
    
    /**
     * Abstract method that is overridden by the demo and live end points and 
     * it is for the login endpoint.
     * @param credentials
     * @param loginAuthToken
     * @return HttpRequest object representing the request of the API's request
     */
    abstract public HttpRequest login(JSONObject credentials, String loginAuthToken);
    
    /**
     * This abstract method is overridden by the Demo and Live APIs, and the 
     * methods take care of building a request to check a vendor's wallet's 
     * balance.
     * @param accessCode
     * @return HttpRequest object
     */
    abstract public HttpRequest.Builder balance(String accessCode);
    
    /**
     * An abstract method that defines the behaviour for validating meter 
     * numbers but live and demo implementations can be different.
     * 
     * @param accessCode String which is the authorization code for the request
     * @param meterNum String, meter number to check for its correctness
     * @return HttpRequest object for validating meter numbers
     */
    abstract public HttpRequest.Builder validateMeterNumber(String accessCode, String meterNum);
    
    /**
     * This method ensures that implementing methods create an HttpRequest object
     * is created and when a request is made with it a new transaction is created 
     * with a unique transaction reference.
     * @param accessCode String which is the authorization code for the request
     * @param meterNum String, meter number for the transaction
     * @param amount double, amount to vend
     * @param phoneNum String, the customer's phone number (compulsory)
     * @return HttpRequest object to create new transaction
     */
    abstract public HttpRequest.Builder newTransaction(String accessCode, String meterNum, double amount, String phoneNum);
    
    /**
     * Abstract method that represents the actual vend.
     * @param accessCode String which is the authorization code for the request
     * @param transRef String, unique transaction reference to this vend and it 
     * is obtained from the successful response message for new transaction
     * @return HttpRequest object to be used with a client to perform the vend.
     */
    abstract public HttpRequest.Builder vendTransaction(String accessCode, String transRef);
    
}
