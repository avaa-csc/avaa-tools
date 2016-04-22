/**
 * 
 */
package fi.csc.avaa.tools.vaadin.customcomponent;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.NativeSelect;

import fi.csc.avaa.tools.vaadin.language.LanguageConst;
import fi.csc.avaa.tools.vaadin.language.LanguageChangeDispatcher;
import fi.csc.avaa.tools.vaadin.language.LanguageChangeEvent;
import fi.csc.avaa.tools.vaadin.language.LanguageChangeListener;
import fi.csc.avaa.tools.vaadin.language.Translator;

/**
 * @author jmlehtin
 *
 */
public class LanguageDropdown extends NativeSelect implements LanguageChangeDispatcher, LanguageChangeListener {

	private static final long serialVersionUID = 1L;
	private Translator translator;
	private List<LanguageChangeListener> listeners;
	private boolean useCaption;

	/**
	 * @param caption
	 * @param options
	 */
	public LanguageDropdown(Collection<Locale> locales, Translator translator, boolean useFlags, boolean useCaption, boolean useCustomLangText) {
		this.translator = translator;
		this.useCaption = useCaption;
		setImmediate(true);
		setMultiSelect(false);
		setNewItemsAllowed(false);
		setNullSelectionAllowed(false);
		for(Locale loc : locales) {
			String langStr = LanguageConst.getLanguageStrFromLocale(loc);
			String basePath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
			addItem(langStr);
			if(useCustomLangText) {
				setItemCaption(langStr, translator.localize("LanguageSelect.Item." + langStr));
			}
			if(useFlags) {
				if(loc == LanguageConst.LOCALE_FI) {
					setItemIcon(langStr, new FileResource(new File(basePath + "/images/flag_FI.gif")));
				} else if(loc == LanguageConst.LOCALE_SE) {
					setItemIcon(langStr, new FileResource(new File(basePath + "/images/flag_SE.gif")));
				} else if(loc == LanguageConst.LOCALE_EN) {
					setItemIcon(langStr, new FileResource(new File(basePath + "/images/flag_GB.gif")));
				}
			}
		}
		
		updateCaption();
		setValue(LanguageConst.getLanguageStrFromLocale(this.translator.getDefaultLocale()));
		addLanguageChangeListener(this);
		addValueChangeListener(e -> {
			Locale newLocale = LanguageConst.getLocaleFromLanguageStr(getValue().toString());
			this.translator.setDefaultLocale(newLocale);
			JavaScript.eval("window.locale = '" + newLocale.toString() + "';");
			dispatchLanguageChange(new LanguageChangeEvent(newLocale));
			
		});
	}
	
	@Override
	public synchronized void dispatchLanguageChange(LanguageChangeEvent e) {
		if (listeners != null) {
            for (LanguageChangeListener listener : listeners) {
                listener.changeComponentLanguage(e);
            }
        }
	}
	
	@Override
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

	
	/**
	 * Make sure to set this caption in a proper way either setting caption in Java or using properties file
	 */
	public void updateCaption() {
		if(useCaption) {
			setCaption(translator.localize("LanguageSelect.Caption"));
		}
	}

	@Override
	public void changeComponentLanguage(LanguageChangeEvent e) {
		updateCaption();
	}
	
}
