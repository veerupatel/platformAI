package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Util;

public class HomePage extends Util {

	WebDriver driver;
	LoginPage loginPage;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//button[normalize-space()='Sign In']");

	public LoginPage clickOnsignInlink() throws InterruptedException {
		onClick(SIGN_IN_BUTTON_LOCATOR);
		loginPage = new LoginPage(driver);
		return loginPage;
	}

}
