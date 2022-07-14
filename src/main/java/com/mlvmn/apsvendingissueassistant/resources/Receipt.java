/*
 * This class is for the presentation of successful data response from API calls.
 * The presentation is in human readable form, for display on the screen, text 
 * transafer and printing on paper receipts.
 */
package com.mlvmn.apsvendingissueassistant.resources;

import com.mlvmn.apsvendingissueassistant.printing.PrintingService;
import com.mlvmn.apsvendingissueassistant.vending.BalanceSummary;
import com.mlvmn.apsvendingissueassistant.vending.Meter;
import com.mlvmn.apsvendingissueassistant.vending.NewTransaction;
import com.mlvmn.apsvendingissueassistant.vending.OutstandingCharges;
import com.mlvmn.apsvendingissueassistant.vending.PaidTransaction;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author tejum
 */
public class Receipt {
    
    private static final String SCREEN_NEW_LINE = "\n-----\n";
    
    /*Labels*/
    private static final String METER__NUMBER = "Meter Number: ";
    private static final String TRANSACTION__REFERENCE_ = "Transaction Reference: ";
    private static final String PHONE__NUMBER_ = "Phone Number: ";
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
    private static final String DEBT_SERVICING_DETAILS = "DEBT SERVICING DETAILS";
    
    
    private NewTransaction newTransaction;
    private Meter meter;
    private BalanceSummary balSummary;
    private PaidTransaction paidTransaction;
    
    public Receipt(Meter aMeter){
        this.meter = aMeter;
    }
    
    public Receipt(BalanceSummary balance){
        this.balSummary = balance;
    }

    public Receipt(PaidTransaction paidTransaction) {
        this.paidTransaction = paidTransaction;
    }

    public Receipt(NewTransaction newTransaction) {
        this.newTransaction = newTransaction;
    }
    
    public String sendMeterDetailsToScreen(){
        StringBuilder sb = new StringBuilder();
        
        sb.append(METER__NUMBER);
        sb.append(this.meter.getMeterNo());
        
        sb.append(SCREEN_NEW_LINE);
        
        sb.append(METER__REGISTERED_TO);
        sb.append(this.meter.getCustomerName());
        
        sb.append(SCREEN_NEW_LINE);
        
        sb.append(CUSTOMER__ADDRESS);
        sb.append(this.meter.getAddress());
        
        sb.append(SCREEN_NEW_LINE);
        
        sb.append("Registered Phone Number: ");
        sb.append(this.meter.getPhoneNo());
        
        sb.append(SCREEN_NEW_LINE);
        
        sb.append("Registered Email: ");
        sb.append(this.meter.getEmail());
        
        sb.append(SCREEN_NEW_LINE);
        
        sb.append("Type of Meter: ");
        sb.append(this.meter.getMeterType());
        
        sb.append(SCREEN_NEW_LINE);
        
        sb.append("Tariff: ");
        sb.append(this.meter.getUnitRate());
        
        sb.append(SCREEN_NEW_LINE);
        
        sb.append("Debt Balance: ₦");
        sb.append(this.meter.getAccountBalance());
        
        sb.append(SCREEN_NEW_LINE);
        
        sb.append("Minimum Vend Payment: ₦");
        sb.append(this.meter.getMinimumPurchase());
        
        return sb.toString();
    }
    
    /**
     * This method formats the JSON response received from checking a vendor's 
     * wallet balance into human understandable format.
     * @return String
     */
    public String sendBalanceSummaryToScreen(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("Total Credits: ₦");
        sb.append(new BigDecimal(this.balSummary.getTotalCredit()).toPlainString());
        sb.append(SCREEN_NEW_LINE);
        sb.append("Total Amount of Vends: ₦");
        sb.append(new BigDecimal(this.balSummary.getTotalDebit()).toPlainString());
        sb.append(SCREEN_NEW_LINE);
        sb.append("Current Balance: ₦");
        sb.append(this.balSummary.getCurrentBalance());
        
        return sb.toString();
    }

    /**
     * 
     * @return 
     */
    public String sendNewTransactionToScreen() {
        StringBuilder sb = new StringBuilder();
        
        NewTransaction aNewTransaction = this.newTransaction;
        
        Meter meterInfo = aNewTransaction.getCustomerMeterInfo();
        
        sb.append(METER__NUMBER)
                .append(meterInfo.getMeterNo())
                .append(SCREEN_NEW_LINE);
        
        sb.append(METER__REGISTERED_TO)
                .append(meterInfo.getCustomerName())
                .append(SCREEN_NEW_LINE);
        
        sb.append(CUSTOMER__ADDRESS)
                .append(meterInfo.getAddress())
                .append(SCREEN_NEW_LINE);
        
        sb.append(PHONE__NUMBER_)
                .append(aNewTransaction.getTransactionGsmNo())
                .append(SCREEN_NEW_LINE);
        
        sb.append(TRANSACTION__REFERENCE_)
                .append(aNewTransaction.getTransactionReference())
                .append(SCREEN_NEW_LINE);
        
        sb.append(TYPE_OF__METER)
                .append(meterInfo.getMeterType())
                .append(SCREEN_NEW_LINE);
        
        sb.append(AMOUNT__TENDERED)
                .append(aNewTransaction.getTotalAmount())
                .append(SCREEN_NEW_LINE);
        
        //service charge
        sb.append(SERVICE_CHARGE)
                .append(aNewTransaction.getServiceCharge())
                .append(SCREEN_NEW_LINE);
        
        
        sb.append(UNITS_ONBTAINABLE_)
                .append(aNewTransaction.getUnits())
                .append(SCREEN_NEW_LINE);
        
        sb.append(VALUE_OF_UNITS)
                .append(aNewTransaction.getUnitsGross())
                .append(SCREEN_NEW_LINE);
        
        sb.append(TAX)
                .append(aNewTransaction.getUnitsTax())
                .append(SCREEN_NEW_LINE);
        
        if(Objects.nonNull(aNewTransaction.getOutstandingCharges())){
            
            sb.append(DEBT_SERVICING_DETAILS).append(SCREEN_NEW_LINE);
            
            OutstandingCharges[] outstandingChargesArray = aNewTransaction.getOutstandingCharges();
            
            for (OutstandingCharges outstandingCharges : outstandingChargesArray) {
                
                sb.append(OUTSTANDING_DESCRIPTION)
                        .append(outstandingCharges.getType())
                        .append(SCREEN_NEW_LINE);
                
                sb.append(OUTSTANDING_BALANCE)
                        .append(outstandingCharges.getCurrentOutstandingBalance())
                        .append(SCREEN_NEW_LINE);
                
                sb.append("Debt deducted: ₦")
                        .append(outstandingCharges.getAmountPaying())
                        .append(SCREEN_NEW_LINE);
                
                sb.append("Percentage deducted: %")
                        .append(outstandingCharges.getPercentPaying())
                        .append(SCREEN_NEW_LINE);
            }
        }
        
        sb.append("Date: ")
                .append(aNewTransaction.getTransactionDate())
                .append(SCREEN_NEW_LINE);
        
        sb.append("Time: ")
                .append(aNewTransaction.getTransactionTime())
                .append(SCREEN_NEW_LINE);
        
        return sb.toString();
    }

    /**
     * @return 
     */
    public String sendPaidTransactionToScreen() {
        StringBuilder sb = new StringBuilder();
        
        Meter meterInfo = this.paidTransaction.getCustomerMeterInfo();
        
        sb.append(METER__NUMBER)
                .append(meterInfo.getMeterNo())
                .append(SCREEN_NEW_LINE);
        
        sb.append(METER__REGISTERED_TO)
                .append(meterInfo.getCustomerName())
                .append(SCREEN_NEW_LINE);
        
        sb.append(CUSTOMER__ADDRESS)
                .append(meterInfo.getAddress())
                .append(SCREEN_NEW_LINE);
        
        sb.append(PHONE__NUMBER_)
                .append(this.paidTransaction.getTransactionGsmNo())
                .append(SCREEN_NEW_LINE);
        
        sb.append(TRANSACTION__REFERENCE_)
                .append(this.paidTransaction.getTransactionReference())
                .append(SCREEN_NEW_LINE);
        
        sb.append("Receipt No: ")
                .append(this.paidTransaction.getReceiptNo())
                .append(SCREEN_NEW_LINE);
        
        sb.append(TYPE_OF__METER)
                .append(meterInfo.getMeterType())
                .append(SCREEN_NEW_LINE);
        
        sb.append(AMOUNT__TENDERED)
                .append(this.paidTransaction.getTotalAmount())
                .append(SCREEN_NEW_LINE);
        
        //service charge
        sb.append(SERVICE_CHARGE)
                .append(this.paidTransaction.getServiceCharge())
                .append(SCREEN_NEW_LINE);        
        
        sb.append(UNITS_ONBTAINABLE_)
                .append(this.paidTransaction.getUnits())
                .append(SCREEN_NEW_LINE);
        
        sb.append(VALUE_OF_UNITS)
                .append(this.paidTransaction.getUnitsGross())
                .append(SCREEN_NEW_LINE);
        
        sb.append(TAX)
                .append(this.paidTransaction.getUnitsTax())
                .append(SCREEN_NEW_LINE);      
        
        if(Objects.nonNull(this.paidTransaction.getOutstandingCharges())){
            
            sb.append(DEBT_SERVICING_DETAILS).append(SCREEN_NEW_LINE);
            
            OutstandingCharges[] outstandingChargesArray = this.paidTransaction.getOutstandingCharges();
            
            for (OutstandingCharges outstandingCharges : outstandingChargesArray) {
                
                sb.append(OUTSTANDING_DESCRIPTION)
                        .append(outstandingCharges.getType())
                        .append(SCREEN_NEW_LINE);
                
                sb.append(OUTSTANDING_BALANCE)
                        .append(outstandingCharges.getCurrentOutstandingBalance())
                        .append(SCREEN_NEW_LINE);
                
                sb.append("Debt deducted: ₦")
                        .append(outstandingCharges.getAmountPaying())
                        .append(SCREEN_NEW_LINE);
                
                sb.append("Percentage deducted: %")
                        .append(outstandingCharges.getPercentPaying())
                        .append(SCREEN_NEW_LINE);
            }
        }
        
        sb.append("Date: ")
                .append(this.paidTransaction.getPaymentDate())
                .append(SCREEN_NEW_LINE);
        
        sb.append("Time: ")
                .append(this.paidTransaction.getPaymentTime())
                .append(SCREEN_NEW_LINE);
        
        sb.append("TOKEN: ");
        
        if (Objects.nonNull(this.paidTransaction.getToken())){
            sb.append(this.paidTransaction.getToken());
        } else {
            sb.append("\n");
        }
        
        return sb.toString();
    }    
    
    public void sendPaidTransactionToPrinter(String printerName) throws IOException{
        String payLoad = new String();
        
        PaidTransaction aPaidTransaction = this.paidTransaction;
        
        String astrikLine = "******************************";
        
        String paymentDate = aPaidTransaction.getPaymentDate().concat(" ").concat(aPaidTransaction.getPaymentTime());
        String receiptNo = String.valueOf(aPaidTransaction.getReceiptNo());
        String name = aPaidTransaction.getCustomerMeterInfo().getCustomerName().trim();
        String meterNo = aPaidTransaction.getCustomerMeterInfo().getMeterNo();
        String accountNo = aPaidTransaction.getCustomerMeterInfo().getCustomerNo();
        String paymentMode;
        String total = String.valueOf(aPaidTransaction.getTotalAmount());
        String totalWords;
        String paidArrears;
        String valueOfUnits = String.valueOf(aPaidTransaction.getUnitsGross());
        String meterType = aPaidTransaction.getCustomerMeterInfo().getMeterType();
        String tarrif = String.valueOf(aPaidTransaction.getUnitsNet());
        String amountOfUnits = String.valueOf(aPaidTransaction.getUnits());
        String tax = String.valueOf(aPaidTransaction.getUnitsTax());
        String balance;
        
        if(Objects.nonNull(aPaidTransaction.getOutstandingCharges())){
            paidArrears = String.valueOf(aPaidTransaction.getOutstandingCharges()[0].getAmountPaying());
            balance = String.valueOf(aPaidTransaction.getOutstandingCharges()[0].getCurrentOutstandingBalance());
        } else {
            balance = paidArrears = "0.00";
        }
        
        //For prepaid meter vends
        if(aPaidTransaction.getCustomerMeterInfo().getMeterNo().length() >= 11){
            paymentMode = "PREPAID";            
            
            payLoad = "AEDC"
                    + "ABUJA ELECTRICITY DISTRIBUTION COMPANY"
                    + "www.abaujaelectricity.com"
                    + "TEL: 08152151515 / 08152141414"
                    + astrikLine
                    + "PAYMENT RECEIPT ORIGINAL"
                    + "PAYMENT DATE: " + paymentDate
                    + "RECEIPT NO: " + receiptNo
                    + "NAME: " + name
                    + "Meter No:" + meterNo
                    + "Account No: " + accountNo
                    + "Payment Mode: " + paymentMode
                    + "Vendor: PAYKIOSK"
                    + "Helplines: 0811 115 2201 / 0909 960 3670"
                    + "Total: " + total
                    + "Total in words: "
                    + astrikLine
                    + "TOKENS"
                    + astrikLine
                    + aPaidTransaction.getToken()
                    + "Paid Arrears: " + paidArrears
                    + "Value of units: " + valueOfUnits
                    + "Meter type: " + meterType
                    + "Tarrif: " + tarrif
                    + "Amount of units: " + amountOfUnits
                    + "Tax: " + tax
                    + "Balance: " + balance
                    + "THANK YOU"
                    + astrikLine;
        }
        
        //For postpaid meter vends
        if(aPaidTransaction.getCustomerMeterInfo().getMeterNo().length() < 11 && aPaidTransaction.getToken() == null){
            paymentMode = "POSTPAID";
        }
        
        //PrintingService.printToThermalPrinter(printerName, payLoad);
    }
    
}
