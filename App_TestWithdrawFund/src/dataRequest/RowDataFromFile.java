/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataRequest;

import com.google.gson.JsonObject;

/**
 *
 * @author CPU01661-local
 */
public class RowDataFromFile {
    private int id;
    private JsonObject data;
    private int thread;
    private String resultExpect;
    private String resultReal;
    private int numReal;
    private String nameAlgorithm;
    public RowDataFromFile() {
//        this.resultReal = "1";
    }

    public RowDataFromFile(int id, JsonObject data, int thread, String resultExpect, String resultReal, int numR, String nameAlgorithm) {
        this.id = id;
        this.data = data;
        this.thread = thread;
        this.resultExpect = resultExpect;
        this.resultReal = resultReal;
        this.numReal = numR;
        this.nameAlgorithm = nameAlgorithm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }

    public int getThread() {
        return thread;
    }

    public void setThread(int thread) {
        this.thread = thread;
    }

    public String getResultExpect() {
        return resultExpect;
    }

    public void setResultExpect(String resultExpect) {
        this.resultExpect = resultExpect;
    }

    public String getResultReal() {
        return resultReal;
    }

    public void setResultReal(String resultReal) {
        this.resultReal = resultReal;
    }

    public int getNumReal() {
        return numReal;
    }

    public void setNumReal(int numReal) {
        this.numReal = numReal;
    }

    public String getNameAlgorithm() {
        return nameAlgorithm;
    }

    public void setNameAlgorithm(String nameAlgorithm) {
        this.nameAlgorithm = nameAlgorithm;
    }
    
}
