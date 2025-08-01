package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Util;

public class CreateNewBOT extends Util {

	WebDriver driver;
	WebDriverWait wait;
	CreateNewBOT createNewBOT;
	BasicInfoPage basicInfoPage;
	String botName;

	public CreateNewBOT(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	private final By SELECT_CHANNEL_LOCATOR = By.xpath("//div[text()=\"Select Channel\"]");
	private final By CHAT_BOT_LOCATOR = By.xpath(".//h5[contains(text(),\"Chat Bot (Text Only)\")]");
	private final By CONTINUE_TO_BASIC_INFO_LOCATOR = By.xpath("//button/span[text()='Continue to Basic Info']");
	private final By CHAT_BOT_DYNAMIC_LOCATOR = By.xpath(".//h5[contains(text(),'" + botName + "')]");

	public CreateNewBOT waitTillSelectChannelTextClikable() throws InterruptedException {
		smoothScrollToElement(SELECT_CHANNEL_LOCATOR);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(SELECT_CHANNEL_LOCATOR, "Select Channel"));
		wait.until(ExpectedConditions.elementToBeClickable(SELECT_CHANNEL_LOCATOR)).click();
		createNewBOT = new CreateNewBOT(driver);
		return createNewBOT;
	}

	public CreateNewBOT selectChannel() throws InterruptedException {
		smoothScrollToElement(CHAT_BOT_LOCATOR);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(CHAT_BOT_LOCATOR)).click();
		createNewBOT = new CreateNewBOT(driver);
		return createNewBOT;
	}

	public CreateNewBOT selectChannelByChoice(String botName) throws InterruptedException {
		
		String dynamicXPath = ".//h5[contains(text(),'" + botName + "')]";
		By dynamicLocator = By.xpath(dynamicXPath);
		scrollTillView(dynamicLocator);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(dynamicLocator)).click();
		return this;
	}

	public BasicInfoPage clickOnContinueInfoButton() throws InterruptedException {
		WebElement continueBtn = wait
				.until(ExpectedConditions.presenceOfElementLocated(CONTINUE_TO_BASIC_INFO_LOCATOR));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", continueBtn);
		onClick(CONTINUE_TO_BASIC_INFO_LOCATOR);
		basicInfoPage = new BasicInfoPage(driver);
		return basicInfoPage;
	}
}
