/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_testwithdrawfund;

import FileHelper.ExcelHelper;
import callAPIHelper.CallApiHelper;
import com.google.gson.Gson;
import dataRequest.DataTestCaseFullList;
import dataRequest.RowDataFromFile;
import dataRequest.SaveData;
import dataRequest.SheetsOfFile;
import dataURL.URLs;
import java.io.IOException;

/**
 *
 * @author CPU01661-local
 */
public class App_TestWithdrawFund {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Gson gson = new Gson();
        ExcelHelper excelH = new ExcelHelper();
        DataTestCaseFullList dataFulls = excelH.ReadDataFromFileExcel("test.xlsx");
        
        
//        SaveDataPostInquiredCard saveDataInquireCard = excelH.ReadFileAPIInquireCard("test.xlsx");
//        System.out.println("data file: " + gson.toJson(saveDataInquireCard));
//        
//        CallApiHelper caller = new CallApiHelper();
//        caller.RunTestApiInquireCard(saveDataInquireCard);
//        
//        System.out.println("data file after run test: " + gson.toJson(saveDataInquireCard));
//        excelH.WriteResultApiInquireCardToFile("test.xlsx", saveDataInquireCard);
//
//        SaveDataWithdrawFunds saveData = excelH.ReadFileAPIWithdrawCard("test.xlsx");
//        CallApiHelper caller = new CallApiHelper();
//        caller.RunTestApiWithdrawFunds(saveData);
//        excelH.WriteResultApiWithdrawFunds("test.xlsx", saveData);
    }
}
