package com.cbo.mntr.service.security;

import java.io.Serializable;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("AESUtil")
public class AESWithPBKDFHashCryptoUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AESWithPBKDFHashCryptoUtil.class);
	// Please download unlimited JCE policies from ORACLE.
	private static final String AES_ALGORITHM = "AES";
	private static final String AES_ALGORITHM_TRANSFORMATION_STRING = "/GCM/NoPadding";
	private static final String AES_ALGORITHM_PROVIDER = "BC";
	private static final String PDKDF_ALGORITHM = "PBKDF2WithHmacSHA512";
	private static final int ITERATION_COUNT = 12288;
	private static final int SALT_LENGTH = 128;
	private static final int DERIVED_KEY_LENGTH = 256;

	@Autowired
	@Qualifier("bouncyProvider")
	private BouncyCastleProvider bouncyProvider;

	@Autowired
	@Qualifier("secRandom")
	private SecureRandom secRandom;

	public String decryptData(final String encryptedData, final String password) {
		logger.info("Inside [AESUtil][decryptData]");
		Cipher c = null;
		byte[] encryptedTextInByteArr = null;
		byte[] decryptedTextInByteArr = null;
		byte[] ivByteArr = null;
		byte[] saltArr = null;
		ByteBuffer buffer = null;
		SecretKeySpec secretKeySpec = null;
		try {
			Security.addProvider(bouncyProvider);
			c = Cipher.getInstance(AES_ALGORITHM + AES_ALGORITHM_TRANSFORMATION_STRING, AES_ALGORITHM_PROVIDER);
			buffer = ByteBuffer.wrap(Base64.decodeBase64(encryptedData));
			saltArr = new byte[SALT_LENGTH];
			buffer.get(saltArr, 0, saltArr.length);
			ivByteArr = new byte[c.getBlockSize()];
			buffer.get(ivByteArr, 0, ivByteArr.length);
			encryptedTextInByteArr = new byte[buffer.capacity() - saltArr.length - ivByteArr.length];
			buffer.get(encryptedTextInByteArr);
			logger.info("TOTLEN DEC: " + buffer.limit() + " S : " + saltArr.length + " I : " + ivByteArr.length
					+ " D : " + encryptedTextInByteArr.length);
			secretKeySpec = generateSecretKeySpec(password, saltArr);
			c.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(ivByteArr));
			c.updateAAD(password.getBytes(StandardCharsets.US_ASCII));
			decryptedTextInByteArr = c.doFinal(encryptedTextInByteArr);
			return new String(decryptedTextInByteArr);
		} catch (Exception ex) {
			logger.error("AESUTIL ERROR : " + ex);
		} finally {
			c = null;
			encryptedTextInByteArr = null;
			decryptedTextInByteArr = null;
			ivByteArr = null;
			saltArr = null;
			buffer = null;
			secretKeySpec = null;
		}
		return null;
	}

	public String encryptData(final String message, final String password) {
		logger.info("Inside [AESUtil][encryptData]");
		Cipher c = null;
		byte[] cipherTextInByteArr = null;
		byte[] ivByteArr = null;
		AlgorithmParameters configuredSpec = null;
		SecretKeySpec secretKeySpec = null;
		byte[] saltArr = null;
		try {
			Security.addProvider(bouncyProvider);
			saltArr = generateSalt();
			secretKeySpec = generateSecretKeySpec(password, saltArr);
			c = Cipher.getInstance(AES_ALGORITHM + AES_ALGORITHM_TRANSFORMATION_STRING, AES_ALGORITHM_PROVIDER);
			c.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			c.updateAAD(password.getBytes(StandardCharsets.US_ASCII));
			configuredSpec = c.getParameters();
			ivByteArr = configuredSpec.getParameterSpec(IvParameterSpec.class).getIV();
			cipherTextInByteArr = c.doFinal(message.getBytes());
			return constructEncodedEncryptedData(saltArr, ivByteArr, cipherTextInByteArr);
		} catch (Exception ex) {
			logger.error("AESUTIL ERROR : " + ex);
		} finally {
			c = null;
			cipherTextInByteArr = null;
			ivByteArr = null;
			configuredSpec = null;
			secretKeySpec = null;
			saltArr = null;

		}
		return null;
	}

	private String constructEncodedEncryptedData(final byte[] saltArr, final byte[] ivArr, final byte[] dataArr) {
		logger.info("Inside [AESUtil][constructEncodedEncryptedData]");
		byte[] finalArr = null;
		try {
			finalArr = new byte[saltArr.length + ivArr.length + dataArr.length];
			System.arraycopy(saltArr, 0, finalArr, 0, saltArr.length);
			System.arraycopy(ivArr, 0, finalArr, saltArr.length, ivArr.length);
			System.arraycopy(dataArr, 0, finalArr, saltArr.length + ivArr.length, dataArr.length);
			logger.info("TOTLEN ENC: " + finalArr.length + " S : " + saltArr.length + " I : " + ivArr.length + " D : "
					+ dataArr.length);
			return URLEncoder.encode(Base64.encodeBase64String(finalArr), StandardCharsets.UTF_8.toString());
		} catch (Exception ex) {
			logger.error("AESUTIL ERROR : " + ex);
		} finally {
			finalArr = null;
		}
		return null;
	}

	private SecretKeySpec generateSecretKeySpec(final String password, final byte[] salt) {
		logger.info("Inside [AESUtil][generateSecretKeySpec]");
		SecretKeyFactory factory = null;
		PBEKeySpec pbeKeySpec = null;
		SecretKey secretKey = null;
		SecretKeySpec secretKeySpec = null;
		try {
			Security.addProvider(bouncyProvider);
			factory = SecretKeyFactory.getInstance(PDKDF_ALGORITHM);
			pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, DERIVED_KEY_LENGTH);
			secretKey = factory.generateSecret(pbeKeySpec);
			secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), AES_ALGORITHM);
			return secretKeySpec;
		} catch (Exception ex) {
			logger.error("AESUTIL ERROR : " + ex);
		} finally {
			factory = null;
			pbeKeySpec = null;
			secretKey = null;
			secretKeySpec = null;
		}
		return null;
	}

	private byte[] generateSalt() {
		logger.info("Inside [AESUtil][generateSalt]");
		byte iv[] = null;
		try {
			iv = new byte[SALT_LENGTH];
			secRandom.nextBytes(iv);
			return iv;
		} catch (Exception ex) {
			logger.error("AESUTIL ERROR : " + ex);
		} finally {
			iv = null;
		}
		return null;
	}

}
