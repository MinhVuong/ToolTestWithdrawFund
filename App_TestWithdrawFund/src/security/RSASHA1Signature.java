/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import FileHelper.SignatureHelper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Kuti
 */
public class RSASHA1Signature {
    public static String getSignature(String nameAlgori, String rawData) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, Exception{
        System.out.println("RawData: " + rawData);
//        System.out.println("raw data: " + rawData);
        String signature = SignatureHelper.RSASHA1Signature(rawData);      
        return signature;
    }
}
