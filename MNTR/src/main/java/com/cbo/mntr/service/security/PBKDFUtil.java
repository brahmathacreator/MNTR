package com.cbo.mntr.service.security;

import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.Security;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class PBKDFUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(PBKDFUtil.class);

	public static final String PDKDF_ALGORITHM = "PBKDF2WithHmacSHA512";
	public static final int ITERATION_COUNT = 12288;
	public static final int SALT_LENGTH = 128;
	public static final int DERIVED_KEY_LENGTH = 256;

	@Autowired
	@Qualifier("bouncyProvider")
	private BouncyCastleProvider bouncyProvider;

	@Autowired
	@Qualifier("secRandom")
	private SecureRandom secRandom;

	public static void main(String args[]) {
		String hashedPassword = null;
		try {
			PBKDFUtil p = new PBKDFUtil();
			hashedPassword = p.hashPassword("Ramakrishna.R");
		} catch (Exception genSecExc) {
			System.out.println(genSecExc.getMessage() + " " + genSecExc.getCause());
		}
		System.out.println("PDKDF2 = " + hashedPassword);
	}

	public String hashPassword(String password) throws GeneralSecurityException {
		logger.info("Inside [PBKDFUtil][hashPassword]");
		byte[] salt = new byte[SALT_LENGTH];
		PBEKeySpec keySpec = null;
		SecretKeyFactory pbkdfKeyFactory = null;
		byte[] pbkdfHashedArray = null;
		try {
			Security.addProvider(bouncyProvider);
			secRandom.nextBytes(salt);
			keySpec = new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, DERIVED_KEY_LENGTH * 8);
			pbkdfKeyFactory = SecretKeyFactory.getInstance(PDKDF_ALGORITHM);
			pbkdfHashedArray = pbkdfKeyFactory.generateSecret(keySpec).getEncoded();
			return Base64.encodeBase64String(pbkdfHashedArray);
		} catch (Exception ex) {
			logger.error("PBKDFUTIL ERROR : " + ex);
		} finally {
			salt = null;
			keySpec = null;
			pbkdfKeyFactory = null;
			pbkdfHashedArray = null;
		}
		return null;
	}
}
