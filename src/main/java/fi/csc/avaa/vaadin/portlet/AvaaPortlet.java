/**
 * 
 */
package fi.csc.avaa.vaadin.portlet;

import javax.portlet.PortletException;

import com.vaadin.server.VaadinPortlet;

import fi.csc.avaa.tools.logging.AvaaLogger;

/**
 * @author SUI team
 * @modified jmlehtin
 *
 */
public class AvaaPortlet extends VaadinPortlet  {

	private static final long serialVersionUID = 1L;
	private AvaaLogger log = new AvaaLogger(AvaaPortlet.class.getName());

	@Override
	protected void portletInitialized() throws PortletException {
		super.portletInitialized();
		log.debug("Portlet " + getPortletName() + " initialized!");
	}
	
}
