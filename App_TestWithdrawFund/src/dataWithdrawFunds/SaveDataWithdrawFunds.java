/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataWithdrawFunds;

import java.util.ArrayList;

/**
 *
 * @author Kuti
 */
public class SaveDataWithdrawFunds {
    private ArrayList<DataRowWithdrawFunds> datas;

    public SaveDataWithdrawFunds() {
    }

    public SaveDataWithdrawFunds(ArrayList<DataRowWithdrawFunds> datas) {
        this.datas = datas;
    }

    public ArrayList<DataRowWithdrawFunds> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<DataRowWithdrawFunds> datas) {
        this.datas = datas;
    }
    
}
