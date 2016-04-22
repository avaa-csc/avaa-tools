/**
 * 
 */
package fi.csc.avaa.tools.vaadin.javascript;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.vaadin.ui.JavaScript;

/**
 * @author jmlehtin
 *
 */
public final class JavaScriptTools {

	public static void sendTabSelectionJavaScript(String tab) {
		String jsCode = "window.selectedTab='" + tab + "';";
		JavaScript.getCurrent().execute(jsCode);
	}
	
	public static void sendEncodedStringVariableJavaScript(String varName, String data) {
		if(varName != null) {
			String dataEncoded = Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
			String jsCode = "window." + varName + "=\"" + dataEncoded + "\";";
			JavaScript.getCurrent().execute(jsCode);
		}
	}
}
