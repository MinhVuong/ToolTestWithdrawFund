/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataRequest;

import dataURL.DataURL;
import java.util.ArrayList;

/**
 *
 * @author CPU01661-local
 */
public class DataTestCaseFull {
    private ArrayList<RowDataFromFile> datas;
    private DataURL dataUrl;

    public DataTestCaseFull() {
    }

    public DataTestCaseFull(ArrayList<RowDataFromFile> datas, DataURL url) {
        this.datas = datas;
        this.dataUrl = url;
    }

    public ArrayList<RowDataFromFile> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<RowDataFromFile> datas) {
        this.datas = datas;
    }

    public DataURL getDataURL() {
        return dataUrl;
    }

    public void setDataURL(DataURL url) {
        this.dataUrl = url;
    }
    
}
