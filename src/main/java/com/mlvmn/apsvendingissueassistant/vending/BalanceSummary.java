/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mlvmn.apsvendingissueassistant.vending;

/**
 *
 * @author tejum
 */
public class BalanceSummary extends AccessPowerVending {

    private long totalCredit;
    private long totalDebit;
    private long currentBalance;

    public BalanceSummary() {
    }

    public long getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(long totalCredit) {
        this.totalCredit = totalCredit;
    }

    public long getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(long totalDebit) {
        this.totalDebit = totalDebit;
    }

    public long getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(long currentBalance) {
        this.currentBalance = currentBalance;
    }
    

}
