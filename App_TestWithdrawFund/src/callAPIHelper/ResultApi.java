/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callAPIHelper;

import dataRequest.DataTestCaseFullList;
import java.util.ArrayList;
import thread.MyThread;
import thread.ThreadResult;

/**
 *
 * @author Kuti
 */
public class ResultApi {
    private ArrayList<ThreadResult> threads;
    private DataTestCaseFullList data;

    public ResultApi() {
    }

    public ResultApi(ArrayList<ThreadResult> threads, DataTestCaseFullList data) {
        this.threads = threads;
        this.data = data;
    }

    public ArrayList<ThreadResult> getThreads() {
        return threads;
    }

    public void setThreads(ArrayList<ThreadResult> threads) {
        this.threads = threads;
    }

    public DataTestCaseFullList getData() {
        return data;
    }

    public void setData(DataTestCaseFullList data) {
        this.data = data;
    }
    
}
