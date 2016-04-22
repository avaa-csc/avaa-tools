/**
 * 
 */
package fi.csc.avaa.tools.vaadin.language;

import java.util.ArrayList;
import java.util.List;

import fi.csc.avaa.tools.Const;
import fi.csc.avaa.tools.StringTools;

/**
 * @author jmlehtin
 *
 */
public final class LanguageTools {

	public static String getLocalizedValueForField(String localizationPrefix, String fieldKey, Translator translator, String emptyKey) {
		if(StringTools.isEmptyOrNull(fieldKey)) {
			return translator.localize(emptyKey);
		}
		return translator.localize(localizationPrefix + fieldKey);
	}
	
	public static String getLocalizedValueForFieldOrEmpty(String localizationKey, Translator translator) {
		if(StringTools.isEmptyOrNull(localizationKey)) {
			return Const.STRING_EMPTY;
		}
		return translator.localize(localizationKey);
	}

	public static String getValueOrLocalizedEmptyValue(String fieldValue, Translator translator, String emptyKey) {
		if(StringTools.isEmptyOrNull(fieldValue)) {
			return translator.localize(emptyKey);
		}
		return fieldValue;
	}

	public static List<String> getTranslatedFieldValueList(Translator translator, String...stringKeys) {
		List<String> set = new ArrayList<>();
		for(String stringKey : stringKeys) {
			set.add(translator.localize(stringKey));
		}
		return set;
	}
	
	public static String getFI_ENTranslatedValueOrEmpty(String fiValue, String enValue, Translator translator, boolean getOtherLangValIfValIsEmpty) {
		if(LanguageConst.LOCALE_FI.equals(translator.getDefaultLocale())) {
			if(getOtherLangValIfValIsEmpty && StringTools.isEmptyOrNull(fiValue)) {
				return StringTools.getStringOrEmptyValue(enValue);
			}
			return StringTools.getStringOrEmptyValue(fiValue);
		} else if(LanguageConst.LOCALE_EN.equals(translator.getDefaultLocale())) {
			if(getOtherLangValIfValIsEmpty && StringTools.isEmptyOrNull(enValue)) {
				return StringTools.getStringOrEmptyValue(fiValue);
			}
			return StringTools.getStringOrEmptyValue(enValue);
		}
		return Const.STRING_EMPTY;
	}

}
