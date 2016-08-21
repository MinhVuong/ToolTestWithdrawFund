/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataWithdrawFunds;

/**
 *
 * @author Kuti
 */
public class DataPostWithdrawFunds {
    private String caller;
    private DataWithdrawFundsFull data;
    private String signature;

    public DataPostWithdrawFunds() {
    }

    public DataPostWithdrawFunds(String caller, DataWithdrawFundsFull data, String signature) {
        this.caller = caller;
        this.data = data;
        this.signature = signature;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public DataWithdrawFundsFull getData() {
        return data;
    }

    public void setData(DataWithdrawFundsFull data) {
        this.data = data;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    
}
