package com.cbo.mntr.utils;

import java.util.Date;
import java.util.GregorianCalendar;

class RollingCalendar extends GregorianCalendar {

	private static final long serialVersionUID = 1L;

	void setType(int i) {
		type = i;
	}

	public long getNextCheckMillis(Date date) {
		return getNextCheckDate(date).getTime();
	}

	public Date getNextCheckDate(Date date) {
		setTime(date);
		switch (type) {
		case 0: // '\0'
			set(13, 0);
			set(14, 0);
			add(12, 1);
			break;

		case 1: // '\001'
			set(12, 0);
			set(13, 0);
			set(14, 0);
			add(11, 1);
			break;

		case 2: // '\002'
			set(12, 0);
			set(13, 0);
			set(14, 0);
			int i = get(11);
			if (i < 12)
				set(11, 0);
			else
				set(11, 12);
			break;

		case 3: // '\003'
			set(11, 0);
			set(12, 0);
			set(13, 0);
			set(14, 0);
			add(5, 1);
			break;

		case 4: // '\004'
			set(7, getFirstDayOfWeek());
			set(11, 0);
			set(13, 0);
			set(14, 0);
			add(3, 1);
			break;

		case 5: // '\005'
			set(5, 1);
			set(11, 0);
			set(13, 0);
			set(14, 0);
			add(2, 1);
			break;

		default:
			throw new IllegalStateException("Unknown periodicity type.");
		}
		return getTime();
	}

	protected RollingCalendar() {
		type = -1;
	}

	int type;
}
