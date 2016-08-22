/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataRequest;

import java.util.ArrayList;

/**
 *
 * @author CPU01661-local
 */
public class DataTestCaseFull {
    private ArrayList<RowDataFromFile> datas;
    private String url;

    public DataTestCaseFull() {
    }

    public DataTestCaseFull(ArrayList<RowDataFromFile> datas, String url) {
        this.datas = datas;
        this.url = url;
    }

    public ArrayList<RowDataFromFile> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<RowDataFromFile> datas) {
        this.datas = datas;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
