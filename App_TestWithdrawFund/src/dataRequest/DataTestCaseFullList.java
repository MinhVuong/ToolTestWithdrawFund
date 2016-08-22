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
public class DataTestCaseFullList {
    private ArrayList<DataTestCaseFull> dataFulls;

    public DataTestCaseFullList() {
    }

    public DataTestCaseFullList(ArrayList<DataTestCaseFull> dataFulls) {
        this.dataFulls = dataFulls;
    }

    public ArrayList<DataTestCaseFull> getDataFulls() {
        return dataFulls;
    }

    public void setDataFulls(ArrayList<DataTestCaseFull> dataFulls) {
        this.dataFulls = dataFulls;
    }
    
}
