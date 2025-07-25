package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.HomePage;
import pages.LoginPage;
import utility.BrowserUtility;

public class LoginTest {

	WebDriver driver;
	BrowserUtility browserUtility;
	HomePage homePage;
	LoginPage loginPage;
	DashboardPage dashboardPage;

	@BeforeMethod
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "E:\\Browser_driver\\chromedriver-win64\\chromedriver.exe");
		browserUtility = new BrowserUtility("chrome", false);
		driver = browserUtility.getDriver();
		driver.get("https://platform.fonada.ai");
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
	}

	@Test(description = "Verify if user is able to login with Invalid Credentials", priority = 1)
	public void loginIntoAplicationWithInvalidCredentials() throws InterruptedException {

		Assert.assertEquals(homePage.clickOnsignInlink().loginIntoApplication("rajesh@gmail.com", "Admin@123")
				.getLoginCredentialsWarningMessage(), "Invalid login credentials");
	}

	@Test(description = "Verify if user is able to login with valid Credentials or not", priority = 2)
	public void loginIntoAplication() throws InterruptedException {
		homePage.clickOnsignInlink().loginIntoApplication("rajesh@gmail.com", "Admin@1234");
		Assert.assertTrue(dashboardPage.getLoggedInUserName("rajesh").equalsIgnoreCase("rajesh"));
	}

	@Test(description = "verify validation if user don't enter any username and password", priority = 0)
	public void verify_validation_if_user_dont_enter_any_username_password() throws InterruptedException {
		loginPage = homePage.clickOnsignInlink().loginIntoApplication("", "");
		Assert.assertEquals(loginPage.getRequiredFieldMessage("email"), "Email is required");
		Assert.assertEquals(loginPage.getRequiredFieldMessage("password"), "Password is required");
	}

	@Test(description = "Verify Forget Link Button redirecting to forget password page or not")
	public void verifyForgetPasswordLinkButton() throws InterruptedException {
		homePage.clickOnsignInlink().clickonForgetLink();
		Assert.assertNotSame(driver.getCurrentUrl(), "https://platform.fonada.ai/");
	}

	@AfterMethod(enabled = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
