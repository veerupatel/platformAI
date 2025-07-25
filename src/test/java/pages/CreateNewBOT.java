package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateNewBOT {

	WebDriver driver;
	WebDriverWait wait;
	CreateNewBOT createNewBOT;
	BasicInfoPage basicInfoPage;
	String botName;

	public CreateNewBOT(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	private final By SELECT_CHANNEL_LOCATOR = By.xpath("//div[text()=\"Select Channel\"]");
	private final By CHAT_BOT_LOCATOR = By.xpath(".//h5[contains(text(),\"Chat Bot (Text Only)\")]");
	private final By CONTINUE_TO_BASIC_INFO_LOCATOR = By.xpath("//span[text()='Continue to Basic Info']");
	private final By CHAT_BOT_DYNAMIC_LOCATOR = By.xpath(".//h5[contains(text(),'" + botName + "')]");
	

	public CreateNewBOT waitTillSelectChannelTextClikable() {

		wait.until(ExpectedConditions.textToBePresentInElementLocated(SELECT_CHANNEL_LOCATOR, "Select Channel"));
		wait.until(ExpectedConditions.elementToBeClickable(SELECT_CHANNEL_LOCATOR)).click();
		createNewBOT = new CreateNewBOT(driver);
		return createNewBOT;
	}

	public CreateNewBOT selectChannel() {
		wait.until(ExpectedConditions.elementToBeClickable(CHAT_BOT_LOCATOR)).click();
		createNewBOT = new CreateNewBOT(driver);
		return createNewBOT;
	}
	
	public CreateNewBOT selectChannelByChoice(String botName) {
	    String dynamicXPath = ".//h5[contains(text(),'" + botName + "')]";
	    By dynamicLocator = By.xpath(dynamicXPath);

	    wait.until(ExpectedConditions.elementToBeClickable(dynamicLocator)).click();
	    return this;
	}


	public BasicInfoPage clickOnContinueInfoButton() {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(CONTINUE_TO_BASIC_INFO_LOCATOR,
				"Continue to Basic Info"));
		wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_TO_BASIC_INFO_LOCATOR)).click();
		basicInfoPage = new BasicInfoPage(driver);
		return basicInfoPage;
	}
}
