package fi.csc.avaa.tools.vaadin.language;

public interface LanguageChangeDispatcher {

	public void dispatchLanguageChange(LanguageChangeEvent e);
	
	public void addLanguageChangeListener(LanguageChangeListener listener);
	
	public void removeLanguageChangeListener(LanguageChangeListener listener);
}
