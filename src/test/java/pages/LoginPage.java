package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Util;

public class LoginPage extends Util {

	private WebDriver driver;
	WebDriverWait wait;
	SignUpPage signUpPage;

	private final By EMAIL_ADDRESS_LOCATOR = By.xpath("//input[@name=\"email\"]");
	private final By PASSWORD_LOCATOR = By.xpath("//input[@name=\"password\"]");
	private final By SIGN_IN_LOCATOR = By.xpath("//button[normalize-space()='Sign in']");
	private final By SIGN_UP_LOCATOR = By.xpath("//a[text()='Sign up']");
	private final By INVALID_LOGIN_CREDENTIALS_WARNING_MESSAGE_LOCATOR = By
			.xpath("//span[text()='Invalid login credentials']");
	private final By PASSWORD_REQUIRED_FIELD_VALIDATION_MESSAGE = By
			.xpath("//input[@name='password']/following-sibling::p[contains(text(),'required')]");
	private final By EMAIL_REQUIRED_FIELD_VALIDATION_MESSAGE = By
			.xpath("//input[@name='email']/following-sibling::p[contains(text(),'required')]");
	private final By FORGET_PASSWORD_BUTTON_LINK_LOCATOR = By.partialLinkText("Forgot your password?");

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public LoginPage loginIntoApplication(String email, String password) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_ADDRESS_LOCATOR)).sendKeys(email);
		wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_LOCATOR)).sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(SIGN_IN_LOCATOR)).click();
		return this;
	}

	public SignUpPage navigateTosignUpPage() throws InterruptedException {
		onClick(SIGN_UP_LOCATOR);
		signUpPage = new SignUpPage(driver);
		return signUpPage;
	}

	public String getLoginCredentialsWarningMessage() {
		return getTextFrom(INVALID_LOGIN_CREDENTIALS_WARNING_MESSAGE_LOCATOR);
	}

	public void clickonForgetLink() {
		try {
			onClick(FORGET_PASSWORD_BUTTON_LINK_LOCATOR);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getRequiredFieldMessage(String field) throws InterruptedException {
		// Only click if needed (optional safeguard)
		// onClick(SIGN_IN_LOCATOR);

		By validationLocator;

		switch (field.toLowerCase()) {
		case "email":
			validationLocator = EMAIL_REQUIRED_FIELD_VALIDATION_MESSAGE;
			break;
		case "password":
			validationLocator = PASSWORD_REQUIRED_FIELD_VALIDATION_MESSAGE;
			break;
		default:
			throw new IllegalArgumentException("Unsupported field: " + field);
		}

		return getTextFrom(validationLocator);
	}

}
