package es.madc.billing.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CalcUtil {

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));

	private CalcUtil() {
		// No hace nada
	}

	public static double round(double number) {
		return Math.ceil(number * 20d) / 20.0d;
	}

	public static double calcTaxes(double price, int tax) {
		return round(price * tax / 100d);
	}
	
	public static String doubleToString(double price) {
		return DECIMAL_FORMAT.format(price);
	}

}
