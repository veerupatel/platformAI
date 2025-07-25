package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Util;

public class ToolsPage extends Util {

	WebDriver driver;

	public ToolsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	By AGENT_DEPLOY_BUTTON_LOCATOR = By.xpath("//span[text()=\"Deploy Agent\"]");

	public void clickOnAgentDeployButton() throws InterruptedException {
		smoothScrollToElement(AGENT_DEPLOY_BUTTON_LOCATOR);
		waitTillElementPresent(AGENT_DEPLOY_BUTTON_LOCATOR);
		onClick(AGENT_DEPLOY_BUTTON_LOCATOR);
		
	}
}
