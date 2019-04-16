package com.csci360.electionapp.security;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.csci360.electionapp.controller.VoterController;
import com.csci360.electionapp.model.database;

public class Security {
 
	 private static String encryptionKey;
	 private static final String characterEncoding       = "UTF-8";
	 private static final String cipherTransformation    = "AES/CBC/PKCS5PADDING";
	 private static final String aesEncryptionAlgorithem = "AES";
    
    
    public static boolean comparePasswords(String data1, byte[] salt, String hash) {
    	boolean match = false;
    	String hashCheck = getSHA256SecurePassword(data1,salt);
    	System.out.println("The second hash is: " + hashCheck);
    	if(hash.equals(hashCheck)) {
    		match = true;
    	}
    	else {
    		match = false;
    	}
    	return match;
    }
    
    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
    
    public String hashPassword(VoterController v) throws IOException, NoSuchAlgorithmException, SQLException {
    	database db = new database();
    	String username = v.getVoterUsername();
    	String passwordToHash = v.getVoterPassword();
    	String ssnToEncrypt = v.getVoterSSN();
    	String encryptedSSN = Security.encrypt(ssnToEncrypt);
    	v.setVoterSSN(encryptedSSN);
    	byte[] salt = getSalt();
    	
    	String hashedPass = Security.getSHA256SecurePassword(passwordToHash, salt);
    	BufferedWriter writer = new BufferedWriter(new FileWriter("salt.txt", true));
    	writer.append(username + ":" + hashedPass + ":" + salt + "\n");
    	writer.close();
    	v.setVoterPassword(hashedPass);
    	
		Connection conn = db.getConnection();
		db.addToVoters(v, salt, conn);
    	return hashedPass;
    }
 
    /**
     * Returns a hexadecimal encoded SHA-256 hash for the input String.
     * @param data
     * @return 
     */
    public static String getSHA256SecurePassword(String passwordToHash, byte[] salt) {
    	String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
    public static final String getEncryptionKey() throws IOException, FileNotFoundException {
    	String keyForEncryption = "";
    	String file ="src/com/csci360/electionapp/security/encryptionKey.txt";
        
        BufferedReader reader = new BufferedReader(new FileReader(file));
        keyForEncryption = reader.readLine();
        reader.close();
    	return keyForEncryption;
    }
    
    // The below methods encrypt and decrypt were acquired from the following website
    // https://www.includehelp.com/java-programs/encrypt-decrypt-string-using-aes-128-bits-encryption-algorithm.aspx
    /**
     * Method for Encrypt Plain String Data
     * @param plainText
     * @return encryptedText
     * @throws IOException 
     */
    public static String encrypt(String plainText) throws IOException {
    	encryptionKey = getEncryptionKey();
        String encryptedText = "";
        try {
            Cipher cipher   = Cipher.getInstance(cipherTransformation);
            byte[] key      = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedText = encoder.encodeToString(cipherText);

        } catch (Exception E) {
             System.err.println("Encrypt Exception : " + E.getMessage());
        }
        return encryptedText;
    }

    /**
     * Method For Get encryptedText and Decrypted provided String
     * @param encryptedText
     * @return decryptedText
     */
    public static String decrypt(String encryptedText) {
        String decryptedText = "";
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(encryptedText.getBytes("UTF8"));
            decryptedText = new String(cipher.doFinal(cipherText), "UTF-8");

        } catch (Exception E) {
            System.err.println("decrypt Exception : " + E.getMessage());
        }
        return decryptedText;
    }
}