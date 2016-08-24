/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_testwithdrawfund;

import FileHelper.ExcelHelper;
import callAPIHelper.CallApiHelper;
import callAPIHelper.ResultApi;
import com.google.gson.Gson;
import dataRequest.DataTestCaseFullList;
import dataRequest.RowDataFromFile;
import dataRequest.SaveData;
import dataRequest.SheetsOfFile;
import dataURL.URLs;
import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CPU01661-local
 */
public class App_TestWithdrawFund {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException, BrokenBarrierException {
        // TODO code application logic here
        Gson gson = new Gson();
        System.out.println(System.currentTimeMillis());
        ExcelHelper excelH = new ExcelHelper();
        DataTestCaseFullList dataFulls = excelH.ReadDataFromFileExcel("test.xlsx");
        ResultApi resultApi = excelH.RunTest(dataFulls);
//        excelH.WriteResult("test.xlsx", resultApi);

    }
}
