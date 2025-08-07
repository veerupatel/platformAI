package tests;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.AdminPanel_Users;
import pages.DashboardPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RCS_Platform;
import pages.VerticalMenus;
import utility.BrowserUtility;

public class AdminPanelHomePageTest {

	WebDriver driver;
	BrowserUtility browserUtility;
	HomePage homePage;
	LoginPage loginPage;
	DashboardPage dashboardPage;
	VerticalMenus verticalMenus;
	AdminPanel_Users adminPanel_Users;

	@BeforeMethod
	public void launchBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "E:\\Browser_driver\\chromedriver-win64\\chromedriver.exe");
		browserUtility = new BrowserUtility("chrome", false);
		driver = browserUtility.getDriver();
		driver.get("https://platform.fonada.ai");
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		verticalMenus = new VerticalMenus(driver);
		adminPanel_Users = new AdminPanel_Users(driver);
		homePage.clickOnsignInlink().loginIntoApplication("admin", "rajesh@gmail.com", "Admin@1234");
	}

	@Test
	public void verifyAllTabName() throws InterruptedException {
		List<String> expectedTabList = Arrays.asList("Dashboard", "Users", "Numbers", "Credits", "Plans", "RCS Users",
				"Activity");
		verticalMenus.clickOnDesiredVerticalMenu("Admin Panel");
		Thread.sleep(3000);
		 List<String> actualTabList = adminPanel_Users.verifyAdminPanelAllTabList();

		Assert.assertEquals(actualTabList, expectedTabList);
	}

}
