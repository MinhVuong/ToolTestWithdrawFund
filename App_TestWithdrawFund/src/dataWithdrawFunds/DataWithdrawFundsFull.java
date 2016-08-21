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
public class DataWithdrawFundsFull {
    private String tid;
    private String amt;

    public DataWithdrawFundsFull() {
    }

    public DataWithdrawFundsFull(String tid, String amt) {
        this.tid = tid;
        this.amt = amt;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }
    
}
