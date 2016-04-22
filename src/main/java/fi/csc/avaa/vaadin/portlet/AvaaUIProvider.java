/**
 * 
 */
package fi.csc.avaa.vaadin.portlet;

import java.util.Locale;

import javax.portlet.PortletMode;

import com.liferay.portal.kernel.util.JavaConstants;
import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinPortlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

import fi.csc.avaa.tools.logging.AvaaLogger;
import fi.csc.avaa.tools.vaadin.language.LanguageConst;

/**
 * @author SUI-team
 * @modified jmlehtin
 *
 */
public abstract class AvaaUIProvider extends UIProvider {

	private static final long serialVersionUID = 1L;

	private AvaaLogger log = new AvaaLogger(AvaaUIProvider.class.getName());
	private Locale localeToServe;
	private int noInvoked = 0;
	private static final String INIT_PARAM_DEFAULT_LOCALE = "DEFAULT_LOCALE";

	protected abstract Class<? extends UI> getEditModeUI(Locale loc);

	protected abstract Class<? extends UI> getHelpModeUI(Locale loc);

	protected abstract Class<? extends UI> getViewModeUI(Locale loc);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.server.UIProvider#getUIClass(com.vaadin.server.
	 * UIClassSelectionEvent)
	 */
	@Override
	public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
		noInvoked++;
		javax.portlet.PortletRequest jxRequest = (javax.portlet.PortletRequest) event
				.getRequest().getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
		if (jxRequest != null) {
			final Class<? extends UI> retVal;
			// For one reason or another, this method is loaded twice on
			// starting a session, so we set the default locale on the second
			// invocation of this method.
			if (noInvoked >= 2) {
				if (localeToServe == null) {
					localeToServe = LanguageConst
							.getLocaleFromLocaleStr(VaadinPortlet
									.getCurrent()
									.getInitParameter(INIT_PARAM_DEFAULT_LOCALE));
					if (localeToServe == null) {
						localeToServe = VaadinSession.getCurrent().getLocale();
					}
					VaadinSession.getCurrent().setLocale(localeToServe);
				} else {
					localeToServe = VaadinSession.getCurrent().getLocale();
				}
			}
			PortletMode mode = jxRequest.getPortletMode();
			if (PortletMode.VIEW.equals(mode))
				retVal = getViewModeUI(localeToServe == null ? VaadinSession
						.getCurrent().getLocale() : localeToServe);
			else if (PortletMode.HELP.equals(mode))
				retVal = getHelpModeUI(localeToServe == null ? VaadinSession
						.getCurrent().getLocale() : localeToServe);
			else if (PortletMode.EDIT.equals(mode))
				retVal = getEditModeUI(localeToServe == null ? VaadinSession
						.getCurrent().getLocale() : localeToServe);
			else
				throw new IllegalArgumentException();
			return retVal;
		}
		log.error("Portlet request null. Unable to choose portlet mode.");
		return null;
	}

}
