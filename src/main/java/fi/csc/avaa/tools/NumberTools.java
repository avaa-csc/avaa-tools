/**
 * 
 */
package fi.csc.avaa.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;

import fi.csc.avaa.tools.logging.AvaaLogger;

/**
 * @author jmlehtin
 *
 */
public final class NumberTools {
	
	private static AvaaLogger log = new AvaaLogger(NumberTools.class.getName());

	/**
	 * Check whether str is a double.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean isNonNegativeInteger(String str) {
		try {
			return (Integer.parseInt(str) >= 0);
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * Convert double value between [0,1] to percent
	 * 
	 * @param value
	 * @return
	 */
	public static double getDoubleAsPercent(double value) {
		return value * 100.0;
	}
	
	/**
	 * Round double 
	 * 
	 * @param value
	 * @param decimalAmt
	 * @return
	 */
	public static double roundDoubleWithDecimals(double value, int decimalAmt) {
		if (decimalAmt < 0) {
			log.error("Invalid decimal amount");
			return 0.0d;
		}

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(decimalAmt, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	public static int getIntOrZeroValue(String strValue) {
		if(StringTools.isEmptyOrNull(strValue)) {
			return 0;
		}
		try {
			return Integer.parseInt(strValue);
		} catch(NumberFormatException e) {
			return 0;
		}
	}
	
	public static int getIntOrCustomValue(String strValue, int customValue) {
		if(StringTools.isEmptyOrNull(strValue)) {
			return customValue;
		}
		try {
			return Integer.parseInt(strValue);
		} catch(NumberFormatException e) {
			return customValue;
		}
	}
	
	public static double getDoubleOrCustomValue(String strValue, double customValue) {
		if(StringTools.isEmptyOrNull(strValue)) {
			return customValue;
		}
		try {
			return Double.parseDouble(strValue);
		} catch(NumberFormatException e) {
			return customValue;
		}
	}
	
}
