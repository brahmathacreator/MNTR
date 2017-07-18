package com.cbo.mntr.utils;

import org.apache.log4j.Logger;

import com.cbo.mntr.constants.MsgConstants;

public class MsgResolver {

	private static final Logger logger = Logger.getLogger(MsgResolver.class);

	public final static String getMsgCodeKey(final Integer msgCode) {
		String msgKey = null;
		try {
			if (msgCode != null) {
				if (msgCode.equals(MsgConstants.successMsgCode)) {
					msgKey = "page.general.txt17";
				} else if (msgCode.equals(MsgConstants.operationMsgCode)) {
					msgKey = "page.general.txt16";
				} else if (msgCode.equals(MsgConstants.parameterMismatch)) {
					msgKey = "page.general.txt19";
				} else if (msgCode.equals(MsgConstants.passwordCtrlrMsgCode1)) {
					msgKey = "page.changepassword.txt1";
				} else if (msgCode.equals(MsgConstants.passwordCtrlrMsgCode2)) {
					msgKey = "page.changepassword.txt2";
				} else if (msgCode.equals(MsgConstants.passwordCtrlrMsgCode3)) {
					msgKey = "page.changepassword.txt6";
				} else {
					msgKey = "page.general.txt18";
				}
				return msgKey;
			}
		} catch (Exception ex) {
			logger.error("UTILS ERROR : " + ex);
		} finally {
			msgKey = null;
		}
		return "page.general.txt18";
	}
}
