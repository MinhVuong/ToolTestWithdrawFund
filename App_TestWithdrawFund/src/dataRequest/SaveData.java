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
    private ArrayList<DataSheet> datas;

    public SaveData() {
    }

    public SaveData(ArrayList<DataSheet> datas) {
        this.datas = datas;
    }

    public ArrayList<DataSheet> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<DataSheet> datas) {
        this.datas = datas;
    }
    
}
