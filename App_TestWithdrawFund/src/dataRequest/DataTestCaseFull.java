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
    private DataSheet datas;
    private DataURL dataUrl;

    public DataTestCaseFull() {
    }

    public DataTestCaseFull(DataSheet datas, DataURL url) {
        this.datas = datas;
        this.dataUrl = url;
    }

    public DataSheet getDatas() {
        return datas;
    }

    public void setDatas(DataSheet datas) {
        this.datas = datas;
    }

    public DataURL getDataURL() {
        return dataUrl;
    }

    public void setDataURL(DataURL url) {
        this.dataUrl = url;
    }

    public DataURL getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(DataURL dataUrl) {
        this.dataUrl = dataUrl;
    }    
}
