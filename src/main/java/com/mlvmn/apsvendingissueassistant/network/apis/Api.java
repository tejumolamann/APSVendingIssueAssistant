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
    abstract public HttpRequest balance(String accessCode);
    
}
