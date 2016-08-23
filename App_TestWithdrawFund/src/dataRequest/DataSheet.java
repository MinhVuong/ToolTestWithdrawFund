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
public class DataSheet {
    private ArrayList<RowDataFromFile> datas;
    private ArrayList<NameDynamic> nameDynamic;
    private ArrayList<String> nameRequest;

    public DataSheet() {
    }

    public DataSheet(ArrayList<RowDataFromFile> datas, ArrayList<NameDynamic> nameDynamic, ArrayList<String> nameRequest) {
        this.datas = datas;
        this.nameDynamic = nameDynamic;
        this.nameRequest = nameRequest;
    }

    public ArrayList<RowDataFromFile> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<RowDataFromFile> datas) {
        this.datas = datas;
    }

    public ArrayList<NameDynamic> getNameDynamic() {
        return nameDynamic;
    }

    public void setNameDynamic(ArrayList<NameDynamic> nameDynamic) {
        this.nameDynamic = nameDynamic;
    }

    public ArrayList<String> getNameRequest() {
        return nameRequest;
    }

    public void setNameRequest(ArrayList<String> nameRequest) {
        this.nameRequest = nameRequest;
    }
    
}
