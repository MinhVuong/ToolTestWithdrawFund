/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encrypt;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author CPU01661-local
 */
public class EncryptHelper {

    public static String SHA512(byte[] str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

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

    public static String SHA1(byte[] str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

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

    public static String MD5(byte[] str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

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

    private static String AES256(String str, String key, String iv) throws Exception {
//        String key = "Kgnk3cpOwDVAtdNfWJ21cCNs3P4IGy81";
//        String iv = "Kgnk3cpOwDVAtdNf";
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secrectKey = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secrectKey, new IvParameterSpec(iv.getBytes()));
            byte[] encrypt = cipher.doFinal(str.getBytes("UTF-8"));
            String result = Base64.encode(encrypt);
            return result;
        } catch (Exception ex) {
            System.out.println("exception: " + ex.getMessage());
            return "";
        }
    }

    private static String RC4(String str, String key) throws Exception {
        SecretKey skey = new SecretKeySpec(key.getBytes(), "RC4");
        // enctypt!
        byte[] encrypted = encrypt(str.getBytes("UTF-8"), skey);
        String result = Base64.encode(encrypted);
        return result;
    }

    public static byte[] encrypt(byte[] text, SecretKey key){
        byte[] cipherText = null;
        try {
            Cipher cipher = Cipher.getInstance("RC4");
            cipher.init(1, key);
            cipherText = cipher.doFinal(text);
        } catch (Exception e) {
            System.out.println("Can't encrypt message using RC4" + e.getMessage());
        }
        return cipherText;
    }

    private static String bytes2String(byte[] bytes) {
        StringBuilder string = new StringBuilder();
        for (byte b : bytes) {
            String hexString = Integer.toHexString(0x00FF & b);
            string.append(hexString.length() == 1 ? "0" + hexString : hexString);
        }
        return string.toString();
    }

    public static String EncryptData(String str, String alth, String key, String iv) throws Exception {
        if (alth.equals("AES-256")) {
            str = AES256(str, key, iv);
        } else if (alth.equals("RC-4")) {
            str = RC4(str, key);
        } else if (alth.equals("AES-512")) {
        }
        return str;
    }

}
