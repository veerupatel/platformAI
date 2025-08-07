package utility;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserUtility {
	private WebDriver driver;
	ChromeOptions options;

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public BrowserUtility(String browserName, boolean isHeadless) {
		if (browserName.equalsIgnoreCase("chrome") && isHeadless) {
			options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--window-size=1920,1080");
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.default_content_setting_values.popups", 0);

			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-notifications");

			this.driver = new ChromeDriver(options);
		} else {
			options = new ChromeOptions();
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-blink-features=AutomationControlled");
			//options.addArguments("--window-size=1920,1080");
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);

			driver = new ChromeDriver(options);
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
