/*
 * The superclass for the end points where demo and live endpoints take their 
 * root from.
 */
package com.mlvmn.apsvendingissueassistant.network.apis;

import java.net.http.HttpRequest;
import com.google.gson.JsonObject;

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
     * @param credentials the value of credentials
     * @param loginAuthToken the value of loginAuthToken
     * @return HttpRequest
     */
    abstract public HttpRequest login(JsonObject credentials, String loginAuthToken);
    
    /**
     * This abstract method is overridden by the Demo and Live APIs, and the 
     * methods take care of building a request to check a vendor's wallet's 
     * balance.
     * @param accessCode
     * @return HttpRequest object
     */
    abstract public HttpRequest balance(String accessCode);
    
    /**
     * An abstract method that defines the behaviour for validating meter 
     * numbers but live and demo implementations can be different.
     * 
     * @param accessCode String which is the authorization code for the request
     * @param meterNum String, meter number to check for its correctness
     * @return HttpRequest object for validating meter numbers
     */
    abstract public HttpRequest validateMeterNumber(String accessCode, String meterNum);
    
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
    abstract public HttpRequest newTransaction(String accessCode, String meterNum, double amount, String phoneNum);
    
    /**
     * Abstract method that represents the actual vend.
     * @param accessCode String which is the authorization code for the request
     * @param transRef String, unique transaction reference to this vend and it 
     * is obtained from the successful response message for new transaction
     * @return HttpRequest object to be used with a client to perform the vend.
     */
    abstract public HttpRequest vendTransaction(String accessCode, String transRef);
    
    /**
     * This method recreates the HttpRequest previously built a descendant of 
     * this class that may have contained an expired access code or none 
     * at all, it takes the old HttpRequest and the new access code and
     * returns a new HttpRequest built with the new access code, but all 
     * other parameters like URI, BodyPublisher method remains the same. Its 
     * implementation is dependant on this class child class.
     * 
     * @param oldRequest
     * @param accessCode
     * @return HttpRequest
     */
    abstract public HttpRequest rebuidRequestWithNewAuthToken(HttpRequest oldRequest, String accessCode);
    
}
