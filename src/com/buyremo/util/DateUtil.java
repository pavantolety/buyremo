package com.buyremo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {

	public static Date formatDate(String format,String dateStr) throws ParseException{

		Date d=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		//DateTimeFormatter fmt = DateTimeFormat.forPattern(format);

		d=sdf.parse(dateStr);

		return d;
	}


	public static String printDate(String format,Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		//DateTimeFormatter fmt = DateTimeFormat.forPattern(format);

		//DateTime dt=new DateTime(date);
		
		
		return sdf.format(date);
	}

	//public static String
}
