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
    private ArrayList<DataInput> listDataInput;
    private ArrayList<DataInputLevel2> dataInputLevel2;

    public DataSheet() {
    }

    public DataSheet(ArrayList<RowDataFromFile> datas, ArrayList<NameDynamic> nameDynamic, ArrayList<DataInput> listDataInput, ArrayList<DataInputLevel2> dataInputLevel2) {
        this.datas = datas;
        this.nameDynamic = nameDynamic;
        this.listDataInput = listDataInput;
        this.dataInputLevel2 = dataInputLevel2;
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

    public ArrayList<DataInput> getListDataInput() {
        return listDataInput;
    }

    public void setListDataInput(ArrayList<DataInput> listDataInput) {
        this.listDataInput = listDataInput;
    }

    public ArrayList<DataInputLevel2> getDataInputLevel2() {
        return dataInputLevel2;
    }

    public void setDataInputLevel2(ArrayList<DataInputLevel2> dataInputLevel2) {
        this.dataInputLevel2 = dataInputLevel2;
    }
        
    
}
