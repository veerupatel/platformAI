package utility;

import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {

	WebDriver driver;
	WebDriverWait wait;

	public Util(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public void onClick(By locator) throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public void enterTextInto(By locator, String textToEnter) {
		clearTextFromTextBox(locator);
		wait.until(ExpectedConditions.elementToBeClickable(locator)).sendKeys(textToEnter);
	}

	public void clearTextFromTextBox(By locator) {
		WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();
	}

	public void textToAppear(By locator, String textToVisible) {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, textToVisible));
	}

	public WebElement waitTillElementPresent(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public List<WebElement> fetchAllOptions(By locator) {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public boolean checkTextboxEmptyOrNot(By locator) {
		boolean value = false;
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		if (!element.getDomAttribute("value").isEmpty()) {
			value = true;
		} else {
			value = false;
		}
		return value;
	}

	public void selectValueFromDropDownList(By locator, By commonlocator, String optionToSelect)
			throws InterruptedException {
		onClick(locator);
		List<WebElement> options = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(commonlocator, 2));
		for (WebElement option : options) {
			Thread.sleep(1000);
			if (option.getText().trim().contains(optionToSelect)) {
				Thread.sleep(1000);
				option.click();
				break;
			}
		}
	}

	public void smoothScrollToElement(By locator) throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript(
				"arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});",
				waitTillElementPresent(locator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void smoothScrollToElement(WebElement element) throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript(
				"arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});", element);

	}

	public void waitTillTextBoxIsFilled(By locator) {
		WebElement descriptionBox = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		wait.until(driver -> {
			String value = descriptionBox.getAttribute("value");
			return value != null && !value.trim().isEmpty();
		});
	}

	public String getTextFrom(By locator) {
		return waitTillElementPresent(locator).getText();

	}

	public String generateRandomText(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}
	
	public static String generateRandomEmailText(int length) {
		return RandomStringUtils.randomAlphanumeric(length)+"@gmail.com";
	}

	public boolean isEnabled(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
	}

}
