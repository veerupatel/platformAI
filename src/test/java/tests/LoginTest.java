package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.HomePage;
import pages.LoginPage;
import pages.VerticalMenus;
import utility.BrowserUtility;

public class LoginTest {

	WebDriver driver;
	BrowserUtility browserUtility;
	HomePage homePage;
	LoginPage loginPage;
	DashboardPage dashboardPage;
	VerticalMenus verticalMenus;

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
	}

	@Test(description = "Verify if user is able to login with Invalid Credentials", priority = 1)
	public void loginIntoAplicationWithInvalidCredentials() throws InterruptedException {

		Assert.assertEquals(homePage.clickOnsignInlink().loginIntoApplication("admin", "rajesh@gmail.com", "Admin@123")
				.getLoginCredentialsWarningMessage(), "Invalid login credentials");
	}

	@Test(description = "Verify if user is able to login with valid Credentials or not", priority = 2)
	public void loginIntoAplication() throws InterruptedException {
		homePage.clickOnsignInlink().loginIntoApplication("admin", "rajesh@gmail.com", "Admin@1234");
		Assert.assertTrue(dashboardPage.getLoggedInUserName("rajesh").equalsIgnoreCase("rajesh"));
	}

	@Test(description = "verify validation if user don't enter any username and password", priority = 0)
	public void verify_validation_if_user_dont_enter_any_username_password() throws InterruptedException {
		loginPage = homePage.clickOnsignInlink().loginIntoApplication("admin", "", "");
		Assert.assertEquals(loginPage.getRequiredFieldMessage("email"), "Email is required");
		Assert.assertEquals(loginPage.getRequiredFieldMessage("password"), "Password is required");
	}

	@Test(description = "Verify Forget Link Button redirecting to forget password page or not")
	public void verifyForgetPasswordLinkButton() throws InterruptedException {
		homePage.clickOnsignInlink().clickonForgetLink();
		Assert.assertNotSame(driver.getCurrentUrl(), "https://platform.fonada.ai/");
	}

	@Test(description = "Verify if user able to login with gmail account")
	public void loginWithGmailAccount() throws InterruptedException {
		String username = homePage.clickOnsignInlink().loginIntoApplicationByUsingGmailAccount("biraju@fonada.com",
				"Saanvi@2021");
		Assert.assertEquals(username, "biraju");
		String role = "superadmin";

		// Define expected menus based on role
		List<String> expectedMenus = new ArrayList<>();

		switch (role.toLowerCase()) {
		case "user":
			expectedMenus = Arrays.asList("Dashboard", "Business Overview", "AI Agents", "Agent Builder",
					"Analytics Config", "WhatsApp Platform", "Campaigns", "Templates", "Social Media", "AI Studio",
					"Services", "Number Management", "API Keys", "Billing", "Settings");
			break;

		case "admin":
		case "superadmin":
			expectedMenus = Arrays.asList("Dashboard", "Business Overview", "AI Agents", "Agent Builder",
					"Analytics Config", "WhatsApp Platform", "Campaigns", "Templates", "Social Media", "AI Studio",
					"Services", "Number Management", "API Keys", "Billing", "Settings", "Admin Panel");
			break;

		default:
			Assert.fail("Unexpected role extracted from email: " + role);
		}

		// Get actual vertical menu items from UI
		List<WebElement> verticalMenuElements = verticalMenus.getAllVerticalMenusName();
		List<String> actualMenus = verticalMenuElements.stream().map(WebElement::getText)
				.filter(text -> !text.trim().isEmpty()).collect(Collectors.toList());

		// Debug logs
		System.out.println("Role: " + role);
		System.out.println("Expected: " + expectedMenus);
		System.out.println("Actual:   " + actualMenus);

		// Assertion
		Assert.assertEquals(actualMenus, expectedMenus, "Vertical menu items do not match for role: " + role);
	}

	@AfterMethod(enabled = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
