/**
 * 
 */
package fi.csc.avaa.tools.vaadin.language;

import java.io.Serializable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import fi.csc.avaa.tools.logging.AvaaLogger;


/**
 * @author jmlehtin
 * 
 * Class for enabling language usage in apps using Vaadin.
 *
 */
public class Translator implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/** Resource Bundle */
    private Locale defaultLocale;
    private AvaaLogger log;

    public Translator(Locale defaultLocale){
    	this.defaultLocale = defaultLocale;
    	this.log = new AvaaLogger(Translator.class.getName());
    }

    public String localize(String key) {
	    try{
	       return localize(key, defaultLocale);
	    }catch(MissingResourceException e){
	        log.warn("Resource "+ key + " was not found in the resources file");
	        return key;
	    }
    }
    
    public String localize(String key, Locale locale) {
	    try{
	    	ResourceBundle rBundle = ResourceBundle.getBundle("Language", locale);
	    	return rBundle.getString(key);
	    }catch(MissingResourceException | NullPointerException e){
	        log.warn("Unable to create resource bundle or resource " + key + " was not found in the resources file");
	        return key;
	    }
    }
    
    public void setDefaultLocale(Locale locale) {
    	this.defaultLocale = locale;
    }
    
    public Locale getDefaultLocale() {
    	return defaultLocale;
    }
    
    public String getDefaultLocaleStr() {
    	return defaultLocale.toString();
    }
    
    public static String getLocaleStr(Locale locale) {
    	return locale.toString();
    }
    
    public static String getLocaleCountryStr(Locale locale) {
    	return locale.getCountry();
    }
    
    public static String getLocaleLanguageStr(Locale locale) {
    	return locale.getLanguage();
    }
}
