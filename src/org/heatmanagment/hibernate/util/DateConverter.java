package org.heatmanagment.hibernate.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

	private static final SimpleDateFormat SDF;
	static {
		SDF = new SimpleDateFormat("yyyy-MM-dd");
	}

	public static String convert(Timestamp date) {
		Date dat = new Date(date.getTime());
		return SDF.format(dat);
	}

	public static Timestamp convert(String date) {
		Date dat = null;
		try {
			dat = SDF.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Timestamp(dat.getTime());
	}
}
