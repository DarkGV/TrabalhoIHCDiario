/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diariosecreto;

import javax.crypto.Cipher;
import java.security.MessageDigest;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author DiogoSilva
 */
public class DiaryCrypto {
    
    public static byte[] encryptEntry(String entry, String passwd) throws Exception{
        
        MessageDigest passwdDigest = MessageDigest.getInstance("md5");
        byte[] dgst = passwdDigest.digest(passwd.getBytes());
        
        Cipher mode = Cipher.getInstance("AES");
        
        SecretKey aesKey = new SecretKeySpec(dgst, 0, dgst.length, "AES");
        mode.init(Cipher.ENCRYPT_MODE, aesKey);
        
        return mode.doFinal(entry.getBytes());
    }
    
    public static String decryptEntry(String entry, String passwd) throws Exception{
        
        MessageDigest passwdDigest = MessageDigest.getInstance("md5");
        byte[] dgst = passwdDigest.digest(passwd.getBytes());
        
        Cipher mode = Cipher.getInstance("AES");
        
        SecretKey aesKey = new SecretKeySpec(dgst, 0, dgst.length, "AES");
        mode.init(Cipher.DECRYPT_MODE, aesKey);
        
        return new String( mode.doFinal(entry.getBytes()) );
    }
}
