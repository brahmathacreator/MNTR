package com.cbo.mntr.constants;

import java.io.Serializable;

public class StatusConstants implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int active = 1;
	public static final int inActive = 0;
	public static final int insert = 1;
	public static final int edit = 2;
	public static final int delete = 3;
	public static final int view = 4;

	public static final String mailDeliverySuccStatus = "DS";
	public static final String mailDeliveryFailStatus = "DF";

}
