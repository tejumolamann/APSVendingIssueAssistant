/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mlvmn.apsvendingissueassistant.vending;

/**
 *
 * @author tejum
 */
public class PaidTransaction {
    private String token;
    private String transactionReference;
    private Meter customerMeterInfo;
    private double totalAmount;
    private double units;
    private double unitsGross;
    private double unitsTax;
    private double unitsNet;
    private OutstandingCharges[] outstandingCharges;
    private String transactionEmail;
    private String transactionGsmNo;
    private String transactionDate;
    private String transactionTime;
    private String paymentDate;
    private String paymentTime;
    private String receiptNo;
    private String disco;
    private String extraData;
    private double serviceCharge;
    private double outstandingChargesNet;

    public double getOutstandingChargesNet() {
        return outstandingChargesNet;
    }

    public void setOutstandingChargesNet(double outstandingChargesNet) {
        this.outstandingChargesNet = outstandingChargesNet;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public PaidTransaction() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public Meter getCustomerMeterInfo() {
        return customerMeterInfo;
    }

    public void setCustomerMeterInfo(Meter customerMeterInfo) {
        this.customerMeterInfo = customerMeterInfo;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }

    public double getUnitsGross() {
        return unitsGross;
    }

    public void setUnitsGross(double unitsGross) {
        this.unitsGross = unitsGross;
    }

    public double getUnitsTax() {
        return unitsTax;
    }

    public void setUnitsTax(double unitsTax) {
        this.unitsTax = unitsTax;
    }

    public double getUnitsNet() {
        return unitsNet;
    }

    public void setUnitsNet(double unitsNet) {
        this.unitsNet = unitsNet;
    }

    public OutstandingCharges[] getOutstandingCharges() {
        return outstandingCharges;
    }

    public void setOutstandingCharges(OutstandingCharges[] outstandingCharges) {
        this.outstandingCharges = outstandingCharges;
    }

    public String getTransactionEmail() {
        return transactionEmail;
    }

    public void setTransactionEmail(String transactionEmail) {
        this.transactionEmail = transactionEmail;
    }

    public String getTransactionGsmNo() {
        return transactionGsmNo;
    }

    public void setTransactionGsmNo(String transactionGsmNo) {
        this.transactionGsmNo = transactionGsmNo;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }
    
}
