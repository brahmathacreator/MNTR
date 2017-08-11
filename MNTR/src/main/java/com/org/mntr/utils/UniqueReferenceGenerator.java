package com.org.mntr.utils;

import java.util.UUID;
import org.apache.commons.lang.RandomStringUtils;

public class UniqueReferenceGenerator {

	public static long getCurrentMSec() {
		return System.currentTimeMillis();
	}

	public static String getUUIDvalue() {
		return UUID.randomUUID().toString();
	}

	public static String generateRandomPassword() {
		return RandomStringUtils.randomAlphanumeric(8);
	}

	public static void main(String[] args) {
		System.out.println(UniqueReferenceGenerator.generateRandomPassword());
	}

}
