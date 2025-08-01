package tests;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RCS_Platform;
import pages.VerticalMenus;
import utility.BrowserUtility;

public class RCS_PlatformTest {

	WebDriver driver;
	BrowserUtility browserUtility;
	HomePage homePage;
	LoginPage loginPage;
	DashboardPage dashboardPage;
	VerticalMenus verticalMenus;
	RCS_Platform rcs_Platform;

	@BeforeMethod
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "E:\\Browser_driver\\chromedriver-win64\\chromedriver.exe");
		browserUtility = new BrowserUtility("chrome", false);
		driver = browserUtility.getDriver();
		driver.get("https://platform.fonada.ai");
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		verticalMenus = new VerticalMenus(driver);
		rcs_Platform = new RCS_Platform(driver);
	}

	@Test
	public void openRCSListPage() throws InterruptedException, AWTException {
		homePage.clickOnsignInlink().dologinWith("Admin", "rajesh@gmail.com", "Admin@1234");
		verticalMenus.clickOnRCSMenu();
		Assert.assertTrue(verticalMenus.getPageHeader().contains("RCS Platform"));
		rcs_Platform.fillBrandInformation("Fonada", "BFSI", "https://platform.fonada.ai/",
				"C:\\Users\\Biraju\\Downloads\\pancar.png");
		rcs_Platform.fillcontactPersonInformation("Biraju", "Patel", "QA", "biraju@fonada.com", "8448752554");
		rcs_Platform.fillcompanyAddress("Noida sector 125, ADD Tower", "Second floor", "India", "UP",
				"Gautam Buddha Nagar", "201303");
		rcs_Platform.filllegalURLAndContactDetails("https://platform.fonada.ai/", "https://platform.fonada.ai/",
				"9599153229", "Technical Support", "biraju@fonada.com", "Official", "https://platform.fonada.ai/",
				"https://platform.fonada.ai/");
		rcs_Platform.uploadDocumentsIntoUploadSection("C:\\Users\\Biraju\\Downloads\\pancar.png",
				"C:\\Users\\Biraju\\Downloads\\pancar.png");
		rcs_Platform.submitRegistration();
	}

	@AfterClass(enabled = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
