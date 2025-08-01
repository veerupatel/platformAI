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
	private final By RCS_MENU_LOCOATOR = By.xpath("//a[normalize-space()='RCS Platform']");
	private final By PAGE_HEADER_LOCATOR = By.xpath("//h1[text()=\"RCS Platform\"]");

	public List<WebElement> getAllVerticalMenusName() {
		return fetchAllOptions(VERTICAL_MENUS_LOCATOR);
	}

	public VerticalMenus clickOnDesiredVerticalMenu(String optionToSelect) throws InterruptedException {
		List<WebElement> menusList = getAllVerticalMenusName();
		for (WebElement desiredMenu : menusList) {
			Thread.sleep(2000);
			System.out.println(desiredMenu.getText().trim());
			if (desiredMenu.getText().equalsIgnoreCase(optionToSelect)) {
				Thread.sleep(2000);
				desiredMenu.click();
			}
		}
		return this;
	}

	public WebElement getMenuOptionByText(String optionText) {

		String xpath = String.format("//ul[@class='-mx-2 space-y-1']//a[normalize-space()='%s']", optionText);
		scrollTillView(By.xpath(xpath));
		return driver.findElement(By.xpath(xpath));
	}

	public void clickOnRCSMenu() throws InterruptedException {
		scrollTillView(RCS_MENU_LOCOATOR);
		Thread.sleep(2000);
		driver.findElement(RCS_MENU_LOCOATOR).click();

	}

	public String getPageHeader() {
			return waitTillNonEmptyText(PAGE_HEADER_LOCATOR);
	}
}
