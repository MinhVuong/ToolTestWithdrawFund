/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataHash;

import java.util.ArrayList;

/**
 *
 * @author CPU01661-local
 */
public class MyDataHash {
    private ArrayList<DataHash> dataHashs;

    public MyDataHash() {
    }

    public MyDataHash(ArrayList<DataHash> dataHashs) {
        this.dataHashs = dataHashs;
    }

    public ArrayList<DataHash> getDataHashs() {
        return dataHashs;
    }

    public void setDataHashs(ArrayList<DataHash> dataHashs) {
        this.dataHashs = dataHashs;
    }
    
    
    
    public DataHash CheckNameDataIsHash(String nameSheet, String nameData){
        for(DataHash dataH : this.dataHashs){
            if((dataH.getNameApi().equals(nameSheet)) && (dataH.getNameData().equals(nameData)))
                return dataH;
        }
        return null;
    }
}
