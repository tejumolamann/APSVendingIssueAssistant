/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mlvmn.apsvendingissueassistant.errors;

import com.google.gson.JsonObject;
import com.mlvmn.apsvendingissueassistant.engine.VendControl;

/**
 *
 * @author tejum
 */
public class AccessPowerException extends Exception{
    
    public AccessPowerException(JsonObject jsonError) {
        super(VendControl.getInstance().getErrorMessage(jsonError));
    }

    public AccessPowerException(String message) {
        super(message);
    }
    
}
