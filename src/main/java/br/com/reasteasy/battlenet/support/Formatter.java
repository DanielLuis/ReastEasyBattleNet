package br.com.reasteasy.battlenet.support;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.LocalDate;

/**
 * Classe utilizada para auxiliar na formatação de campos. 
 * 
 * @author daniel.vicente
 */
public class Formatter {


	public static String getDateToString(LocalDate data) {
		return getDateToString(data.toDateTimeAtStartOfDay().toDate(), "dd/MM/yyyy");
	}

	public static String getDateToString(Date data) {
		return getDateToString(data, "dd/MM/yyyy");
	}

	public static String getDateToString(Date data, String pattern) {
		if (data != null) {
			SimpleDateFormat dt = new SimpleDateFormat(pattern);
			return dt.format(data);
		}

		return "";
	}
}
