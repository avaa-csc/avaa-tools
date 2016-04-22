/**
 * 
 */
package fi.csc.avaa.vaadin.portlet;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.vaadin.addon.ipcforliferay.LiferayIPC;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinPortletRequest;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.UI;

import fi.csc.avaa.tools.logging.AvaaLogger;
import fi.csc.avaa.tools.vaadin.customcomponent.DetailsConfirmationDialog;
import fi.csc.avaa.tools.vaadin.language.LanguageConst;
import fi.csc.avaa.tools.vaadin.language.Translator;

/**
 * @author jmlehtin
 *
 */
public abstract class AvaaUI extends UI {
	private static AvaaLogger log = new AvaaLogger(AvaaUI.class.getName());
	private static final long serialVersionUID = 1L;
	protected Translator translator = new Translator(LanguageConst.LOCALE_FI);
	protected LiferayIPC ipc;
	
	public AvaaUI() {
		this.ipc = new LiferayIPC();
		this.ipc.extend(this);
	}

	@SuppressWarnings("serial")
	@Override
	protected void init(VaadinRequest request) {
		UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
			@Override
			public void error(com.vaadin.server.ErrorEvent event) {
				DetailsConfirmationDialog confirmation = new DetailsConfirmationDialog(translator,
																						translator.localize("Application.System.Error"),
																						translator.localize("Application.Error.ContactAvaa"),
																						getExceptionAsString(getRootCauseException(event)));
				UI.getCurrent().addWindow(confirmation);
				log.error("Unexpected error", event.getThrowable());
			}
		});
	}

	@Override
	protected void refresh(VaadinRequest request) {
		JavaScript.eval("window.locale = '" + Translator.getLocaleStr(translator.getDefaultLocale()) + "';");
	}
	
	protected PortletRequest getPortletRequest(VaadinRequest request) {
		if(request instanceof VaadinPortletRequest) {
			return ((VaadinPortletRequest) request).getPortletRequest();
		}
		return null;
	}
	
	protected ThemeDisplay getThemeDisplay(VaadinRequest request) {
		PortletRequest pReq = getPortletRequest(request);
		if(pReq != null) {
			return (ThemeDisplay) pReq.getAttribute(WebKeys.THEME_DISPLAY);
		}
		return null;
	}
	
	
	protected boolean isSignedIntoPortal(VaadinRequest request) {
		ThemeDisplay themeDisp = getThemeDisplay(request);
		if(themeDisp != null) {
			return themeDisp.isSignedIn();
		}
		return false;
	}
	
	private Throwable getRootCauseException(com.vaadin.server.ErrorEvent event) {
		Throwable t = event.getThrowable();
		for ( ; t != null; t = t.getCause()) {
		    if (t.getCause() == null) {
		    	break;
		    }
		}
		return t;
	}
	
	private String getExceptionAsString(Throwable throwable) {
		StringWriter writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		throwable.printStackTrace(printWriter);
		printWriter.flush();
		return writer.toString();
	}
}
