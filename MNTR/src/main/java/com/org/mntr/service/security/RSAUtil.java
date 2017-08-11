package com.org.mntr.service.security;

import java.io.Serializable;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;

public class RSAUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(RSAUtil.class);

	private final static int RSA_KEY_LENGTH = 4096;
	static final String ALGORITHM_NAME = "RSA";
	static final String PADDING_SCHEME = "OAEPWITHSHA-512ANDMGF1PADDING";
	static final String MODE_OF_OPERATION = "ECB";

	public static void main(String args[]) {
		String shortMessage = "aaaaaaaaaaaaaaaaaaaaaaaaazzzzzzzzzzzzzzzzzzzzaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaax563623754237478236%#%^#^%#&^$&^$76rsdyusgfhbfhdnkjsdnkjgehf348ty349u3t0j349jg34gn834gh7843gh3489hg8934hg834ngunrejnbjkdf bdf78ty347ty4893y98F%^F^FC^*GF&*EGFBEFEBNF&*HF&*FG*&G#RH#R*HR*#YR&HF&*F&H&fdgdfgdfgdfgdfbdfdfbdfbdfdfdfggesgsdvsdjhvbjsdbvusuvsbvsubvubukvbsukbvusvuskbvuskbvuksbusvkusbukvbsdkuvbuksdbvusdkbvukbsdukvbsdukbveukhg48h3hfhfihdibnvksdbnv,jsdbvjsdbkbvsdkvksdbvksdbvjksdbkvbsdukbvkbsdvjkbsdjkbvjksdvjksdjkvaaaaaaaaaaaaaaaaaaaaaaaaazzzzzzzzzzzzzzzzzzzzaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaax563623754237478236%#%^#^%#&^$&^$76rsdyusgfhbfhdnkjsdnkjgehf348ty349u3t0j349jg34gn834gh7843gh3489hg8934hg834ngunrejnbjkdf bdf78ty347ty4893y98F%^F^FC^*GF&*EGFBEFEBNF&*HF&*FG*&G#RH#R*HR*#YR&HF&*F&H&fdgdfgdfgdfgdfbdfdfbdfbdfdfdfggesgsdvsdjhvbjsdbvusuvsbvsubvubukvbsukbvusvuskbvuskbvuksbusvkusbukvbsdkuvbuksdbvusdkbvukbsdukvbsdukbveukhg48h3hfhfihdibnvksdbnv,jsdbvjsdbkbvsdkvksdbvksdbvjksdbkvbsdukbvkbsdvjkbsdjkbvjksdvjksdjkv";

		try {
			RSAUtil r = new RSAUtil();
			KeyPair rsaKeyPair = r.generateKeyPair();
			String encryptedText = r.encryptData(shortMessage, rsaKeyPair.getPublic());
			System.out.println("Encrypted text = " + encryptedText);
			String decryptedText = r.decryptData(encryptedText, rsaKeyPair.getPrivate());
			System.out.println("Decrypted text = " + decryptedText);
		} catch (Exception e) {
			System.out.println("Exception while encryption/decryption");
			e.printStackTrace();
		}

	}

	public KeyPair generateKeyPair() {
		logger.info("Inside [RSAUtil][getKeyPair]");
		KeyPairGenerator rsaKeyGen = null;
		try {
			rsaKeyGen = KeyPairGenerator.getInstance(ALGORITHM_NAME);
			rsaKeyGen.initialize(RSA_KEY_LENGTH);
			return rsaKeyGen.generateKeyPair();
		} catch (Exception ex) {
			logger.error("RSAUTIL ERROR : " + ex);
		}
		return null;
	}

	public String encryptData(String message, Key publicKey) throws Exception {
		logger.info("Inside [RSAUtil][generateSecretKey]");
		Cipher c = null;
		byte[] cipherTextArray = null;
		try {
			c = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME);
			c.init(Cipher.ENCRYPT_MODE, publicKey);
			cipherTextArray = c.doFinal(message.getBytes());
			return Base64.encodeBase64String(cipherTextArray);
		} catch (Exception ex) {
			logger.error("RSAUTIL ERROR : " + ex);
		} finally {
			c = null;
			cipherTextArray = null;
		}
		return null;
	}

	public String decryptData(String encryptedMessage, Key privateKey) throws Exception {
		logger.info("Inside [RSAUtil][generateSecretKey]");
		Cipher c = null;
		byte[] plainText = null;
		try {
			c = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME);
			c.init(Cipher.DECRYPT_MODE, privateKey);
			plainText = c.doFinal(Base64.decodeBase64(encryptedMessage));
			return new String(plainText);
		} catch (Exception ex) {
			logger.error("RSAUTIL ERROR : " + ex);
		} finally {
			c = null;
			plainText = null;
		}
		return null;
	}
}
