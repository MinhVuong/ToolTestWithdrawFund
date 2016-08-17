/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataInquireCard;

import java.util.ArrayList;

/**
 *
 * @author CPU01661-local
 */
public class SaveDataPostInquiredCard {
    private ArrayList<DataRowInquireCard> datas;

    public SaveDataPostInquiredCard() {
    }

    public SaveDataPostInquiredCard(ArrayList<DataRowInquireCard> datas) {
        this.datas = datas;
    }

    public ArrayList<DataRowInquireCard> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<DataRowInquireCard> datas) {
        this.datas = datas;
    }
    
}
