package fi.csc.avaa.tools;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * @author jmlehtin
 *
 */
public final class StringTools {

	/**
	 * Check whether a string is empty (i.e. null or empty)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyOrNull(String str) {
		if(str == null) {
			return true;
		} else if(str.isEmpty()) {
			return true;
		}
		return false; 
	}
	
	public static boolean isNull(String str) {
		if(str == null) {
			return true;
		}
		return false; 
	}
	
	public static Properties getStringAsProperties(String string, String propertySeparator, String keyValueSeparator) {
		StringBuffer buffer = new StringBuffer();
		
		String[] split = string.replaceAll(keyValueSeparator, "=").split(Pattern.quote(propertySeparator));
		for (int i = 0; i < split.length; i++) {
			buffer.append(split[i] + "\n");
		}
		Properties properties = new Properties();
		try {
			properties.load(new StringReader(buffer.toString()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return properties;
	}
	
	public static String escapeForHtml(String str) {
		return str.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	public static String getStringOrEmptyValue(String strValue) {
		if(isEmptyOrNull(strValue)) {
			return Const.STRING_EMPTY;
		}
		return strValue;
	}

	public static String getIntStringOrEmptyValue(int intValue) {
		if(intValue == 0) {
			return Const.STRING_EMPTY;
		}
		return String.valueOf(intValue);
	}

	public static boolean getBooleanValueFromBooleanString(String booleanStr) {
		if(isEmptyOrNull(booleanStr)) {
			return Boolean.FALSE;
		}
		return Boolean.getBoolean(booleanStr);
	}

	public static String getStringOrEmptyFromDate(Date date) {
		if(date == null) {
			return Const.STRING_EMPTY;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate lDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return formatter.format(lDate);
	}

	public static TreeSet<String> getAlphabeticallyOrderedStringSet(String...strings) {
		TreeSet<String> set = new TreeSet<>();
		for(String stringKey : strings) {
			set.add(stringKey);
		}
		return set;
	}
	
	public static String removeCharFromString(String str, char charToRemove) {
		return str.replaceAll(String.valueOf(charToRemove), Const.STRING_EMPTY);
	}
	
}
