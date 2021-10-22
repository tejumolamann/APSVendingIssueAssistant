/*
 * The vending controller, it controls the vending operations of the program.
 */
package com.mlvmn.apsvendingissueassistant.engine;

/**
 *
 * @author tejum
 */
public class VendControl {
    
    private static VendControl controller;
    
    private VendControl(){
        
    }
    
    public static VendControl getInstance(){
        if(controller == null)
            controller = new VendControl();
        return controller;
    }
    
}
