package com.auto.message.utils;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class EncodingUtil {

	
	/**
	 * MD5 암호화
	 * 
	 * @param msg
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5(String msg) throws NoSuchAlgorithmException {
		long start = System.currentTimeMillis();
	    
		MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(msg.getBytes());
	    
	    long end = System.currentTimeMillis();
	    
	    System.out.println("MD5 경과 : " + (end-start));
	    
	    return EncodingUtil.byteToHexString(md.digest());
	}

	/**
	 * SHA256 암호화
	 * 
	 * @param msg
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String sha256(String msg)  throws NoSuchAlgorithmException {
		long start = System.currentTimeMillis();
	    
		MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.update(msg.getBytes());
	    
	    long end = System.currentTimeMillis();
	    System.out.println("SHA256 경과 : " + (end-start));
	    
	    return EncodingUtil.byteToHexString(md.digest());
	}
	
	/**
	 * MD5, SHA256 암호화시 메시지 변환 모듈
	 * 
	 * @param data
	 * @return
	 */
	public static String byteToHexString(byte[] data) {

	    StringBuilder sb = new StringBuilder();

	    for(byte b : data) {
	        sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
	    }
	    return sb.toString();

	}

	/**
	 * AES256 암호화
	 * 
	 * @param msg
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptAES256(String msg, String key) throws Exception {
		long start = System.currentTimeMillis();
	    
		SecureRandom 	random 		= new SecureRandom();
	    byte 			bytes[] 	= new byte[20];
	    random.nextBytes(bytes);
	    
	    byte[] 			saltBytes 	= bytes;

	    // Password-Based Key Derivation function 2
	    SecretKeyFactory 	factory		= SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	    
	    // 70000번 해시하여 256 bit 길이의 키를 만든다.
	    PBEKeySpec 			spec 		= new PBEKeySpec(key.toCharArray(), saltBytes, 70000, 256);
	    SecretKey 			secretKey 	= factory.generateSecret(spec);
	    SecretKeySpec 		secret		= new SecretKeySpec(secretKey.getEncoded(), "AES");

	    // 알고리즘/모드/패딩
	    // CBC : Cipher Block Chaining Mode
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    cipher.init(Cipher.ENCRYPT_MODE, secret);

	    AlgorithmParameters params = cipher.getParameters();

	    // Initial Vector(1단계 암호화 블록용)
	    byte[] ivBytes 				= params.getParameterSpec(IvParameterSpec.class).getIV();
	    byte[] encryptedTextBytes 	= cipher.doFinal(msg.getBytes("UTF-8"));
	    byte[] buffer 				= new byte[saltBytes.length + ivBytes.length + encryptedTextBytes.length];

	    System.arraycopy(saltBytes			, 0, buffer, 0									, saltBytes.length);
	    System.arraycopy(ivBytes			, 0, buffer, saltBytes.length					, ivBytes.length);
	    System.arraycopy(encryptedTextBytes	, 0, buffer, saltBytes.length + ivBytes.length	, encryptedTextBytes.length);

	    
	    long end = System.currentTimeMillis();
	    System.out.println("AES256 경과 : " + (end-start));
	    
	    return Base64.getEncoder().encodeToString(buffer);

	}
	
	/**
	 * AES256 복호화
	 * 
	 * @param msg
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptAES256(String msg, String key) throws Exception {
		long start = System.currentTimeMillis();
	    
		Cipher 		cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    ByteBuffer 	buffer = ByteBuffer.wrap(Base64.getDecoder().decode(msg));

	    byte[] saltBytes = new byte[20];
	    buffer.get(saltBytes, 0, saltBytes.length);

	    byte[] ivBytes = new byte[cipher.getBlockSize()];
	    buffer.get(ivBytes, 0, ivBytes.length);

	    byte[] encryoptedTextBytes = new byte[buffer.capacity() - saltBytes.length - ivBytes.length];
	    buffer.get(encryoptedTextBytes);

	    SecretKeyFactory factory 	= SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	    PBEKeySpec 		 spec 	 	= new PBEKeySpec(key.toCharArray(), saltBytes, 70000, 256);
	    SecretKey 		 secretKey  = factory.generateSecret(spec);
	    SecretKeySpec 	 secret 	= new SecretKeySpec(secretKey.getEncoded(), "AES");

	    cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes));

	    byte[] decryptedTextBytes = cipher.doFinal(encryoptedTextBytes);

	    long end = System.currentTimeMillis();
	    System.out.println("AES256 복구화 경과 : " + (end-start));
	    
	    return new String(decryptedTextBytes);

	}
	
	public static String edDSA(String msg) throws Exception {
		long start = System.currentTimeMillis();
		
		KeyPairGenerator 	kpg = KeyPairGenerator.getInstance("Ed25519");
		KeyPair				kp	= kpg.generateKeyPair();
		
		byte[] getStr = msg.getBytes(StandardCharsets.UTF_8);
		
		Signature sig = Signature.getInstance("Ed25519");
		sig.initSign(kp.getPrivate());
		sig.update(getStr);
		
		byte[] resultStr = sig.sign();
		
		String encodedStr = Base64.getEncoder().encodeToString(resultStr);
		
		long end = System.currentTimeMillis();
	    System.out.println("edDSA 경과 : " + (end-start));
		
		return encodedStr;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		try {
			String a = "안녕하세요";
			String s = "secret";
			String b,c,d,dd,e;
			
			b = md5(a);
			c = sha256(a);
			d = encryptAES256(a, s);
			dd = decryptAES256(d, s);
			e = edDSA(a);
			
			System.out.println("암호화 md5 : " + b);
			System.out.println("암호화 sha-256 : " + c);
			System.out.println("암호화 AES256 : " + d);
			System.out.println("복구화 AES256 : " + dd);
			System.out.println("암호화 EdDSA : " + e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
