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
	public static final int delete = 4;
	public static final int view = 3;
	public static final int approve = 5;
	public static final int reject = 6;
	public static final int dashboard = 100;

	public static final String mailDeliverySuccStatus = "DS";
	public static final String mailDeliveryFailStatus = "DF";

}
