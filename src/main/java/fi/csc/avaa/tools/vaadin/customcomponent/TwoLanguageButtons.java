/**
 * 
 */
package fi.csc.avaa.tools.vaadin.customcomponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;

import fi.csc.avaa.tools.vaadin.language.LanguageChangeDispatcher;
import fi.csc.avaa.tools.vaadin.language.LanguageChangeEvent;
import fi.csc.avaa.tools.vaadin.language.LanguageChangeListener;
import fi.csc.avaa.tools.vaadin.language.LanguageConst;
import fi.csc.avaa.tools.vaadin.language.Translator;
import fi.csc.avaa.vaadin.tools.VaadinTools;

/**
 * @author jmlehtin
 *
 */
public class TwoLanguageButtons extends HorizontalLayout implements LanguageChangeDispatcher {

	private static final long serialVersionUID = 1L;
	private Translator translator;
	private Button locale1Btn;
	private Button locale2Btn;
	private Label textBetween;
	private List<LanguageChangeListener> listeners;

	/**
	 * @param caption
	 * @param options
	 */
	public TwoLanguageButtons(Locale locale1, Locale locale2, Translator translator, boolean useCustomLangText) {
		this.translator = translator;
		setImmediate(true);
		
		locale1Btn = VaadinTools.createLinkNativeButton(useCustomLangText ? translator.localize("LanguageSelect.Item." + LanguageConst.getLanguageStrFromLocale(locale1)) : LanguageConst.getLanguageStrFromLocale(locale1), null, null, "borderless language-btn");
		locale1Btn.setData(locale1);
		locale1Btn.addClickListener(e -> {
			Locale newLocale = (Locale) e.getButton().getData();
			this.translator.setDefaultLocale(newLocale);
			JavaScript.eval("window.locale = '" + newLocale.toString() + "';");
			dispatchLanguageChange(new LanguageChangeEvent(newLocale));
		});
		
		locale2Btn = VaadinTools.createLinkNativeButton(useCustomLangText ? translator.localize("LanguageSelect.Item." + LanguageConst.getLanguageStrFromLocale(locale2)) : LanguageConst.getLanguageStrFromLocale(locale2), null, null, "borderless language-btn");
		locale2Btn.addStyleName("borderless");
		locale2Btn.setData(locale2);
		locale2Btn.addClickListener(e -> {
			Locale newLocale = (Locale) e.getButton().getData();
			this.translator.setDefaultLocale(newLocale);
			JavaScript.eval("window.locale = '" + newLocale.toString() + "';");
			dispatchLanguageChange(new LanguageChangeEvent(newLocale));
		});
		
		textBetween = new Label("/", ContentMode.HTML);
		addComponents(locale1Btn, textBetween, locale2Btn);
		
		if(this.translator.getDefaultLocale() == locale1) {
			locale1Btn.addStyleName("bold underline");
			locale2Btn.removeStyleName("bold");
			locale2Btn.removeStyleName("underline");
		} else if(this.translator.getDefaultLocale() == locale2) {
			locale2Btn.addStyleName("bold underline");
			locale1Btn.removeStyleName("bold");
			locale1Btn.removeStyleName("underline");
		}
		
	}
	
	@Override
	public synchronized void dispatchLanguageChange(LanguageChangeEvent e) {
		if (listeners != null) {
            for (LanguageChangeListener listener : listeners) {
                listener.changeComponentLanguage(e);
            }
        }
	}
	
	public synchronized void addLanguageChangeListener(LanguageChangeListener listener) {
		if (listeners == null) {
            listeners = new ArrayList<>();
        }
        listeners.add(listener);
	}
	
	@Override
	public synchronized void removeLanguageChangeListener(LanguageChangeListener listener) {
		if (listeners == null) {
            listeners = new ArrayList<>();
        }
        listeners.remove(listener);
	}
	
}
