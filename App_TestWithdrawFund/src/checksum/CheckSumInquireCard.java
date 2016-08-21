/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checksum;

import com.google.gson.Gson;
import dataInquireCard.DataInquireCardFull;
import encrypt.EncryptHelper;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author CPU01661-local
 */
public class CheckSumInquireCard {
    private String caller;
    private DataInquireCardFull data;
    private String secrectKey;

    public CheckSumInquireCard() {
    }

    public CheckSumInquireCard(String caller, DataInquireCardFull data, String secrectKey) {
        this.caller = caller;
        this.data = data;
        this.secrectKey = secrectKey;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public DataInquireCardFull getData() {
        return data;
    }

    public void setData(DataInquireCardFull data) {
        this.data = data;
    }

    public String getSecrectKey() {
        return secrectKey;
    }

    public void setSecrectKey(String secrectKey) {
        this.secrectKey = secrectKey;
    }
    
    public String createCheckSum() throws NoSuchAlgorithmException, UnsupportedEncodingException{
        Gson gson = new Gson();
        this.secrectKey = "a";
//        System.out.println("Data: " + gson.toJson(this.data));
        String rawData = this.caller + "|" + gson.toJson(this.data) + this.secrectKey;
        //System.out.println("RawData: " + rawData);
        byte[] encrypt = rawData.getBytes();
        String result = EncryptHelper.SHA256(encrypt);
        return result;
    }
}
