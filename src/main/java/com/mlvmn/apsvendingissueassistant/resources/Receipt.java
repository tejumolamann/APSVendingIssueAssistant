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
    
    /*Labels*/
    private static final String METER__NUMBER = "Meter Number: ";
    private static final String DEBT_DEDUCTION = "Debt deduction: ₦";
    private static final String TRANSACTION__REFERENCE_ = "Transaction Reference: ";
    private static final String PHONE__NUMBER_ = "Phone Number: ";
    private static final String DEDUCTION_ = "Deduction: %";
    private static final String CURRENT_OUTSTANDING_BALANCE = "Current outstanding balance: ₦";
    private static final String TAX = "Tax: ₦";
    private static final String VALUE_OF_UNITS = "Value of units: ₦";
    private static final String UNITS_ONBTAINABLE_ = "Units onbtainable: ";
    private static final String AMOUNT__TENDERED = "Amount Tendered: ₦";
    private static final String TYPE_OF__METER = "Type of Meter: ";
    private static final String METER__REGISTERED_TO = "Meter Registered to: ";
    private static final String CUSTOMER__ADDRESS = "Customer Address: ";
    private static final String SERVICE_CHARGE = "Service charge: ₦";
    private static final String OUTSTANDING_BALANCE = "Outstanding balance: ₦";
    private static final String OUTSTANDING_DESCRIPTION = "Outstanding description: ";
    
    /*JSON Keys*/
    private static final String METER_NO = "meterNo";
    private static final String TRANSACTION_REFERENCE = "transactionReference";
    private static final String PHONE_NO = "phoneNo";
    private static final String OUTSTANDING_CHARGES = "outstandingCharges";
    private static final String OUTSTANDING_CHARGES_NET = "outstandingChargesNet";
    private static final String UNITS_TAX = "unitsTax";
    private static final String UNITS_GROSS = "unitsGross";
    private static final String UNITS = "units";
    private static final String TOTAL_AMOUNT = "totalAmount";
    private static final String METER_TYPE = "meterType";
    private static final String ADDRESS = "address";
    private static final String CUSTOMER_NAME = "customerName";
    private static final String OUTSTANDING_TYPE = "type";
    
    
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

    public String printTransactionToScreen(boolean appliedServiceCharge) {
        StringBuilder sb = new StringBuilder();
        
        JSONObject meterInfo = this.jsonResponse.getJSONObject("customerMeterInfo");
        
        sb.append(TRANSACTION__REFERENCE_)
                .append(this.jsonResponse.getString(TRANSACTION_REFERENCE))
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
        
        sb.append(AMOUNT__TENDERED)
                .append(this.jsonResponse.getDouble(TOTAL_AMOUNT))
                .append(SCREEN_NEW_LINE);
        
        //service charge
        sb.append(SERVICE_CHARGE);
        if (appliedServiceCharge){
            sb.append(Settings.getSettings().retrieveServiceCharge());
        } else {
            sb.append("0.0");
        }
        sb.append(SCREEN_NEW_LINE);
        
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
        
        if (!this.jsonResponse.isNull(OUTSTANDING_CHARGES)) {
            JSONArray outstandingArray = this.jsonResponse.getJSONArray(OUTSTANDING_CHARGES);
            
            if (outstandingArray.length() != 0) {
                
                JSONObject outstandingDetails = outstandingArray.getJSONObject(0);
                
                sb.append(OUTSTANDING_DESCRIPTION)
                        .append(outstandingDetails.getString(OUTSTANDING_TYPE))
                        .append(SCREEN_NEW_LINE);
                
                sb.append(OUTSTANDING_BALANCE)
                        .append(outstandingDetails.getDouble("currentOutstandingBalance"))
                        .append(SCREEN_NEW_LINE);
                
                sb.append("Debt to be deducted: ₦")
                        .append(outstandingDetails.getDouble("amountPaying"))
                        .append(SCREEN_NEW_LINE);
                
                sb.append("Percentage deduction: %")
                        .append(outstandingDetails.getDouble("percentPaying"))
                        .append(SCREEN_NEW_LINE);
            }
        }
        
        sb.append("Date: ")
                .append(this.jsonResponse.getString("transactionDate"))
                .append(SCREEN_NEW_LINE);
        
        sb.append("Time: ")
                .append(this.jsonResponse.getString("transactionTime"))
                .append(SCREEN_NEW_LINE);
        
        return sb.toString();
    }

    public String printVendToScreen(boolean appliedServiceCharge) {
        StringBuilder sb = new StringBuilder();
        
        JSONObject meterInfo = this.jsonResponse.getJSONObject("customerMeterInfo");
        
        sb.append(METER__NUMBER)
                .append(meterInfo.getString(METER_NO))
                .append(SCREEN_NEW_LINE);
        
        sb.append(METER__REGISTERED_TO)
                .append(meterInfo.getString(CUSTOMER_NAME))
                .append(SCREEN_NEW_LINE);
        
        sb.append(CUSTOMER__ADDRESS)
                .append(meterInfo.getString(ADDRESS))
                .append(SCREEN_NEW_LINE);
        
        sb.append(PHONE__NUMBER_)
                .append(meterInfo.getString(PHONE_NO))
                .append(SCREEN_NEW_LINE);
        
        sb.append(TRANSACTION__REFERENCE_)
                .append(this.jsonResponse.getString(TRANSACTION_REFERENCE))
                .append(SCREEN_NEW_LINE);
        
        sb.append(TYPE_OF__METER)
                .append(meterInfo.getString(METER_TYPE))
                .append(SCREEN_NEW_LINE);
        
        sb.append(AMOUNT__TENDERED)
                .append(this.jsonResponse.getDouble(TOTAL_AMOUNT))
                .append(SCREEN_NEW_LINE);
        
        //service charge
        sb.append(SERVICE_CHARGE);
        if (appliedServiceCharge){
            sb.append(Settings.getSettings().retrieveServiceCharge());
        } else {
            sb.append("0.0");
        }
        sb.append(SCREEN_NEW_LINE);
        
        
        sb.append(UNITS_ONBTAINABLE_)
                .append(this.jsonResponse.getDouble(UNITS))
                .append(SCREEN_NEW_LINE);
        
        sb.append(VALUE_OF_UNITS)
                .append(this.jsonResponse.getDouble(UNITS_GROSS))
                .append(SCREEN_NEW_LINE);
        
        sb.append(TAX)
                .append(this.jsonResponse.getDouble(UNITS_TAX))
                .append(SCREEN_NEW_LINE);
        
        sb.append(DEBT_DEDUCTION)
                .append(this.jsonResponse.getDouble(OUTSTANDING_CHARGES_NET))
                .append(SCREEN_NEW_LINE);        
        
        if(!this.jsonResponse.isNull(OUTSTANDING_CHARGES)){
            
            JSONArray outstandingArray = this.jsonResponse.getJSONArray(OUTSTANDING_CHARGES);
            
            if (outstandingArray.length() != 0) {
                JSONObject outstandingDetails = outstandingArray.getJSONObject(0);
                
                sb.append(OUTSTANDING_DESCRIPTION)
                        .append(outstandingDetails.getString(OUTSTANDING_TYPE))
                        .append(SCREEN_NEW_LINE);
                
                sb.append(OUTSTANDING_BALANCE)
                        .append(outstandingDetails.getDouble("currentOutstandingBalance"))
                        .append(SCREEN_NEW_LINE);
                
                sb.append("Debt deducted: ₦")
                        .append(outstandingDetails.getDouble("amountPaying"))
                        .append(SCREEN_NEW_LINE);
                
                sb.append("Percentage deducted: %")
                        .append(outstandingDetails.getDouble("percentPaying"))
                        .append(SCREEN_NEW_LINE);
            }
        }
        
        sb.append("Date: ")
                .append(this.jsonResponse.getString("paymentDate"))
                .append(SCREEN_NEW_LINE);
        
        sb.append("Time: ")
                .append(this.jsonResponse.getString("paymentTime"))
                .append(SCREEN_NEW_LINE);
        
        sb.append("TOKEN: ");
        
        if (this.jsonResponse.getString("token") != null){
            sb.append(this.jsonResponse.getString("token"));
        } else {
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
}
