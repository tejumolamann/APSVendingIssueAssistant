/*
 * This class is for the presentation of successful data response from API calls.
 * The presentation is in human readable form, for display on the screen, text 
 * transafer and printing on paper receipts.
 */
package com.mlvmn.apsvendingissueassistant.resources;

import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;
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

    public Receipt(Meter aMeter) {
        this.meter = aMeter;
    }

    public Receipt(BalanceSummary balance) {
        this.balSummary = balance;
    }

    public Receipt(PaidTransaction paidTransaction) {
        this.paidTransaction = paidTransaction;
    }

    public Receipt(NewTransaction newTransaction) {
        this.newTransaction = newTransaction;
    }

    public String sendMeterDetailsToScreen() {
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
     *
     * @return String
     */
    public String sendBalanceSummaryToScreen() {
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

        if (Objects.nonNull(aNewTransaction.getOutstandingCharges())) {

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

        if (Objects.nonNull(this.paidTransaction.getOutstandingCharges())) {

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

        if (!this.paidTransaction.getToken().isEmpty()) {
            sb.append("TOKEN: ");
            sb.append(this.paidTransaction.getToken());
        } else {
            sb.append("\n");
        }

        return sb.toString();
    }

    public void sendPaidTransactionToPrinter(String printerName) throws IOException {
        PrintingService printer = new PrintingService(printerName);

        PaidTransaction aPaidTransaction = this.paidTransaction;

        String astrikLine = "******************************";

        String paymentDate = aPaidTransaction.getPaymentDate().concat(" ").concat(aPaidTransaction.getPaymentTime());
        String receiptNo = String.valueOf(aPaidTransaction.getReceiptNo());
        String name = aPaidTransaction.getCustomerMeterInfo().getCustomerName().trim();
        String meterNo = aPaidTransaction.getCustomerMeterInfo().getMeterNo();
        String accountNo = aPaidTransaction.getCustomerMeterInfo().getCustomerNo();
        String total = String.valueOf(aPaidTransaction.getTotalAmount());
        String valueOfUnits = String.valueOf(aPaidTransaction.getUnitsGross());
        String tax = String.valueOf(aPaidTransaction.getUnitsTax());

        String tender = String.valueOf(
                aPaidTransaction.getTotalAmount() + aPaidTransaction.getServiceCharge()
        );

        Style header = new Style();
        header.setBold(true);
        header.setFontSize(Style.FontSize._3, Style.FontSize._3);
        header.setJustification(EscPosConst.Justification.Center);
        printer.sendToThermalPrinterOnNewLine(header, "AEDC");

        Style subHeader = new Style();
        subHeader.setJustification(EscPosConst.Justification.Center);
        printer.sendToThermalPrinterOnNewLine(subHeader, "ABUJA ELECTRICITY DISTRIBUTION \nCOMPANY");
        printer.sendToThermalPrinterOnNewLine(subHeader, "www.abaujaelectricity.com");
        printer.sendToThermalPrinterOnNewLine(subHeader, "TEL: 08152151515 / 08152141414");

        Style asterickStyle = new Style();
        asterickStyle.setBold(true);
        asterickStyle.setJustification(EscPosConst.Justification.Center);
        printer.sendToThermalPrinterOnNewLine(asterickStyle, astrikLine);

        printer.sendToThermalPrinterOnNewLine(subHeader, "PAYMENT RECEIPT ORIGINAL");
        printer.sendWhiteLinesToPrinter(1);

        Style labelStyle = new Style();
        labelStyle.setJustification(EscPosConst.Justification.Left_Default);
        printer.setPrintStyle(labelStyle);
        printer.sendToThermalPrinterOnNewLine("Payment Date: ");

        Style valueStyle = new Style();
        valueStyle.setJustification(EscPosConst.Justification.Left_Default);
        valueStyle.setBold(true);
        printer.setPrintStyle(valueStyle);
        printer.sendToThermalPrinterOnSameLine(paymentDate);
        printer.sendWhiteLinesToPrinter(1);

        printer.setPrintStyle(labelStyle);
        printer.sendToThermalPrinterOnSameLine("Receipt No: ");
        printer.setPrintStyle(valueStyle);
        printer.sendToThermalPrinterOnSameLine(receiptNo);
        printer.sendWhiteLinesToPrinter(1);

        printer.setPrintStyle(labelStyle);
        printer.sendToThermalPrinterOnSameLine("Name: ");
        printer.setPrintStyle(valueStyle);
        printer.sendToThermalPrinterOnSameLine(name);
        printer.sendWhiteLinesToPrinter(1);

        printer.setPrintStyle(labelStyle);
        printer.sendToThermalPrinterOnSameLine("Address: ");
        printer.setPrintStyle(valueStyle);
        printer.sendToThermalPrinterOnSameLine(aPaidTransaction.getCustomerMeterInfo().getAddress().trim());
        printer.sendWhiteLinesToPrinter(1);

        printer.setPrintStyle(labelStyle);
        printer.sendToThermalPrinterOnSameLine("Meter No: ");
        printer.setPrintStyle(valueStyle);
        printer.sendToThermalPrinterOnSameLine(meterNo);
        printer.sendWhiteLinesToPrinter(1);

        printer.setPrintStyle(labelStyle);
        printer.sendToThermalPrinterOnSameLine("Account No: ");
        printer.setPrintStyle(valueStyle);
        printer.sendToThermalPrinterOnSameLine(accountNo);
        printer.sendWhiteLinesToPrinter(1);

//        printer.setPrintStyle(labelStyle);
//        printer.sendToThermalPrinterOnSameLine("Payment Type: ");
//        printer.setPrintStyle(valueStyle);
//        printer.sendToThermalPrinterOnSameLine(paymentMode);
//        printer.sendWhiteLinesToPrinter(2);

        printer.setPrintStyle(labelStyle);
        printer.sendToThermalPrinterOnSameLine("Vendor: ");
        printer.setPrintStyle(valueStyle);
        printer.sendToThermalPrinterOnSameLine("PayKiosk");
        printer.sendWhiteLinesToPrinter(1);

        printer.setPrintStyle(labelStyle);
        printer.sendToThermalPrinterOnSameLine("Helplines: ");
        printer.setPrintStyle(valueStyle);
        printer.sendToThermalPrinterOnSameLine("\n0811 115 2201 / 0909 960 3670");
        printer.sendWhiteLinesToPrinter(2);

        printer.setPrintStyle(labelStyle);
        printer.sendToThermalPrinterOnSameLine("Total Paid: =N=");
        printer.setPrintStyle(valueStyle);
        printer.sendToThermalPrinterOnSameLine(tender);
        printer.sendWhiteLinesToPrinter(1);

        Style tokenStyle = new Style();
        tokenStyle.setJustification(EscPosConst.Justification.Center);
        tokenStyle.setBold(true);
        tokenStyle.setFontSize(Style.FontSize._2, Style.FontSize._2);

        if (!aPaidTransaction.getToken().isEmpty()) {
            printer.sendToThermalPrinterOnNewLine(asterickStyle, astrikLine);
            printer.sendToThermalPrinterOnNewLine(subHeader, "TOKEN");
            printer.sendToThermalPrinterOnNewLine(asterickStyle, astrikLine);

            printer.sendToThermalPrinterOnNewLine(tokenStyle, aPaidTransaction.getToken());
            printer.sendWhiteLinesToPrinter(1);
        } else {
            printer.sendToThermalPrinterOnNewLine(asterickStyle, astrikLine);
            printer.sendToThermalPrinterOnNewLine(tokenStyle, "BILL PAID");
            printer.sendToThermalPrinterOnNewLine(asterickStyle, astrikLine);
            printer.sendWhiteLinesToPrinter(1);
        }

        printer.setPrintStyle(labelStyle);
        printer.sendToThermalPrinterOnSameLine("Paid Arrears: =N=");
        printer.setPrintStyle(valueStyle);
        printer.sendToThermalPrinterOnSameLine(String.valueOf(aPaidTransaction.getOutstandingChargesNet()));
        printer.sendWhiteLinesToPrinter(1);

        if (!Objects.isNull(aPaidTransaction.getOutstandingCharges())) {
            printer.setPrintStyle(labelStyle);
            printer.sendToThermalPrinterOnSameLine("Percentage Arrears: ");
            printer.setPrintStyle(valueStyle);
            printer.sendToThermalPrinterOnSameLine(String.valueOf(aPaidTransaction.getOutstandingCharges()[0].getPercentPaying()));
            printer.sendWhiteLinesToPrinter(1);
            
            printer.setPrintStyle(labelStyle);
            printer.sendToThermalPrinterOnSameLine("Arrears: ");
            printer.setPrintStyle(valueStyle);
            printer.sendToThermalPrinterOnSameLine(aPaidTransaction.getOutstandingCharges()[0].getType());
            printer.sendWhiteLinesToPrinter(1);
        }

        printer.setPrintStyle(labelStyle);
        printer.sendToThermalPrinterOnSameLine("Debt Balance: =N=");
        printer.setPrintStyle(valueStyle);
        printer.sendToThermalPrinterOnSameLine(String.valueOf(aPaidTransaction.getCustomerMeterInfo().getAccountBalance()));
        printer.sendWhiteLinesToPrinter(1);

        printer.setPrintStyle(labelStyle);
        printer.sendToThermalPrinterOnSameLine("Total: =N=");
        printer.setPrintStyle(valueStyle);
        printer.sendToThermalPrinterOnSameLine(total);
        printer.sendWhiteLinesToPrinter(1);

        printer.setPrintStyle(labelStyle);
        printer.sendToThermalPrinterOnSameLine("Service Charge: =N=");
        printer.setPrintStyle(valueStyle);
        printer.sendToThermalPrinterOnSameLine(String.valueOf(aPaidTransaction.getServiceCharge()));
        printer.sendWhiteLinesToPrinter(1);

        if (aPaidTransaction.getUnitsGross() > 0.0) {
            printer.setPrintStyle(labelStyle);
            printer.sendToThermalPrinterOnSameLine("Value of units: ");
            printer.setPrintStyle(valueStyle);
            printer.sendToThermalPrinterOnSameLine(valueOfUnits);
            printer.sendWhiteLinesToPrinter(1);
        }

        if (aPaidTransaction.getUnitsTax() > 0.0) {
            printer.setPrintStyle(labelStyle);
            printer.sendToThermalPrinterOnSameLine("Tax :");
            printer.setPrintStyle(valueStyle);
            printer.sendToThermalPrinterOnSameLine(tax);
            printer.sendWhiteLinesToPrinter(1);
        }

        printer.setPrintStyle(labelStyle);
        printer.sendToThermalPrinterOnSameLine("Billing: ");
        printer.setPrintStyle(valueStyle);
        printer.sendToThermalPrinterOnSameLine(aPaidTransaction.getCustomerMeterInfo().getMeterType());
        printer.sendWhiteLinesToPrinter(1);

        if (aPaidTransaction.getUnits() > 0.0) {
            printer.setPrintStyle(labelStyle);
            printer.sendToThermalPrinterOnSameLine("Units: ");
            printer.setPrintStyle(valueStyle);
            printer.sendToThermalPrinterOnSameLine(String.valueOf(aPaidTransaction.getUnits()));
            printer.sendWhiteLinesToPrinter(1);
        }

        if (aPaidTransaction.getCustomerMeterInfo().getUnitRate() > 0.0) {
            printer.setPrintStyle(labelStyle);
            printer.sendToThermalPrinterOnSameLine("Per per unit: ");
            printer.setPrintStyle(valueStyle);
            printer.sendToThermalPrinterOnSameLine("N" + String.valueOf(aPaidTransaction.getCustomerMeterInfo().getUnitRate()));
            printer.sendWhiteLinesToPrinter(1);
        }

        printer.sendWhiteLinesToPrinter(1);
        printer.sendToThermalPrinterOnNewLine(subHeader, "THANK YOU");
        printer.sendToThermalPrinterOnNewLine(asterickStyle, astrikLine);

        printer.sendWhiteLinesToPrinter(3);
        printer.closePrintConnection();
    }

}
