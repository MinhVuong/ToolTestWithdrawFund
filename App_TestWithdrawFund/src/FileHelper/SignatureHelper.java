/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileHelper;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Kuti
 */
public class SignatureHelper {
    public static String RSASHA1Signature(String plaintext) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, Exception {
// Generate new key
        KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        PrivateKey privateKey = get("MICODE_private.pem");
        //String plaintext = "This is the message being signed";

// Compute signature
        Signature instance = Signature.getInstance("SHA1withRSA");
        instance.initSign(privateKey);
        instance.update(plaintext.getBytes("UTF-8"));
        byte[] signature = instance.sign();

// Display results
//        System.out.println("Input data: " + plaintext);
        //System.out.println("Digest: " + bytes2String(digest));
        //System.out.println("Cipher text: " + bytes2String(cipherText));
//        System.out.println("Base64: " + Base64.encode(signature));

        String result = Base64.encode(signature);
        return result;
    }
    
    public static PrivateKey get(String filename)
            throws Exception {

        

        File f = new File(filename);
        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int) f.length()];
        dis.readFully(keyBytes);
        dis.close();

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }
}
