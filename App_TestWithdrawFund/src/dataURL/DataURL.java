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
    private String acceptType;
    private String contentType;
    private String rawData;

    public DataURL() {
    }

    public DataURL(String nameSheet, String url, String acceptType, String contentType, String rawData) {
        this.nameSheet = nameSheet;
        this.url = url;
        this.acceptType = acceptType;
        this.contentType = contentType;
        this.rawData = rawData;
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

    public String getAcceptType() {
        return acceptType;
    }

    public void setAcceptType(String acceptType) {
        this.acceptType = acceptType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }
    
}
