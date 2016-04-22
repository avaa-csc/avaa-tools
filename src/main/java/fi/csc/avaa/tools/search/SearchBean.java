/**
 * 
 */
package fi.csc.avaa.tools.search;

import fi.csc.avaa.tools.vaadin.language.Translator;

/**
 * Use this bean as an object which stores all search parameters.
 * 
 * @author jmlehtin
 *
 */
public abstract class SearchBean {

	public static final String SEPARATOR = "^^";
	
	public abstract String getFieldsAsHtml(Translator translator);
	public abstract String getFieldsAsString();
	
	
}
