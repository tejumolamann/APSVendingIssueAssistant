/*
 * This class is for the presentation of successful data response from API calls.
 * The presentation is in human readable form, for display on the screen, text 
 * transafer and printing on paper receipts.
 */
package com.mlvmn.apsvendingissueassistant.resources;

import org.json.JSONObject;

/**
 *
 * @author tejum
 */
public class Receipt {
    
    private static final String SCREEN_NEW_LINE = "\n-----\n";
    
    private final JSONObject jsonResponse;

    public Receipt(JSONObject jsonResponse) {
        this.jsonResponse = jsonResponse;
    }
    
    public String printBalanceToScreen(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("Total Credits: ₦");
        sb.append(this.jsonResponse.getDouble("totalCredit"));
        sb.append(SCREEN_NEW_LINE);
        sb.append("Total Amount of Vends: ₦");
        sb.append(this.jsonResponse.getDouble("totalDebit"));
        sb.append(SCREEN_NEW_LINE);
        sb.append("Current Balance: ₦");
        sb.append(this.jsonResponse.getDouble("currentBalance"));
        
        return sb.toString();
    }
    
}
