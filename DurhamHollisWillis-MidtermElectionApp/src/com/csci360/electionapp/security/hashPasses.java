package com.csci360.electionapp.security;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;

import com.csci360.electionapp.controller.VoterController;
import com.csci360.electionapp.model.database;

public class hashPasses {
 
    /*public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner sn = new Scanner(System.in);
        System.out.print("Please enter data for which SHA256 is required: ");
        String data = sn.nextLine();
        //sn.close();
        System.out.println("The password entered was: " + data);
         
        byte[] salt = getSalt();
        System.out.println("The salt used in data: " + salt);
        String hash = hashPasses.getSHA256SecurePassword(data,salt);
        System.out.println("The SHA256 (hexadecimal encoded) hash is: " + hash);
        //Scanner sn1 = new Scanner(System.in);
        System.out.print("Please enter data for which SHA256 is required: ");
        String data1 = sn.nextLine();
        System.out.println("The Password entered was: " + data1);
        System.out.println("The salt used in data1: " + salt);
        //System.out.println("Same passwords: " + comparePasswords(data1,salt,hash));
        
        sn.close();
    }*/
    
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
    	byte[] salt = getSalt();
    	
    	String hashedPass = hashPasses.getSHA256SecurePassword(passwordToHash, salt);
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
     
    /**
     * Use javax.xml.bind.DatatypeConverter class in JDK to convert byte array
     * to a hexadecimal string. Note that this generates hexadecimal in upper case.
     * @param hash
     * @return 
     */
    private String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
}