/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checksum;

import FileHelper.SignatureHelper;
import com.google.gson.Gson;
import dataWithdrawFunds.DataWithdrawFundsFull;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Kuti
 */
public class RSASHA1Signature {
    private String caller;
    private DataWithdrawFundsFull data;
    private String secrectKey;

    public RSASHA1Signature() {
    }

    public RSASHA1Signature(String caller, DataWithdrawFundsFull data, String secrectKey) {
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

    public DataWithdrawFundsFull getData() {
        return data;
    }

    public void setData(DataWithdrawFundsFull data) {
        this.data = data;
    }

    public String getSecrectKey() {
        return secrectKey;
    }

    public void setSecrectKey(String secrectKey) {
        this.secrectKey = secrectKey;
    }
    public String getSignature() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, Exception{
        this.caller = "MICODE";
        this.secrectKey = "a";
        Gson gson = new Gson();
        String rawData = this.caller + "|" + gson.toJson(this.data) + this.secrectKey;
        String signature = SignatureHelper.RSASHA1Signature(rawData);      
        return signature;
    }
}
