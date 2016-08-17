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
public class DataPostInquireCard {
    private String caller;
    private DataInquireCardFull data;
    private String chksum;

    public DataPostInquireCard() {
    }

    public DataPostInquireCard(String caller, DataInquireCardFull data, String chksum) {
        this.caller = caller;
        this.data = data;
        this.chksum = chksum;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public DataInquireCardFull getData() {
        return data;
    }

    public void setData(DataInquireCardFull data) {
        this.data = data;
    }

    public String getChksum() {
        return chksum;
    }

    public void setChksum(String chksum) {
        this.chksum = chksum;
    }
    
}
