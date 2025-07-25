package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserUtility {
	private WebDriver driver;

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public WebDriver getDriver() {
	    return this.driver;
	}


	public BrowserUtility(String browserName, boolean isHeadless) {
		if (browserName.equalsIgnoreCase("chrome") && isHeadless) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--window-size=1920,1080");
			this.driver = new ChromeDriver(options);
		} else {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
	}

	public void goToWebsite(String url) {
	    if (driver == null) {
	        throw new IllegalStateException("WebDriver is not initialized.");
	    }
	    driver.get(url);
	}

	public void quitDriver() {
	    if (driver != null) {
	        driver.quit();
	        driver = null;
	    }
	}

}
