package es.madc.billing.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalcUtil {

	private CalcUtil() {
		// No hace nada
	}

	public static BigDecimal calcTaxes(BigDecimal price, BigDecimal tax) {
		return round(price.multiply(tax).divide(new BigDecimal(100), RoundingMode.CEILING));
	}

	private static BigDecimal round(BigDecimal price) {
		// To round to the nearest .05, multiply by 20, round to the nearest integer, then divide by 20
		BigDecimal result =  BigDecimal.valueOf(Math.ceil(price.doubleValue() * 20) / 20);
		return result.setScale(2, RoundingMode.HALF_UP);
	}
	
	public static String formatPrice(BigDecimal price) {
		return round(price).toString();
	}

}
