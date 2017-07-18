package com.cbo.mntr.exceptions;

public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;

	private final int msgCode;

	public CustomException(int msgCode) {
		this.msgCode = msgCode;
	}

	public int getMsgCode() {
		return msgCode;
	}

}
