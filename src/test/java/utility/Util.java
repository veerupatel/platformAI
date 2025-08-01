package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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
	JavascriptExecutor javascriptExecutor;

	public Util(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public void onClick(By locator) throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		Thread.sleep(1000);
	}

//	public void onClick(By locator) {
//		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
//		wait.until(ExpectedConditions.visibilityOf(element));
//		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
//	}

	public void enterTextInto(By locator, String textToEnter) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		// Clear the text box
		element.clear();

		// Wait until the text box is actually empty
		wait.until(driver -> element.getAttribute("value").isEmpty());

		// Enter the desired text
		element.sendKeys(textToEnter);
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
		List<WebElement> options = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(commonlocator, 1));
		for (WebElement option : options) {
			Thread.sleep(1000);
			if (option.getText().trim().contains(optionToSelect)) {
				Thread.sleep(1000);
				option.click();
				break;
			}
		}
	}

//	public void selectValueFromDropDownList(By dropdownLocator, By optionsLocator, String optionToSelect) throws InterruptedException {
//		// Click to open dropdown
//		onClick(dropdownLocator);
//
//		// Wait for options to load (more than 2 means dropdown is populated)
//		List<WebElement> options = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(optionsLocator, 2));
//
//		// Wait for the desired option to appear and click it
//		for (WebElement option : options) {
//			if (option.getText().trim().equalsIgnoreCase(optionToSelect)) {
//				Thread.sleep(2000);
//				wait.until(ExpectedConditions.elementToBeClickable(option)).click();
//				break;
//			}
//		}
//	}

	public void smoothScrollToElement(By locator) throws InterruptedException {
		Thread.sleep(2000);
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript(
				"arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});",
				waitTillElementPresent(locator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void scrollTillView(By locator) {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});", element);

		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void scrollToBottomSmoothly() {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });");
	}

	public void smoothScrollToElement(WebElement element) throws InterruptedException {
		Thread.sleep(2000);
		javascriptExecutor = (JavascriptExecutor) driver;
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
		return RandomStringUtils.randomAlphabetic(5, 10);
	}

	public static String generateRandomEmailText(int length) {
		return RandomStringUtils.randomAlphanumeric(length) + "@gmail.com";
	}

	public boolean isEnabled(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
	}

	public void scrollToField(By locator) {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		// Scroll element into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

		// Click the element using a clean wait
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public String waitTillNonEmptyText(By locator) {
		return wait.until(driver -> {
			WebElement element = driver.findElement(locator);
			String text = element.getText();
			return (text != null && !text.trim().isEmpty()) ? text : null;
		});
	}

	public WebElement isClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void uploadDocument(String fileLocation, By locator) throws AWTException, InterruptedException {
		onClick(locator);
		String filePath = fileLocation;
		StringSelection stringSelection = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		// Use Robot to simulate Ctrl+V and Enter
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Press Ctrl+V to paste
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		// Press Enter to confirm the file
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

}
