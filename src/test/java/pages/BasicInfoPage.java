package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Util;

public class BasicInfoPage extends Util {

	WebDriver driver;
	WebDriverWait wait;
	KnowledgeBasePage knowledgeBasePage;

	public BasicInfoPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	private final By BASIC_INFO_LOCATOR = By.xpath("//div[text()=\"Basic Info\"]");
	private final By BOT_NAME_LOCATOR = By.xpath("//input[@id=\"name\"]");
	private final By DESCRIPTION_TEMPLATE_LOCATOR = By.xpath("(.//div[contains(@class,'ant-select-selector')])[1]");
	private final By LLM_PROVIDER_LOCATOR = By.xpath("(.//div[contains(@class,'ant-select-selector')])[2]");
	private final By LLM_MODEL_LOCATOR = By.xpath("(.//div[contains(@class,'ant-select-selector')])[3]");
	private final By LANGUAGES_SELECTOR_LOCATOR = By.xpath("(.//div[contains(@class,'ant-select-selector')])[4]");
	private final By COMMON_XPATH_LOCATOR = By.xpath(".//div[contains(@class,'ant-select-item-option-content')]");
	private final By LLM_PROVIDER_LABEL_LOCATOR = By.xpath("//label[@title=\"LLM Provider\"]");
	private final By DESCRIPTION_LOCATOR = By.xpath("//textarea[@id=\"description\"]");
	private final By SYSTEM_PROMPT_LOCATOR = By.xpath("//textarea[@placeholder=\"System prompt for LLM\"]");
	private final By TURN_TIME_OUT_LOCATOR = By.xpath("//input[@id=\"turn_timeout\"]");
	private final By CREATE_AGENT_BUTTON_LOCATOR = By
			.xpath("//div[@class=\"ant-form-item-control-input-content\"]//button[@type=\"submit\"]");
	private final By SYSTEM_PROMPT_LABEL_LOCATOR = By.xpath("//span[text()='System Prompt']");
	private final By LLM_PROVIDER_COMMON_LOCATOR = By
			.xpath("//div[@class='rc-virtual-list-holder-inner']//div[@class='ant-select-item-option-content']");
	private final By FIRST_MESSAGE_LABEL_LOCATOR = By.xpath("//label[@title=\"First Message\"]");
	private final By LLM_MODEL_LABEL_LOCATOR = By.xpath("//label[@title=\"LLM Model\"]");
	private final By TTS_MODEL_LOCATOR = By.xpath("(//span[text()='Fonada Labs'])[1]");
	private final By VOICE_NAME_LOCATOR = By.xpath("//input[@id=\"tts_config_voice_name\"]");
	private final By TEST_VOICE_BUTTON_LOCATOR = By
			.xpath("//button[@type=\"button\"]//span[contains(text(),'Test Voice')]");
	private final By STOP_TEST_VOICE_BUTTON_LOCATOR = By
			.xpath("//button[@type=\"button\"]//span[contains(text(),'Stop')]");

	public BasicInfoPage WaitTillBasicInfoBoxIsVisibleAndEnable() {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(BASIC_INFO_LOCATOR, "Basic Info"));
		wait.until(ExpectedConditions.elementToBeClickable(BASIC_INFO_LOCATOR)).click();
		BasicInfoPage basicInfoPage = new BasicInfoPage(driver);
		return basicInfoPage;
	}

	public KnowledgeBasePage fillBasicInfoOfBOT() throws InterruptedException {

		Util util = new Util(driver);
		wait.until(ExpectedConditions.elementToBeClickable(BOT_NAME_LOCATOR)).sendKeys(util.generateRandomText(10));
		smoothScrollToElement(DESCRIPTION_LOCATOR);
		Thread.sleep(1000);
		util.selectValueFromDropDownList(DESCRIPTION_TEMPLATE_LOCATOR, COMMON_XPATH_LOCATOR, "Customer Support Bot");
		waitTillTextBoxIsFilled(DESCRIPTION_LOCATOR);
		Thread.sleep(1000);
		smoothScrollToElement(FIRST_MESSAGE_LABEL_LOCATOR);
		util.selectValueFromDropDownList(LLM_PROVIDER_LOCATOR, COMMON_XPATH_LOCATOR, "OpenAI");
		util.selectValueFromDropDownList(LLM_MODEL_LOCATOR, COMMON_XPATH_LOCATOR, "GPT-4o (Multimodal)");
		Thread.sleep(2000);
		smoothScrollToElement(SYSTEM_PROMPT_LABEL_LOCATOR);
		util.selectValueFromDropDownList(LANGUAGES_SELECTOR_LOCATOR, COMMON_XPATH_LOCATOR, "English");
		onClick(LLM_MODEL_LABEL_LOCATOR);
		enterTextInto(SYSTEM_PROMPT_LOCATOR,
				"A friendly and knowledgeable agent that helps users resolve their queries and provides information about our products and services.");
		enterTextInto(TURN_TIME_OUT_LOCATOR, "60");
		onClick(CREATE_AGENT_BUTTON_LOCATOR);
		knowledgeBasePage = new KnowledgeBasePage(driver);
		return knowledgeBasePage;
	}
	
	public KnowledgeBasePage fillBasicInfoOfDynamicBOT(String choice) throws InterruptedException {

		Util util = new Util(driver);
		wait.until(ExpectedConditions.elementToBeClickable(BOT_NAME_LOCATOR)).sendKeys(util.generateRandomText(10));
		smoothScrollToElement(DESCRIPTION_LOCATOR);
		Thread.sleep(1000);
		util.selectValueFromDropDownList(DESCRIPTION_TEMPLATE_LOCATOR, COMMON_XPATH_LOCATOR, "Customer Support Bot");
		waitTillTextBoxIsFilled(DESCRIPTION_LOCATOR);
		Thread.sleep(1000);
		smoothScrollToElement(FIRST_MESSAGE_LABEL_LOCATOR);
		util.selectValueFromDropDownList(LLM_PROVIDER_LOCATOR, COMMON_XPATH_LOCATOR, "OpenAI");
		util.selectValueFromDropDownList(LLM_MODEL_LOCATOR, COMMON_XPATH_LOCATOR, "GPT-4o (Multimodal)");
		Thread.sleep(2000);
		smoothScrollToElement(SYSTEM_PROMPT_LABEL_LOCATOR);
		util.selectValueFromDropDownList(LANGUAGES_SELECTOR_LOCATOR, COMMON_XPATH_LOCATOR, "English");
		onClick(LLM_MODEL_LABEL_LOCATOR);
		enterTextInto(SYSTEM_PROMPT_LOCATOR,
				"A friendly and knowledgeable agent that helps users resolve their queries and provides information about our products and services.");
		enterTextInto(TURN_TIME_OUT_LOCATOR, "60");
		onClick(CREATE_AGENT_BUTTON_LOCATOR);
		knowledgeBasePage = new KnowledgeBasePage(driver);
		return knowledgeBasePage;
	}

}
