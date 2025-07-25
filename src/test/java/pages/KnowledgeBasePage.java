package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Util;

public class KnowledgeBasePage extends Util {

	WebDriver driver;
	ToolsPage toolsPage;

	public KnowledgeBasePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	By NEXT_STEP_LOCATOR = By.xpath("//span[text()=\"Next Step\"]");

	public ToolsPage clickOnNextStepButton() throws InterruptedException {
		smoothScrollToElement(NEXT_STEP_LOCATOR);
		waitTillElementPresent(NEXT_STEP_LOCATOR);
		onClick(NEXT_STEP_LOCATOR);
		toolsPage = new ToolsPage(driver);
		return toolsPage;
	}
}
