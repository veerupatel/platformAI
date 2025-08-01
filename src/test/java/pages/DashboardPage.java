package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Util;

public class DashboardPage extends Util {

	WebDriver driver;
	WebDriverWait wait;
	AiAgentsListPage agentsListPage;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private final By HORIZONTAL_MENUS_LOCATOR = By.xpath("//a[@href=\"/dashboard/agents\"]");
	private final By DASHBOARD_HANDCROMB_LOCATOR = By.xpath("//a[text()=\"dashboard\"]");
	private final By OVERVIEW_HANDCROMB_LOCATOR = By.xpath("//span[text()=\"overview\"]");

	public AiAgentsListPage clickOnAIAgentsMenu() throws InterruptedException {
		onClick(HORIZONTAL_MENUS_LOCATOR);
		agentsListPage = new AiAgentsListPage(driver);
		return agentsListPage;
	}

	public String getLoggedInUserName(String username) {
		String LOGGED_IN_USER_NAME_LOCATOR = "//span[contains(text(),'" + username + "')]";
		By dynamicLocator = By.xpath(LOGGED_IN_USER_NAME_LOCATOR);
		return getTextFrom(dynamicLocator);
	}

}
