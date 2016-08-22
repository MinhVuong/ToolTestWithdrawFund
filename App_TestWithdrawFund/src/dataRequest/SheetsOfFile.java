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
public class SheetsOfFile {
    private ArrayList<String> sheets ;

    public SheetsOfFile() {
    }

    public SheetsOfFile(ArrayList<String> sheets) {
        this.sheets = sheets;
    }

    public ArrayList<String> getSheets() {
        return sheets;
    }

    public void setSheets(ArrayList<String> sheets) {
        this.sheets = sheets;
    }
    
}
