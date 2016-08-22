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

    public RowDataFromFile() {
    }

    public RowDataFromFile(int id, JsonObject data, int thread, String resultExpect, String resultReal) {
        this.id = id;
        this.data = data;
        this.thread = thread;
        this.resultExpect = resultExpect;
        this.resultReal = resultReal;
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
    
}
