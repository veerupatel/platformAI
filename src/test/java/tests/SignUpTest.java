package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.SignUpPage;
import utility.BrowserUtility;
import utility.Util;

public class SignUpTest {

	WebDriver driver;
	SignUpPage signUpPage;
	HomePage homePage;

	@BeforeClass
	public void launchBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "E:\\Browser_driver\\chromedriver-win64\\chromedriver.exe");
		BrowserUtility browserUtility = new BrowserUtility("chrome", false);
		driver = browserUtility.getDriver();
		driver.get("https://platform.fonada.ai/");
		homePage = new HomePage(driver);
		signUpPage = homePage.clickOnsignInlink().navigateTosignUpPage();

	}

	@Test(description = "verify signUp User Functionality", priority = 2)
	public void verify_signUp_User_Functionality() throws InterruptedException {
		signUpPage.signUP("vibha@gmail.com", "Admin@1234", "Agency");
		//Assert.assertTrue(signUpPage.getWarningMessage().isBlank(), "Failed to create account");
	}

	@Test(description = "verify warining message if user not signUp successfully", priority = 1)
	public void verify_warining_message_if_user_not_signUp_successfully() throws InterruptedException {
		signUpPage.signUP(Util.generateRandomEmailText(7), "Admin@1234", "Agency");
		Assert.assertEquals(signUpPage.getWarningMessage(), "Failed to create account");
	}

	@Test(description = "verify validation if user already exists", priority = 0)
	public void verify_validation_if_user_already_exists() throws InterruptedException {
		signUpPage.signUP("vibha@gmail.com", "Admin@1234", "Agency");
		Assert.assertEquals(signUpPage.getWarningMessageForUserAlreadyRegistered(), "User already registered");
	}

	@Test(description = "verify validation if user dont enter any username and password", priority = -1)
	public void verify_validation_if_user_dont_enter_any_username_password() throws InterruptedException {
		Assert.assertEquals(signUpPage.getRequiredFieldMessageForEmail(), "Email is required");
		Assert.assertEquals(signUpPage.getRequiredFieldMessageForPassword(), "Password is required");
	}

	@AfterClass(enabled = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
