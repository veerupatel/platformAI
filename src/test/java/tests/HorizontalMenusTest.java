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
import pages.HorizontalMenus;
import pages.LoginPage;
import pages.VerticalMenus;
import utility.BrowserUtility;

public class HorizontalMenusTest {

	WebDriver driver;
	BrowserUtility browserUtility;
	HomePage homePage;
	LoginPage loginPage;
	DashboardPage dashboardPage;
	HorizontalMenus horizontalMenus;

	@BeforeMethod
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "E:\\Browser_driver\\chromedriver-win64\\chromedriver.exe");
		browserUtility = new BrowserUtility("chrome", false);
		driver = browserUtility.getDriver();
		driver.get("https://platform.fonada.ai");
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		horizontalMenus = new HorizontalMenus(driver);

	}

	@Test(dataProvider = "urserRoles", dataProviderClass = dataproviders.RoleDataProvider.class)
	public void verifyhorizontalMenusListBasedOnRole(String role, String email, String password)
			throws InterruptedException {
		 String username = email.split("@")[0];
		// Define expected menus based on role
		List<String> expectedMenus = new ArrayList<>();

		switch (role.toLowerCase()) {
		case "user":
			expectedMenus = Arrays.asList("Create Agent", "Templates Gallery", "Live Dashboard", "Help Center",username);

			break;

		case "admin":
			expectedMenus = Arrays.asList("Create Agent", "Templates Gallery", "Live Dashboard", "Help Center");
			break;

		case "superadmin":
			expectedMenus = Arrays.asList("Create Agent", "Templates Gallery", "Live Dashboard", "Help Center");
			break;
		}

		// Log in with provided role credentials
		homePage.clickOnsignInlink().loginIntoApplication(role, email, password);

		// Get actual vertical menu items
		List<WebElement> horizontalMenusElements = horizontalMenus.getAllVerticalMenusName();
		List<String> actualMenus = horizontalMenusElements.stream().map(WebElement::getText)
				.filter(text -> !text.trim().isEmpty()).collect(Collectors.toList());

		// Print for debugging
		System.out.println("Role: " + role);
		System.out.println("Expected: " + expectedMenus);
		System.out.println("Actual:   " + actualMenus);

		// Compare expected vs. actual
		Assert.assertEquals(actualMenus, expectedMenus, "Horizontal menu items do not match for role: " + role);
	}
	
	@AfterMethod(enabled = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
