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
public class DataRowInquireCard {
    private int id;
    private DataPostInquireCard data;
    private int threadNumber;
    private int codeExpect;
    private String codeReal;

    public DataRowInquireCard() {
    }

    public DataRowInquireCard(int id, DataPostInquireCard data, int threadNumber, int code, String codeR ) {
        this.id = id;
        this.data = data;
        this.threadNumber = threadNumber;
        this.codeExpect = code;
        this.codeReal = codeR;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DataPostInquireCard getData() {
        return data;
    }

    public void setData(DataPostInquireCard data) {
        this.data = data;
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

    public void setCodeExpect(int code) {
        this.codeExpect = code;
    }

    public String getCodeReal() {
        return codeReal;
    }

    public void setCodeReal(String codeReal) {
        this.codeReal = codeReal;
    }
    
}
