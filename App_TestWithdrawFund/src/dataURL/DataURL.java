package dataURL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CPU01661-local
 */
public class DataURL {
    private String nameSheet;
    private String url;

    public DataURL() {
    }

    public DataURL(String nameSheet, String url) {
        this.nameSheet = nameSheet;
        this.url = url;
    }

    public String getNameSheet() {
        return nameSheet;
    }

    public void setNameSheet(String nameSheet) {
        this.nameSheet = nameSheet;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
