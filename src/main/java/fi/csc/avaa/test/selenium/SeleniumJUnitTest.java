/**
 * 
 */
package fi.csc.avaa.test.selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import fi.csc.avaa.test.selenium.BrowserDriverFactory.Browser;
import fi.csc.avaa.tools.vaadin.language.LanguageConst;
import fi.csc.avaa.tools.vaadin.language.Translator;


/**
 * @author jmlehtin
 *
 */
@RunWith(Parameterized.class)
public abstract class SeleniumJUnitTest {
	
	private static final boolean RUN_CHROME_TESTS = true;
	private static final boolean RUN_FIREFOX_TESTS = true;
	private static final boolean RUN_IE_TESTS = false;
	
	private BrowserDriverFactory driverFactory = new BrowserDriverFactory();
	protected RemoteWebDriver driver;
	protected Translator translator = new Translator(LanguageConst.LOCALE_FI);
	
	@Parameters
	public static Iterable<Browser> data() {
		List<Browser> browsers = new ArrayList<>();
		if(RUN_CHROME_TESTS) {
			browsers.add(Browser.CHROME);
		}
		if(RUN_FIREFOX_TESTS) {
			browsers.add(Browser.FIREFOX);
		}
		if(RUN_IE_TESTS) {
			browsers.add(Browser.IE);
		}
        return browsers;
    }
	
	@Parameter
	public Browser browserType;
	
	@Before
	public void openBrowser() {
		driver = driverFactory.getBrowserDriver(browserType);
		driver.manage().window().maximize();
		driver.get("http://avoin-test.csc.fi/");
		driver.manage().timeouts().implicitlyWait(5,  TimeUnit.SECONDS);
		setPortalLanguageToFI();
       
	} 

	@After
	public void closeBrowser() {
		driver.quit();
	}
	
	private void setPortalLanguageToFI() {
		WebElement langElem = driver.findElementByCssSelector("#heading .language-selection a");
		if(!langElem.getText().equals("In English")) {
			langElem.click();
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
	}

	protected void clickWebElement(WebElement elem, int waitSeconds) {
		elem.click();
		sleepSeconds(waitSeconds);
	}
	
	protected WebElement fluentWait(final By locator) {
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(10, TimeUnit.SECONDS)
	            .pollingEvery(1, TimeUnit.SECONDS)
	            .ignoring(NoSuchElementException.class);

	    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return driver.findElement(locator);
	        }
	    });
	    return foo;
	};
	
	protected String getElemText(By locator) {
		final int MAX_LOOPS = 10000;
		int i=0;
		while(true) {
			WebElement elem = fluentWait(locator);
			try {
				return elem.getText();
			} catch (StaleElementReferenceException e) {
				i++;
				if(i > MAX_LOOPS) {
					break;
				}
			}
		}
		return null;
	}
	
	protected void sleepSeconds(int sleepSeconds) {
		try {
			Thread.sleep(sleepSeconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected boolean isElementPresent(By locatorKey) {
	    try {
	        driver.findElement(locatorKey);
	        return true;
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}
	
}
