/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataHash;

/**
 *
 * @author CPU01661-local
 */
public class DataHash {
    private String nameApi;
    private String nameData;
    private int numColumn;
    private String algorithm;
    private String key;
    private String iv;

    public DataHash() {
    }

    public DataHash(String nameApi, int numColumn, String nameData, String algorithm, String key, String iv) {
        this.nameApi = nameApi;
        this.numColumn = numColumn;
        this.nameData = nameData;
        this.algorithm = algorithm;
        this.key = key;
        this.iv = iv;
    }

    public String getNameApi() {
        return nameApi;
    }

    public void setNameApi(String nameApi) {
        this.nameApi = nameApi;
    }

    public int getNumColumn() {
        return numColumn;
    }

    public void setNumColumn(int numColumn) {
        this.numColumn = numColumn;
    }

    public String getNameData() {
        return nameData;
    }

    public void setNameData(String nameData) {
        this.nameData = nameData;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
    
}
