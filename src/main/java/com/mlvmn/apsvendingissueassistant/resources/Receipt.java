/*
 * This class is for the presentation of successful data response from API calls.
 * The presentation is in human readable form, for display on the screen, text 
 * transafer and printing on paper receipts.
 */
package com.mlvmn.apsvendingissueassistant.resources;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author tejum
 */
public class Receipt {
    
    private static final String SCREEN_NEW_LINE = "\n-----\n";
    
    private static final String METER__NUMBER = "Meter Number: ";
    private static final String DEBT_DEDUCTION = "Debt deduction: ₦";
    
    //JSON Keys
    private static final String METER_NO = "meterNo";
    
    
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

    public String printMeterDetailsToScreen() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(METER__NUMBER);
        sb.append(this.jsonResponse.getString(METER_NO));
        
        sb.append(SCREEN_NEW_LINE);
        
        sb.append(METER__REGISTERED_TO);
        sb.append(this.jsonResponse.getString(CUSTOMER_NAME));
        
        sb.append(SCREEN_NEW_LINE);
        
        sb.append(CUSTOMER__ADDRESS);
        sb.append(this.jsonResponse.getString(ADDRESS));
        
        sb.append(SCREEN_NEW_LINE);
        
        sb.append("Registered Phone Number: ");
        sb.append(this.jsonResponse.getString("phoneNo"));
        
        sb.append(SCREEN_NEW_LINE);
        
        sb.append("Registered Email: ");
        sb.append(this.jsonResponse.getString("email"));
        
        sb.append(SCREEN_NEW_LINE);
        
        sb.append("Type of Meter: ");
        sb.append(this.jsonResponse.getString("meterType"));
        
        sb.append(SCREEN_NEW_LINE);
        
        sb.append("Tariff: ");
        sb.append(this.jsonResponse.getDouble("unitRate"));
        
        sb.append(SCREEN_NEW_LINE);
        
        sb.append("Debt Balance: ₦");
        sb.append(this.jsonResponse.getDouble("accountBalance"));
        
        sb.append(SCREEN_NEW_LINE);
        
        sb.append("Minimum Vend Payment: ₦");
        sb.append(this.jsonResponse.getDouble("minimumPurchase"));
        
        return sb.toString();
    }
    private static final String ADDRESS = "address";
    private static final String CUSTOMER__ADDRESS = "Customer Address: ";
    private static final String CUSTOMER_NAME = "customerName";
    private static final String METER__REGISTERED_TO = "Meter Registered to: ";
    

    public String printTransactionToScreen() {
        StringBuilder sb = new StringBuilder();
        
        JSONObject meterInfo = this.jsonResponse.getJSONObject("customerMeterInfo");
        
        sb.append("Transaction Reference: ")
                .append(this.jsonResponse.getString("transactionReference"))
                .append(SCREEN_NEW_LINE);
        
        sb.append(METER__NUMBER)
                .append(meterInfo.getString(METER_NO))
                .append(SCREEN_NEW_LINE);
        
        sb.append(METER__REGISTERED_TO)
                .append(meterInfo.getString(CUSTOMER_NAME))
                .append(SCREEN_NEW_LINE);
        
        sb.append(CUSTOMER__ADDRESS)
                .append(meterInfo.getString(ADDRESS))
                .append(SCREEN_NEW_LINE);
        
        sb.append("Amount Tendered: ₦")
                .append(this.jsonResponse.getDouble("totalAmount"))
                .append(SCREEN_NEW_LINE);
        
        sb.append("Units onbtainable: ")
                .append(this.jsonResponse.getDouble("units"))
                .append(SCREEN_NEW_LINE);
        
        sb.append("Value of units: ₦")
                .append(this.jsonResponse.getDouble("unitsGross"))
                .append(SCREEN_NEW_LINE);
        
        sb.append("Tax: ₦")
                .append(this.jsonResponse.getDouble("unitsTax"))
                .append(SCREEN_NEW_LINE);
        
        sb.append(DEBT_DEDUCTION)
                .append(this.jsonResponse.getDouble("outstandingChargesNet"))
                .append(SCREEN_NEW_LINE);
        JSONArray outstandingDetails = this.jsonResponse.getJSONArray("outstandingCharges");
        
        if(outstandingDetails.length() != 0){
            sb.append(outstandingDetails.getString(0))
                    .append("\n");
            
            sb.append("Current outstanding balance: ₦")
                    .append(outstandingDetails.getDouble(1))
                    .append("\n");
            
            sb.append(DEBT_DEDUCTION)
                    .append(outstandingDetails.getDouble(2))
                    .append("\n");
            
            sb.append("Deduction: ₦")
                    .append(outstandingDetails.getDouble(3))
                    .append("%")
                    .append("\n");
        }
        
        return sb.toString();
    }
    
    
}
