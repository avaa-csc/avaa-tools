package fi.csc.avaa.tools.vaadin.language;

import java.util.Locale;

public class LanguageChangeEvent {

	private Locale locale;
	
	public LanguageChangeEvent(Locale locale) {
		this.locale = locale;
	}

	public Locale getLocale() {
		return locale;
	}
	
}
