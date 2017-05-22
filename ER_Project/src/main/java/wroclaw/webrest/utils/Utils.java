package wroclaw.webrest.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import wroclaw.webrest.entity.CurrencyEntity;
import wroclaw.webrest.entity.DocumentEntity;

public class Utils {

	public static <T> String convertListToString(List<T> list) {
		String text = "";
		for (T element : list) {
			text += element.toString() + "</br>";
		}
		return text;
	}

	public static Date getConvertedDate(String date) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOut = null;
		try {
			dateOut = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateOut;
	}

	/**
	 * Sort list of currency by date. </br>
	 * From older to newer. </br>
	 * 
	 * @see #getConvertedDate(String)
	 * @see Collections#sort(List)
	 */
	public static void sortCurrencyByDate(List<CurrencyEntity> unsortedList) throws ParseException {
		Collections.sort(unsortedList, new Comparator<CurrencyEntity>() {

			@Override
			public int compare(CurrencyEntity o1, CurrencyEntity o2) {
				if (getConvertedDate(o1.getDate()).after(getConvertedDate(o2.getDate()))) {
					return 1;
				} else {
					return -1;
				}
			}
		});
	}
	
	public static void sortDocumentByDate(List<DocumentEntity> unsortedList) throws ParseException {
		Collections.sort(unsortedList, new Comparator<DocumentEntity>() {
			
			@Override
			public int compare(DocumentEntity o1, DocumentEntity o2) {
				if (getConvertedDate(o1.getDate()).after(getConvertedDate(o2.getDate()))) {
					return 1;
				} else {
					return -1;
				}
			}
		});
	}
	
}
