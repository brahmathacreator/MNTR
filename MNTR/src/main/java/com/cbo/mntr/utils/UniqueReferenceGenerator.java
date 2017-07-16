package com.cbo.mntr.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

public class UniqueReferenceGenerator {

	public static long getCurrentMSec() {
		return System.currentTimeMillis();
	}

	public static String getUUIDvalue() {
		return UUID.randomUUID().toString();
	}

	public static String generateRandomPassword() {
		return new BigInteger(40, new SecureRandom()).toString(32);
	}

	public static void main(String[] args) {
		System.out.println(UniqueReferenceGenerator.generateRandomPassword());
	}

}
