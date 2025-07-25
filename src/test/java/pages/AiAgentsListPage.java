package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Util;

public class AiAgentsListPage extends Util {

	WebDriver driver;
	WebDriverWait wait;
	CreateNewBOT createNewBOT;

	public AiAgentsListPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	private final By CREATE_NEW_BOT_LOCATOR = By.xpath("//span[text()=\"Create New Bot\"]");
	private final By AGENTS_HANDCROMB_LOCATOR = By.xpath("//span[text()=\"agents\"]");
	private final By AI_AGENTS_LIST_LOCATOR = By.xpath("//div[text()=\"AI Agents\" and @role=\"tab\"]");
	private final By PLAYGROUND_LOCATOR = By.xpath("//div[text()=\"Playground\" and @role=\"tab\"]");
	private final By TOOLS_LOCATOR = By.xpath("//div[text()=\"Tools\" and @role=\"tab\"]");
	private final By TOOLS_LOGS_LOCATOR = By.xpath("//div[text()=\"Tool Logs\" and @role=\"tab\"]");
	private final By CHAT_SUMMARIES_LOCATOR = By.xpath("//div[text()=\"Chat Summaries\" and @role=\"tab\"]");
	private final By CHAT_SESSIONS_LOCATOR = By.xpath("//div[text()=\"Chat Sessions\" and @role=\"tab\"]");
	private final By MEDIA_MANAGER_LOCATOR = By.xpath("//div[text()=\"Media Manager\" and @role=\"tab\"]");
	private final By VOICE_CHAT_LOCATOR = By.xpath("//div[text()=\"Voice Chat\" and @role=\"tab\"]");
	private final By AI_AGENTS_PAGE_HEADER_LOCATOR = By.xpath("//h1[text()=\"AI AGENTS\"]");
	private final By ALL_CHANNELS_DROP_DOWN_LIST_LOCATOR = By.xpath("//div[contains(@class,\"ant-select-single\")]");
	private final By CHANNEL_DROP_DOWN_SEARCH_TEXTBOX_LOCATOR = By
			.xpath("//input[@id=\"rc_select_4\" and @role=\"combobox\"]//parent::span");
	private final By HORIZONTAL_TAB_LISTS = By.xpath("//div[@class=\"ant-tabs-nav-list\"]/div//div");
	private final By SELECT_AN_AGENT_TO_USE_THE_LOCATOR = By.xpath(".//div[contains(text(),\"Select an agent to\")]");
	private final By COMMON_XPATH_LOCATOR = By.xpath("//div[@class=\"ant-select-item-option-content\"]");
	private final By SHOW_RECORD_COUNT_LOCATOR = By
			.xpath("(.//span[contains(@class,\"ant-tag-blue\") and text()=\"Showing: \"])[1]");
	private final By BOT_AGENT_NAME_LOCATOR = By.xpath("(//span[@aria-label='user'])/following-sibling::span");
	private final By LOAD_MORE_LOCATOR = By.xpath("//span[text()=\"Load More\"]");
	private final By LEGENDS_BUTTON_LOCATOR = By
			.xpath("//div[contains(@class,\"ant-space-item\")]//button[@type=\"button\"]");
	private final By BAR_CHART_LOCATOR = By.xpath("(//span[@aria-label=\"bar-chart\"])[2]");
	private final By CHANNEL_TYPE_LOCATOR = By
			.xpath("(//div[contains(@class,'ant-card-body')]//span[contains(normalize-space(), 'Chat Bot')])[1]");
	private final By LEGENDS_BASED_ON_CHANNEL_TYPE_LOCATOR = By.xpath(
			"(//div[contains(@class,'ant-card-body')]//span[contains(normalize-space(), 'Chat Bot')])[1]//following::div[4]//button//span[@role=\"img\"]");

	public CreateNewBOT clickOnCreatenewBotButton() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(CREATE_NEW_BOT_LOCATOR)).click();
		createNewBOT = new CreateNewBOT(driver);
		return createNewBOT;
	}

	public List<String> verifyTextOfAllTabOfAIListForm() throws InterruptedException {
		Util util = new Util(driver);
		List<WebElement> tablist = util.fetchAllOptions(HORIZONTAL_TAB_LISTS);
		List<String> tabNames = new ArrayList<String>();
		for (WebElement tab : tablist) {
			tabNames.add(tab.getText().trim());
		}
		return tabNames;
	}

	public void verifyAllTabOfAIListForm() throws InterruptedException {
		Util util = new Util(driver);

		for (int i = 0; i < util.fetchAllOptions(HORIZONTAL_TAB_LISTS).size(); i++) {
			// Re-fetch tablist every time to avoid stale element issue
			List<WebElement> tablist = util.fetchAllOptions(HORIZONTAL_TAB_LISTS);
			WebElement tab = tablist.get(i);
			String tabName = tab.getText().trim();

			if (!tabName.isEmpty()) {
				WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
				shortWait.until(ExpectedConditions.elementToBeClickable(tab)).click();

				if (i != 0) { // From second tab onward
					try {
						// Wait for the content area to appear
						Thread.sleep(2000);
						WebElement contentElement = wait.until(
								ExpectedConditions.visibilityOfElementLocated(SELECT_AN_AGENT_TO_USE_THE_LOCATOR));

						// Get and trim text
						String message = contentElement.getText().trim();

						if (message.isEmpty()) {
							System.out.println("Text is EMPTY after clicking tab: " + tabName);
						} else {
							System.out.println("Tab '" + tabName + "' shows text: " + message);
						}
					} catch (TimeoutException e) {
						System.out.println("Content element not found after clicking tab: " + tabName);
					}
				}
			}
		}
	}

	public void verifySearchFilter(String textToFilter) throws InterruptedException {
		Util util = new Util(driver);
		util.selectValueFromDropDownList(ALL_CHANNELS_DROP_DOWN_LIST_LOCATOR, COMMON_XPATH_LOCATOR, textToFilter);
	}

	public String verifyTotalRecordCount() {
		Util util = new Util(driver);
		String totalRecordCount = util.getTextFrom(SHOW_RECORD_COUNT_LOCATOR);
		System.out.println("Record count method called");
		return totalRecordCount;
	}

	public void selectEachFilterOptionsOneByOne(String optionToSelect) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ALL_CHANNELS_DROP_DOWN_LIST_LOCATOR)).click();
		List<WebElement> options = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(COMMON_XPATH_LOCATOR, 2));
		for (WebElement option : options) {
			Thread.sleep(1000);
			if (option.getText().trim().contains(optionToSelect)) {
				option.click();

				// Get the total record count
				String recordCount = verifyTotalRecordCount(); // e.g. "Showing: 10 of 25"
				String[] parts = recordCount.split(":|of");
				String firstCountStr = parts[1].trim();
				int recordCounts = Integer.parseInt(firstCountStr);

				System.out.println(optionToSelect + "Before fetched Element's Text    " + recordCounts);

				// ✅ Click "Load More" until all items are loaded (if applicable)
				if (recordCounts >= 10) {
					while (true) {
						try {
							smoothScrollToElement(LOAD_MORE_LOCATOR);
							WebElement loadMore = wait
									.until(ExpectedConditions.visibilityOfElementLocated(LOAD_MORE_LOCATOR));
							loadMore.click();
							String recordCount1 = verifyTotalRecordCount(); // e.g. "Showing: 10 of 25"
							String[] parts1 = recordCount1.split(":|of");
							String firstCountStr1 = parts[1].trim();
							int recordCounts1 = Integer.parseInt(firstCountStr1);

							System.out.println(optionToSelect + "    " + recordCounts1);
							Thread.sleep(2000);
							wait.until(ExpectedConditions.invisibilityOfElementLocated(LOAD_MORE_LOCATOR));
						} catch (Exception e) {
							// Load more not found or done — break the loop
							break;
						}
					}
				}

				// ✅ After all items are loaded, collect and print them
				List<WebElement> botNames = driver.findElements(BOT_AGENT_NAME_LOCATOR);
				for (WebElement bot : botNames) {
					System.out.println(bot.getText());
				}

				break; // Done after selecting the correct option
			}
		}
	}

	public void selectEachFilterOptionsOneByOne2(String optionToSelect) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ALL_CHANNELS_DROP_DOWN_LIST_LOCATOR)).click();
		List<WebElement> options = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(COMMON_XPATH_LOCATOR, 2));

		for (WebElement option : options) {
			Thread.sleep(1000);
			if (option.getText().trim().contains(optionToSelect)) {
				option.click();

				// Track already printed bot names
				// Set<String> seenBotNames = new HashSet<>();

				while (true) {
					// Wait and collect all currently visible bot names
					List<WebElement> botNameElements = driver.findElements(BOT_AGENT_NAME_LOCATOR);
					for (WebElement bot : botNameElements) {
						String botName = bot.getText().trim();
						System.out.println(botName);
//						if (!botName.isEmpty() && !seenBotNames.contains(botName)) {
//							System.out.println(botName);
//							seenBotNames.add(botName);
//						}
					}

					// Try to click Load More if visible, otherwise break
					try {
						smoothScrollToElement(LOAD_MORE_LOCATOR);
						WebElement loadMore = wait
								.until(ExpectedConditions.visibilityOfElementLocated(LOAD_MORE_LOCATOR));
						loadMore.click();
						Thread.sleep(2000);
						smoothScrollToElement(ALL_CHANNELS_DROP_DOWN_LIST_LOCATOR);
					} catch (Exception e) {
						// Load More not present = all items loaded
						break;
					}
				}

				System.out.println(optionToSelect + " --> Total unique bots fetched: "); // + seenBotNames.size());
				break; // Done for current filter option
			}
		}
	}

	public void verifylegendsButton(String textToEnter) throws InterruptedException {
		selectEachFilterOptionsOneByOne(textToEnter);
		smoothScrollToElement(BAR_CHART_LOCATOR);

		List<WebElement> allElements = driver.findElements(LEGENDS_BASED_ON_CHANNEL_TYPE_LOCATOR);
		int maxSize = Math.min(11, allElements.size());
		List<WebElement> first11Elements = allElements.subList(0, maxSize);

		for (WebElement span : first11Elements) {
			WebElement button = span.findElement(By.xpath("./ancestor::button")); // Get parent button
			boolean status = isEnabled(button); // Check button status
			String state = status ? "Enabled" : "Disabled";

			System.out.println(span.getDomAttribute("aria-label") + " Button  " + state);
		}
	}

}
