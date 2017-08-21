package com.org.mntr.audit;

import java.util.Calendar;

import org.springframework.data.auditing.DateTimeProvider;

public class AuditDateTimeProvider implements DateTimeProvider {

	@Override
	public Calendar getNow() {
		return Calendar.getInstance();
	}

}
