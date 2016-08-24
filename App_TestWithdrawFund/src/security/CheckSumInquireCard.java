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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author CPU01661-local
 */
public class CheckSumInquireCard {

    public static String createCheckSum(String nameChecksum, String caller, String secrectKey, JsonObject jObj) throws NoSuchAlgorithmException, UnsupportedEncodingException{
//        Gson gson = new Gson();
//        System.out.println("Data Cheksum: " + jObj.toString());
        String rawData = caller + "|" + jObj.toString() + secrectKey;
//        System.out.println("RawData: " + rawData);
        byte[] encrypt = rawData.getBytes();
        String result = "";
        if(nameChecksum.equals("chksum-SHA-256")){
            result = SHA256(encrypt);
        }else if(nameChecksum.equals("chksum-SHA-512")){
            result = SHA512(encrypt);
        }else if(nameChecksum.equals("chksum-SHA-1")){
            result = SHA1(encrypt);
        }else if(nameChecksum.equals("chksum-MD5")){
            result = MD5(encrypt);
        }else if(nameChecksum.equals("signature")){
        }
        return result;
    }
    
    private static String SHA256(byte[] str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(str);
        byte byteData[] = md.digest();
        
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    private static String SHA512(byte[] str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(str);
        byte byteData[] = md.digest();
        
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    private static String SHA1(byte[] str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(str);
        byte byteData[] = md.digest();
        
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    private static String MD5(byte[] str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str);
        byte byteData[] = md.digest();
        
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
