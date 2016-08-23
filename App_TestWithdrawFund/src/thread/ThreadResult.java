/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.util.ArrayList;

/**
 *
 * @author Kuti
 */
public class ThreadResult {
    private ArrayList<MyThread> threads;
    private String nameSheet;

    public ThreadResult() {
    }

    public ThreadResult(ArrayList<MyThread> threads, String nameSheet) {
        this.threads = threads;
        this.nameSheet = nameSheet;
    }

    public ArrayList<MyThread> getThreads() {
        return threads;
    }

    public void setThreads(ArrayList<MyThread> threads) {
        this.threads = threads;
    }

    public String getNameSheet() {
        return nameSheet;
    }

    public void setNameSheet(String nameSheet) {
        this.nameSheet = nameSheet;
    }
    
}
