/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iannotate.database;

import java.math.BigInteger;
import java.security.*;
/**
 *
 * @author Susan
 */
public class MessageEncryption {
    public static String encryptMD5(String pass) throws NoSuchAlgorithmException {
	MessageDigest m = MessageDigest.getInstance("MD5");
	byte[] data = pass.getBytes(); 
	m.update(data,0,data.length);
	BigInteger i = new BigInteger(1,m.digest());
	return String.format("%1$032X", i);
    }
    
    public static String encryptSHA1(String pass) throws NoSuchAlgorithmException {
	MessageDigest m = MessageDigest.getInstance("SHA1");
	byte[] data = pass.getBytes(); 
	m.update(data,0,data.length);
	BigInteger i = new BigInteger(1,m.digest());
	return String.format("%1$040X", i);
    }

}
