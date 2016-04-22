/**
 * 
 */
package fi.csc.avaa.vaadin.iframe;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.BrowserFrame;

/**
 * @author jmlehtin
 *
 */
public class HtmlContentFrame extends BrowserFrame {

	private static final long serialVersionUID = 1L;
	
	public HtmlContentFrame(String contentPath, String iframeStyle) {
		initFrame(iframeStyle);
		if(contentPath != null) {
			ExternalResource rs = new ExternalResource(contentPath);
			rs.setMIMEType("text/html");
			setSource(rs);
		}
	}

	public HtmlContentFrame(Resource source, String iframeStyle) {
		initFrame(iframeStyle);
		setSource(source);
	}
	
	private void initFrame(String iframeStyle) {
		setStyleName(iframeStyle);
		setImmediate(true);
		setResponsive(true);
	}
}
