/**
 * 
 */
package fi.csc.avaa.tools.vaadin.language;

import java.util.Locale;

/**
 * @author jmlehtin
 *
 */
public final class LanguageConst {

	public static final Locale LOCALE_FI = new Locale("fi", "FI");
	public static final Locale LOCALE_SE = new Locale("sv", "SE");
	public static final Locale LOCALE_EN = new Locale("en", "US");
	
	public static String getLanguageStrFromLocale(Locale locale) {
		return locale.getLanguage().toUpperCase();
	}
	
	public static Locale getLocaleFromLanguageStr(String languageStr) {
		if(LOCALE_FI.getLanguage().equalsIgnoreCase(languageStr)) {
			return LOCALE_FI;
		} else if(LOCALE_SE.getLanguage().equalsIgnoreCase(languageStr)) {
			return LOCALE_SE;
		} else if(LOCALE_EN.getLanguage().equalsIgnoreCase(languageStr)) {
			return LOCALE_EN;
		}
		return null;
	}
	
	public static String getLocaleStrFromLocale(Locale locale) {
		return locale.toString();
	}
	
	public static Locale getLocaleFromLocaleStr(String localeStr) {
		if(LOCALE_FI.toString().equalsIgnoreCase(localeStr)) {
			return LOCALE_FI;
		} else if(LOCALE_SE.toString().equalsIgnoreCase(localeStr)) {
			return LOCALE_SE;
		} else if(LOCALE_EN.toString().equalsIgnoreCase(localeStr)) {
			return LOCALE_EN;
		}
		return null;
	}
}
