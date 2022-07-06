/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mlvmn.apsvendingissueassistant.vending;

/**
 *
 * @author tejum
 */
public class OutstandingCharges {
    private String type;
    private double currentOutstandingBalance;
    private double amountPaying;
    private double percentPaying;

    public OutstandingCharges() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCurrentOutstandingBalance() {
        return currentOutstandingBalance;
    }

    public void setCurrentOutstandingBalance(double currentOutstandingBalance) {
        this.currentOutstandingBalance = currentOutstandingBalance;
    }

    public double getAmountPaying() {
        return amountPaying;
    }

    public void setAmountPaying(double amountPaying) {
        this.amountPaying = amountPaying;
    }

    public double getPercentPaying() {
        return percentPaying;
    }

    public void setPercentPaying(double percentPaying) {
        this.percentPaying = percentPaying;
    }
    
    
}
