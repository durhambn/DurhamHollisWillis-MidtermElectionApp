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
	// Encryption key read from a file.
	private static String encryptionKey;
	private static final String characterEncoding       = "UTF-8";
	private static final String cipherTransformation    = "AES/CBC/PKCS5PADDING";
	private static final String aesEncryptionAlgorithem = "AES";
    
    /**
     * 
     * @param data1
     * @param salt
     * @param hash
     * @return
     */
    public static boolean comparePasswords(String data1, byte[] salt, String hash) {
    	boolean match = false;
    	// Hash the password to compare against hash in database
    	String hashCheck = getSHA256SecurePassword(data1,salt);
    	// Return true if they match otherwise return false
    	if(hash.equals(hashCheck)) {
    		match = true;
    	}
    	else {
    		match = false;
    	}
    	return match;
    }
    
    /**
     * 
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static byte[] getSalt() throws NoSuchAlgorithmException {
    	// Random instance generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        // Byte array of length 16
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
    
    /**
     * 
     * @param v
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws SQLException
     */
    public String hashPassword(VoterController v) throws IOException, NoSuchAlgorithmException, SQLException {
    	// Create connection to the database and grab necessary info to encrypt, hash and store to database
    	database db = new database();
    	String username = v.getVoterUsername();
    	String passwordToHash = v.getVoterPassword();
    	String ssnToEncrypt = v.getVoterSSN();
    	// Encrypt the SSN in case of breach it is not in plaintext
    	String encryptedSSN = Security.encrypt(ssnToEncrypt);
    	// Set ssn field to the encrypted one
    	v.setVoterSSN(encryptedSSN);
    	byte[] salt = getSalt();
    	// Hash the password with a salt (uses SHA-256)
    	String hashedPass = Security.getSHA256SecurePassword(passwordToHash, salt);
    	//Back up file is stored to the a file
    	BufferedWriter writer = new BufferedWriter(new FileWriter("salt.txt", true));
    	writer.append(username + ":" + hashedPass + ":" + salt + "\n");
    	writer.close();
    	// Set the voter's password to the hashed text
    	v.setVoterPassword(hashedPass);
    	// Grab a connection and add voter with the salt to the database
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
    	// Attempts to make a SHA-256 hash of the given password with an associated salt
    	// Salt is unique to user (Typically would be placed in a separate database
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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
    /**
     * 
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static final String getEncryptionKey() throws IOException, FileNotFoundException {
    	// Read the encryption key from the file (Would be stored on a separate local computer on the network)
    	String keyForEncryption = "";
    	String file ="src/com/csci360/electionapp/security/encryptionKey.txt";
        // Read the file and store the encryption key
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
    	// Grab the encryption key
    	encryptionKey = getEncryptionKey();
        String encryptedText = "";
        // Encrypt the information
        try {
            Cipher cipher   = Cipher.getInstance(cipherTransformation);
            byte[] key      = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedText = encoder.encodeToString(cipherText);

        } catch (Exception e) {
             System.err.println("Encrypt Exception : " + e.getMessage());
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
        // Decrypt the information
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(encryptedText.getBytes("UTF8"));
            decryptedText = new String(cipher.doFinal(cipherText), "UTF-8");

        } catch (Exception e) {
            System.err.println("decrypt Exception : " + e.getMessage());
        }
        return decryptedText;
    }
}