/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mlvmn.apsvendingissueassistant.errors;

/**
 *
 * @author tejum
 */
public class AccessPowerException extends Exception{
    
    public static final String NETOWRK_ERROR_MESSAGE = "A network error occurred";
    public static final String NETWORK_ERROR_INTERRUPTION_MESSAGE = "The netork was interrupted";

    public AccessPowerException(String message) {
        super(message);
    }

    public AccessPowerException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
