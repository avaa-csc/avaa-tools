/**
 * 
 */
package fi.csc.avaa.test.selenium;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author jmlehtin
 *
 */
public final class BrowserDriverFactory {

	public RemoteWebDriver getBrowserDriver(Browser browser) {
		switch (browser) {
		case CHROME:
			return new ChromeDriver();
		case FIREFOX:
			return new FirefoxDriver();
		case IE:
			return new InternetExplorerDriver();
		default:
			return null;
		}
	}
	
	public enum Browser {
		CHROME,
		FIREFOX,
		IE;
	}
}
