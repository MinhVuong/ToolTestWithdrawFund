/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataWithdrawFunds;

import dataInquireCard.DataPostInquireCard;

/**
 *
 * @author Kuti
 */
public class DataRowWithdrawFunds {
    private int id;
    private DataPostInquireCard dataPostInquireCard;
    private DataPostWithdrawFunds dataWithdrawFunds;
    private int threadNumber;
    private int codeExpect;
    private String codeReal;

    public DataRowWithdrawFunds() {
    }

    public DataRowWithdrawFunds(int id, DataPostInquireCard dataPostInquireCard, DataPostWithdrawFunds dataWithdrawFunds, int threadNumber, int codeExpect, String codeReal) {
        this.id = id;
        this.dataPostInquireCard = dataPostInquireCard;
        this.dataWithdrawFunds = dataWithdrawFunds;
        this.threadNumber = threadNumber;
        this.codeExpect = codeExpect;
        this.codeReal = codeReal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DataPostInquireCard getDataPostInquireCard() {
        return dataPostInquireCard;
    }

    public void setDataPostInquireCard(DataPostInquireCard dataPostInquireCard) {
        this.dataPostInquireCard = dataPostInquireCard;
    }

    public DataPostWithdrawFunds getDataWithdrawFunds() {
        return dataWithdrawFunds;
    }

    public void setDataWithdrawFunds(DataPostWithdrawFunds dataWithdrawFunds) {
        this.dataWithdrawFunds = dataWithdrawFunds;
    }

    public int getThreadNumber() {
        return threadNumber;
    }

    public void setThreadNumber(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    public int getCodeExpect() {
        return codeExpect;
    }

    public void setCodeExpect(int codeExpect) {
        this.codeExpect = codeExpect;
    }

    public String getCodeReal() {
        return codeReal;
    }

    public void setCodeReal(String codeReal) {
        this.codeReal = codeReal;
    }
    
}
