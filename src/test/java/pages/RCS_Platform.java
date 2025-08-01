package pages;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import utility.Util;

public class RCS_Platform extends Util {

	WebDriver driver;
	Util util;

	public RCS_Platform(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.util = new Util(driver);
	}

	By CONNECT_TAB_LOCATOR = By.xpath("//span[normalize-space()=\"Connect\"]");
	By BRAND_NAME_LOCATOR = By.id("brandName");
	By INDUSTRY_TYPE_LOCATOR = By.cssSelector("div.ant-select-selector");
	By COMMON_XPATH_FOR_INDUSTRY_TYPE_LOCATOR = By.cssSelector("div.ant-select-item-option-content");
	By OFFICIAL_BRAND_WEBSITE = By.id("officialBrandWebsite");
	By BRAND_LOGO = By.xpath("//span[text()=\"Click or drag logo to upload\"]");
	By NEXT_BUTTON_LOCATOR = By.xpath("//button[normalize-space()='Next']");
	By FIRST_NAME_LOCATOR = By.id("contactPersonFirstName");
	By LAST_NAME_LOCATOR = By.id("contactPersonLastName");
	By DESIGNATION_LOCATOR = By.id("contactPersonDesignation");
	By EMAIL_LOCATOR = By.id("contactPersonEmail");
	By MOBILE_NUMBER_LOCATOR = By.id("contactPersonMobile");
	By ADDRESS_LINE1_LOCATOR = By.id("addressLine1");
	By ADDRESS_LINE2_LOCATOR = By.id("addressLine2");
	By COUNTRY_LOCATOR = By.id("country");
	By STATE_LOCATOR = By.id("stateProvinceRegion");
	By CITY_LOCATOR = By.id("city");
	By ZIP_POSTALCODE_LOCATOR = By.id("zipCode");
	By TERMS_OF_USE_URL_LOCATOR = By.id("termsOfUseUrl");
	By PRIVACY_POLICY_URL_LOCATOR = By.id("privacyPolicyUrl");
	By PRIMARY_PHONE_NUMBER_LOCATOR = By.id("primaryPhoneNumber");
	By PRIMARY_PHONE_LABEL_LOCATOR = By.id("primaryPhoneLabel");
	By PRIMARY_EMAIL_ID_LOCATOR = By.id("primaryEmailId");
	By PRIMARY_EMAIL_LABEL_LOCATOR = By.id("primaryEmailLabel");
	By PRIMARY_WEBSITE_LOCATOR = By.id("primaryWebsite");
	By PRIMARY_WEBSITE_LABEL_LOCATOR = By.id("primaryWebsiteLabel");
	By GST_CERTIFICATE_LOCATOR = By.xpath("//span[text()=\"Upload GST Certificate\"]");
	By PAN_CARD_LOCATOR = By.xpath("//span[text()=\"Upload PAN Card\"]");
	By SUBMIT_REGISTRATION_LOCATOR = By.xpath("//button[normalize-space()=\"Submit Registration\"]");

	public void fillBrandInformation(String brandName, String industryType, String brandURL, String fileLocaton)
			throws InterruptedException, AWTException {

		scrollTillView(OFFICIAL_BRAND_WEBSITE);
		Thread.sleep(3000);
		enterTextInto(BRAND_NAME_LOCATOR, brandName);
		driver.findElement(BRAND_NAME_LOCATOR).sendKeys(Keys.TAB);
		selectValueFromDropDownList(INDUSTRY_TYPE_LOCATOR, COMMON_XPATH_FOR_INDUSTRY_TYPE_LOCATOR, industryType);
		enterTextInto(OFFICIAL_BRAND_WEBSITE, brandURL);
		util.uploadDocument(fileLocaton, BRAND_LOGO);
		scrollTillView(NEXT_BUTTON_LOCATOR);
		onClick(NEXT_BUTTON_LOCATOR);
	}

	public void fillcontactPersonInformation(String firstName, String lastName, String designation, String email,
			String number) throws InterruptedException {

		scrollTillView(FIRST_NAME_LOCATOR);
		Thread.sleep(3000);
		enterTextInto(FIRST_NAME_LOCATOR, firstName);
		enterTextInto(LAST_NAME_LOCATOR, lastName);
		enterTextInto(DESIGNATION_LOCATOR, designation);
		enterTextInto(EMAIL_LOCATOR, email);
		enterTextInto(MOBILE_NUMBER_LOCATOR, number);
		onClick(NEXT_BUTTON_LOCATOR);
	}

	public void fillcompanyAddress(String addressline1, String addressline2, String country, String state, String city,
			String zip) throws InterruptedException {

		scrollTillView(ADDRESS_LINE1_LOCATOR);
		Thread.sleep(3000);
		enterTextInto(ADDRESS_LINE1_LOCATOR, addressline1);
		enterTextInto(ADDRESS_LINE2_LOCATOR, addressline2);
		enterTextInto(COUNTRY_LOCATOR, country);
		scrollTillView(ZIP_POSTALCODE_LOCATOR);
		enterTextInto(STATE_LOCATOR, state);
		enterTextInto(CITY_LOCATOR, city);
		enterTextInto(ZIP_POSTALCODE_LOCATOR, zip);
		onClick(NEXT_BUTTON_LOCATOR);
	}

	public void filllegalURLAndContactDetails(String termsURL, String privacypolicy, String primaryphone,
			String primaryphonelabel, String primaryEmailId, String primaryEmailLabel, String primaryWebSite,
			String primaryWebsiteLabel) throws InterruptedException {

		scrollTillView(TERMS_OF_USE_URL_LOCATOR);
		Thread.sleep(3000);
		enterTextInto(TERMS_OF_USE_URL_LOCATOR, termsURL);
		enterTextInto(PRIVACY_POLICY_URL_LOCATOR, privacypolicy);
		enterTextInto(PRIMARY_PHONE_NUMBER_LOCATOR, primaryphone);
		enterTextInto(PRIMARY_PHONE_LABEL_LOCATOR, primaryphonelabel);
		scrollTillView(NEXT_BUTTON_LOCATOR);
		enterTextInto(PRIMARY_EMAIL_ID_LOCATOR, primaryEmailId);
		enterTextInto(PRIMARY_EMAIL_LABEL_LOCATOR, primaryEmailLabel);
		enterTextInto(PRIMARY_WEBSITE_LOCATOR, primaryWebSite);
		enterTextInto(PRIMARY_WEBSITE_LABEL_LOCATOR, primaryWebsiteLabel);
		scrollTillView(NEXT_BUTTON_LOCATOR);
		onClick(NEXT_BUTTON_LOCATOR);
	}

	public void uploadDocumentsIntoUploadSection(String GSTfileLocation, String PanfileLocation)
			throws AWTException, InterruptedException {
		uploadDocument(GSTfileLocation, GST_CERTIFICATE_LOCATOR);
		uploadDocument(PanfileLocation, PAN_CARD_LOCATOR);
	}

	public void submitRegistration() throws InterruptedException {
		scrollTillView(SUBMIT_REGISTRATION_LOCATOR);
		onClick(SUBMIT_REGISTRATION_LOCATOR);
	}
}
