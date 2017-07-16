package com.cbo.mntr.service.security;

import java.io.Serializable;
import java.security.SecureRandom;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SimpleAESUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SimpleAESUtil.class);
	// Please download unlimited JCE policies from ORACLE.
	private static final int AES_KEY_SIZE = 256;
	private static final int IV_SIZE = 96;
	private static final int TAG_BIT_LENGTH = 128;
	private static final String ALGO = "AES";
	private static final String ALGO_TRANSFORMATION_STRING = "/GCM/NoPadding";
	private static final String ALGO_PROVIDER = "BC";

	public static void main(String args[]) {
		String message = "aaaaaaaaaaaaaaaaaaaaaaaaazzzzzzzzzzzzzzzzzzzzaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaax563623754237478236%#%^#^%#&^$&^$76rsdyusgfhbfhdnkjsdnkjgehf348ty349u3t0j349jg34gn834gh7843gh3489hg8934hg834ngunrejnbjkdf bdf78ty347ty4893y98F%^F^FC^*GF&*EGFBEFEBNF&*HF&*FG*&G#RH#R*HR*#YR&HF&*F&H&fdgdfgdfgdfgdfbdfdfbdfbdfdfdfggesgsdvsdjhvbjsdbvusuvsbvsubvubukvbsukbvusvuskbvuskbvuksbusvkusbukvbsdkuvbuksdbvusdkbvukbsdukvbsdukbveukhg48h3hfhfihdibnvksdbnv,jsdbvjsdbkbvsdkvksdbvksdbvjksdbkvbsdukbvkbsdvjkbsdjkbvjksdvjksdjkvaaaaaaaaaaaaaaaaaaaaaaaaazzzzzzzzzzzzzzzzzzzzaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaax563623754237478236%#%^#^%#&^$&^$76rsdyusgfhbfhdnkjsdnkjgehf348ty349u3t0j349jg34gn834gh7843gh3489hg8934hg834ngunrejnbjkdf bdf78ty347ty4893y98F%^F^FC^*GF&*EGFBEFEBNF&*HF&*FG*&G#RH#R*HR*#YR&HF&*F&H&fdgdfgdfgdfgdfbdfdfbdfbdfdfdfggesgsdvsdjhvbjsdbvusuvsbvsubvubukvbsukbvusvuskbvuskbvuksbusvkusbukvbsdkuvbuksdbvusdkbvukbsdukvbsdukbveukhg48h3hfhfihdibnvksdbnv,jsdbvjsdbkbvsdkvksdbvksdbvjksdbkvbsdukbvkbsdvjkbsdjkbvjksdvjksdjkv";
		String password = "password";
		SimpleAESUtil a = new SimpleAESUtil();
		SecretKey aesKey = a.generateSecretKey();
		GCMParameterSpec spec = a.generateGCMParameterSpec();
		for (int i = 0; i < 10; i++) {
			String encData = a.encryptData(message + i, password, aesKey, spec);
			System.out.println("ENC : " + i + encData);
			String decData = a.decryptData(encData, password, aesKey, spec);
			System.out.println("DEC : " + i + decData);
		}
	}

	public SecretKey generateSecretKey() {
		SecretKey aesKey = null;
		KeyGenerator keygen = null;
		try {
			logger.info("Inside [AESUtil][generateSecretKey]");
			keygen = KeyGenerator.getInstance(ALGO);
			keygen.init(AES_KEY_SIZE);
			aesKey = keygen.generateKey();
			return aesKey;
		} catch (Exception ex) {
			logger.error("AESUTIL ERROR : " + ex);
		} finally {
			aesKey = null;
			keygen = null;
		}
		return null;
	}

	public GCMParameterSpec generateGCMParameterSpec() {
		GCMParameterSpec gcmParamSpec = null;
		byte iv[] = new byte[IV_SIZE];
		SecureRandom secureRandom = null;
		try {
			secureRandom = new SecureRandom();
			logger.info("Inside [AESUtil][generateGCMParameterSpec]");
			secureRandom.nextBytes(iv);
			gcmParamSpec = new GCMParameterSpec(TAG_BIT_LENGTH, iv);
			return gcmParamSpec;
		} catch (Exception ex) {
			logger.error("AESUTIL ERROR : " + ex);
		} finally {
			secureRandom = null;
			gcmParamSpec = null;
			iv = null;
		}
		return null;
	}

	public String encryptData(String message, String password, SecretKey aesKey, GCMParameterSpec gcmParamSpec) {
		logger.info("Inside [AESUtil][encryptData]");
		Cipher c = null;
		byte[] cipherTextInByteArr = null;
		BouncyCastleProvider bouncyCastleProvider = null;
		try {
			bouncyCastleProvider = new BouncyCastleProvider();
			Security.addProvider(bouncyCastleProvider);
			c = Cipher.getInstance(ALGO + ALGO_TRANSFORMATION_STRING, ALGO_PROVIDER);
			c.init(Cipher.ENCRYPT_MODE, aesKey, gcmParamSpec, new SecureRandom());
			c.updateAAD(password.getBytes());
			cipherTextInByteArr = c.doFinal(message.getBytes());
			return Base64.encodeBase64String(cipherTextInByteArr);
		} catch (Exception ex) {
			logger.error("AESUTIL ERROR : " + ex);
		} finally {
			bouncyCastleProvider = null;
			c = null;
			gcmParamSpec = null;
			cipherTextInByteArr = null;
			aesKey = null;
		}
		return null;
	}

	public String decryptData(String message, String password, SecretKey aesKey, GCMParameterSpec gcmParamSpec) {
		logger.info("Inside [AESUtil][decryptData]");
		Cipher c = null;
		byte[] plainTextInByteArr = null;
		String plainText;
		BouncyCastleProvider bouncyCastleProvider = null;
		try {
			bouncyCastleProvider = new BouncyCastleProvider();
			Security.addProvider(bouncyCastleProvider);
			c = Cipher.getInstance(ALGO + ALGO_TRANSFORMATION_STRING, ALGO_PROVIDER);
			c.init(Cipher.DECRYPT_MODE, aesKey, gcmParamSpec, new SecureRandom());
			c.updateAAD(password.getBytes());
			plainTextInByteArr = c.doFinal(Base64.decodeBase64(message));
			plainText = new String(plainTextInByteArr);
			return plainText;
		} catch (Exception ex) {
			logger.error("AESUTIL ERROR : " + ex);
		} finally {
			bouncyCastleProvider = null;
			c = null;
			gcmParamSpec = null;
			plainTextInByteArr = null;
			aesKey = null;
			plainText = null;
		}
		return null;
	}

}
