/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import encrypt.EncryptHelper;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author CPU01661-local
 */
public class CheckSumInquireCard {

    public static String createCheckSum(String caller, String secrectKey, JsonObject jObj) throws NoSuchAlgorithmException, UnsupportedEncodingException{
//        Gson gson = new Gson();
//        System.out.println("Data Cheksum: " + jObj.toString());
        String rawData = caller + "|" + jObj.toString() + secrectKey;
//        System.out.println("RawData: " + rawData);
        byte[] encrypt = rawData.getBytes();
        String result = EncryptHelper.SHA256(encrypt);
        return result;
    }
}
