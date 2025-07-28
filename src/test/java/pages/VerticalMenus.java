package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Util;

public class VerticalMenus extends Util {

	WebDriver driver;

	public VerticalMenus(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private final By VERTICAL_MENUS_LOCATOR = By.cssSelector("ul[class=\"-mx-2 space-y-1\"] > li a");

	public List<WebElement> getAllVerticalMenusName() {
		return fetchAllOptions(VERTICAL_MENUS_LOCATOR);
	}

}
