package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Util;

public class SignUpPage extends Util {

	WebDriver driver;
	String planType;

	public SignUpPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private final By CREATE_YOUR_ACCOUNT_HEADER_LOCATOR = By.xpath("//h2[text()='Create your account']");
	private final By EMAIL_ADDRESS_LOCATOR = By.xpath("//input[@name=\"email\"]");
	private final By PASSWORD_ADDRESS_LOCATOR = By.xpath("//input[@name=\"password\"]");
	private final By SELECT_YOUR_PLAN_LABEL_LOCATOR = By.xpath("//label[contains(text(),\"Select your plan\")]");
	private final By PLAN_OPTIONS_LOCATOR = By.xpath("//div[@class=\"space-y-2\"]//span[contains(text(),\"Free\")]");
	private final By SIGN_UP_BUTTON_LOCATOR = By.xpath("//button[@type='submit'][text()=\"Sign up\"]");
	private final By WARNING_MESSAGE_FOR_FAILED_TO_USER_SIGNUP_LOCATOR = By
			.xpath("//span[text()='Failed to create account']");
	private final By EMAIL_REQUIRED_FIELD_VALIDATION_MESSAGE = By
			.xpath("//label[contains(text(),'Email')]/following-sibling::p[contains(text(),'required')]");
	private final By PASSWORD_REQUIRED_FIELD_VALIDATION_MESSAGE = By
			.xpath("//label[contains(text(),'Password')]/following-sibling::p[contains(text(),'required')]");
	private final By USER_ALREADY_REGISTERED_WARNING_MESSAGE = By.xpath("//span[text()='User already registered']");

	public SignUpPage selectPlanForSignUpNewClient(String plantype) throws InterruptedException {
		waitTillElementPresent(SELECT_YOUR_PLAN_LABEL_LOCATOR);
		onClick(SELECT_YOUR_PLAN_LABEL_LOCATOR);
		SignUpPage signUpPage = new SignUpPage(driver);
		return signUpPage;
	}

	public SignUpPage signUP(String password, String plantype) throws InterruptedException {
		waitTillElementPresent(CREATE_YOUR_ACCOUNT_HEADER_LOCATOR);
		enterTextInto(EMAIL_ADDRESS_LOCATOR, Util.generateRandomEmailText(7));
		enterTextInto(PASSWORD_ADDRESS_LOCATOR, password);
		selectPlanTypeByChoice(plantype);
		scrollTillView(SIGN_UP_BUTTON_LOCATOR);
		onClick(SIGN_UP_BUTTON_LOCATOR);
		SignUpPage signUpPage = new SignUpPage(driver);
		return signUpPage;
	}

	public SignUpPage signUPwithAllreadyExistsUser(String emailAddress, String password, String plantype)
			throws InterruptedException {
		waitTillElementPresent(CREATE_YOUR_ACCOUNT_HEADER_LOCATOR);
		enterTextInto(EMAIL_ADDRESS_LOCATOR, emailAddress);
		enterTextInto(PASSWORD_ADDRESS_LOCATOR, password);
		selectPlanTypeByChoice(plantype);
		scrollTillView(SIGN_UP_BUTTON_LOCATOR);
		onClick(SIGN_UP_BUTTON_LOCATOR);
		SignUpPage signUpPage = new SignUpPage(driver);
		return signUpPage;
	}

	public String getWarningMessage() {
		return getTextFrom(WARNING_MESSAGE_FOR_FAILED_TO_USER_SIGNUP_LOCATOR);
	}

	public String getWarningMessageForUserAlreadyRegistered() {
		return getTextFrom(USER_ALREADY_REGISTERED_WARNING_MESSAGE);
	}

	public SignUpPage selectPlanTypeByChoice(String planType) throws InterruptedException {
		String dynamicXPath = "//div[@class=\"space-y-2\"]//span[contains(text(),'" + planType + "')]";
		By dynamicLocator = By.xpath(dynamicXPath);
		onClick(dynamicLocator);
		return this;
	}

	public String getRequiredFieldMessageForEmail(String plantype) throws InterruptedException {
		selectPlanTypeByChoice(plantype);
		scrollTillView(SIGN_UP_BUTTON_LOCATOR);
		onClick(SIGN_UP_BUTTON_LOCATOR);
		String emailError = getTextFrom(EMAIL_REQUIRED_FIELD_VALIDATION_MESSAGE);
		return emailError;
	}

	public String getRequiredFieldMessageForPassword(String plantype) throws InterruptedException {
		selectPlanTypeByChoice(plantype);
		scrollTillView(SIGN_UP_BUTTON_LOCATOR);
		onClick(SIGN_UP_BUTTON_LOCATOR);
		String passwordError = getTextFrom(PASSWORD_REQUIRED_FIELD_VALIDATION_MESSAGE);
		return passwordError;
	}

}
