/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataInquireCard;

/**
 *
 * @author CPU01661-local
 */
public class DataInquireCardFull {
    private String tid;
    private String tn;
    private String amt;

    public DataInquireCardFull() {
    }

    public DataInquireCardFull(String tid, String tn, String amt) {
        this.tid = tid;
        this.tn = tn;
        this.amt = amt;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }
    
}
