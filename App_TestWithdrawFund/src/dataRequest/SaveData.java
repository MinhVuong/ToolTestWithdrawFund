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
public class SaveData {
    private ArrayList<RowDataFromFile> datas;

    public SaveData() {
    }

    public SaveData(ArrayList<RowDataFromFile> datas) {
        this.datas = datas;
    }

    public ArrayList<RowDataFromFile> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<RowDataFromFile> datas) {
        this.datas = datas;
    }
    
}
