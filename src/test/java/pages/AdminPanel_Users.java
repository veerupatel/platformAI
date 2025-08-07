package pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Util;

public class AdminPanel_Users extends Util {

	WebDriver driver;
	WebDriverWait wait;

	public AdminPanel_Users(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	By adminPanelTabLocator = By.xpath("//span[@style]//parent::div[@role=\"tab\"]");
	By userRowCountLocator = By.xpath("//table[@style]//tbody//tr[contains(@class,\"user-row\")]");

	public List<String> verifyAdminPanelAllTabList() {
		List<WebElement> tablist = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(adminPanelTabLocator, 2));
		List<String> actualTabList = tablist.stream().map(WebElement::getText).collect(Collectors.toList());
		return actualTabList;
	}
}
