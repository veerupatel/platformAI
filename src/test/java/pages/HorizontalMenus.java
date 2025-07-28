package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Util;

public class HorizontalMenus extends Util {

	WebDriver driver;

	public HorizontalMenus(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private final By HORIZONTAL_MENUS_LOCATOR = By.cssSelector("div.max-w-7xl.mx-auto button");

	public List<WebElement> getAllVerticalMenusName() {
		return fetchAllOptions(HORIZONTAL_MENUS_LOCATOR);
	}

}
