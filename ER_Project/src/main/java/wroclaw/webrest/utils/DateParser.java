package wroclaw.webrest.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

	/**
	 * Return Date in format ("EEE MMM dd HH:mm:ss zzz yyyy") </br>
	 * Example: Fri May 19 13:11:00 CEST 2017
	 * 
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static Date fromString(String date) throws ParseException {
		SimpleDateFormat  simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		return simpleDateFormat.parse(date);
	}
	
}
