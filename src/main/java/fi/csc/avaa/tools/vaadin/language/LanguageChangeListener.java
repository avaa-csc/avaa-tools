/**
 * 
 */
package fi.csc.avaa.tools.vaadin.language;

/**
 * @author jmlehtin
 *
 */
@FunctionalInterface
public interface LanguageChangeListener {

	public void changeComponentLanguage(LanguageChangeEvent e);
}
