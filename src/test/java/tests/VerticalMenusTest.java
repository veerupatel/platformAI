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

public class VerticalMenusTest {

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

	@Test(dataProvider = "urserRoles", dataProviderClass = dataproviders.RoleDataProvider.class)
	public void verifyVerticalMenusListBasedOnRole(String role, String email, String password)
			throws InterruptedException {

		// Define expected menus based on role
		List<String> expectedMenus = new ArrayList<>();

		switch (role.toLowerCase()) {
		case "user":
			expectedMenus = Arrays.asList("Dashboard", "Business Overview", "AI Agents", "Agent Builder",
					"Analytics Config", "WhatsApp Platform", "Campaigns", "Templates", "Social Media", "AI Studio",
					"Services", "Number Management", "API Keys", "Billing", "Settings");
			break;

		case "admin":
			expectedMenus = Arrays.asList("Dashboard", "Business Overview", "AI Agents", "Agent Builder",
					"Analytics Config", "WhatsApp Platform", "Campaigns", "Templates", "Social Media", "AI Studio",
					"Services", "Number Management", "API Keys", "Billing", "Settings", "Admin Panel");
			break;

		case "superadmin":
			expectedMenus = Arrays.asList("Dashboard", "Business Overview", "AI Agents", "Agent Builder",
					"Analytics Config", "WhatsApp Platform", "Campaigns", "Templates", "Social Media", "AI Studio",
					"Services", "Number Management", "API Keys", "Billing", "Settings", "Admin Panel",
					"Super Admin Tools");
			break;
		}

		// Log in with provided role credentials
		homePage.clickOnsignInlink().loginIntoApplication(role, email, password);

		// Get actual vertical menu items
		List<WebElement> verticalMenuElements = verticalMenus.getAllVerticalMenusName();
		List<String> actualMenus = verticalMenuElements.stream().map(WebElement::getText)
				.filter(text -> !text.trim().isEmpty()).collect(Collectors.toList());

		// Print for debugging
		System.out.println("Role: " + role);
		System.out.println("Expected: " + expectedMenus);
		System.out.println("Actual:   " + actualMenus);

		// Compare expected vs. actual
		Assert.assertEquals(actualMenus, expectedMenus, "Vertical menu items do not match for role: " + role);
	}
	
	@AfterMethod(enabled = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
