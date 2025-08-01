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
	BasicInfoPage basicInfoPage;

	public BasicInfoPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	private final By BASIC_INFO_LOCATOR = By.xpath("//div[text()=\"Basic Info\"]");
	private final By BOT_NAME_LOCATOR = By.xpath("//input[@id=\"name\"]");
	private final By DESCRIPTION_TEMPLATE_LOCATOR = By.xpath("(.//div[contains(@class,'ant-select-selector')])[1]");
	private final By LLM_PROVIDER_LOCATOR = By.xpath("//span[@title='Groq']");
	private final By LLM_MODEL_LOCATOR = By.cssSelector("div:nth-child(8) div:nth-child(1) div:nth-child(2) div:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(1) span:nth-child(1) span:nth-child(2)");
	private final By AGENT_TYPE = By.cssSelector("input#agent_type");
	private final By LANGUAGES_SELECTOR_LOCATOR = By.xpath("(.//div[contains(@class,'ant-select-selector')])[4]");
	private final By LANGUAGES_SUPPORTED_SELECTOR_LOCATOR = By
			.xpath("(.//div[contains(@class,'ant-select-selector')])[8]");
	private final By LANGUAGE_LABEL_LOCATOR = By.xpath("//div/label[@for=\"languages\"]");
	private final By COMMON_XPATH_LOCATOR = By.xpath(".//div[contains(@class,'ant-select-item-option-content')]");
	private final By LLM_COMMON_LOCATOR = By
			.xpath("//div[@class=\"rc-virtual-list-holder-inner\"]//div[@class=\"ant-select-item-option-content\"]");
	private final By LLM_PROVIDER_LABEL_LOCATOR = By.xpath("//label[@title=\"LLM Provider\"]");
	private final By DESCRIPTION_LOCATOR = By.xpath("//textarea[@id=\"description\"]");
	private final By SYSTEM_PROMPT_LOCATOR = By.xpath("//textarea[@placeholder=\"System prompt for LLM\"]");
	private final By TURN_TIME_OUT_LOCATOR = By.xpath("//input[@id=\"turn_timeout\"]");
	private final By CREATE_AGENT_BUTTON_LOCATOR = By
			.xpath("//div[@class=\"ant-form-item-control-input-content\"]//button[@type=\"submit\"]");
	private final By SYSTEM_PROMPT_LABEL_LOCATOR = By.xpath("//span[text()='System Prompt']");
//	private final By LLM_PROVIDER_COMMON_LOCATOR = By
//			.xpath("//div[@class='rc-virtual-list-holder-inner']//div[@class='ant-select-item-option-content']");

	private final By FIRST_MESSAGE_LABEL_LOCATOR = By.xpath("//label[@title=\"First Message\"]");
	private final By LLM_MODEL_LABEL_LOCATOR = By.xpath("//label[@title=\"LLM Model\"]");
	private final By TTS_MODEL_LOCATOR = By.xpath("(//span[text()='Fonada Labs'])[1]");
	private final By VOICE_NAME_LOCATOR = By.xpath("//input[@id=\"tts_config_voice_name\"]");
	private final By TEST_VOICE_BUTTON_LOCATOR = By
			.xpath("//button[@type=\"button\"]//span[contains(text(),'Test Voice')]");
	private final By STOP_TEST_VOICE_BUTTON_LOCATOR = By
			.xpath("//button[@type=\"button\"]//span[contains(text(),'Stop')]");
	private final By FIRST_MESSAGE_TEXTBOX_LOCATOR = By.cssSelector("input#first_message");
	private final By SELECT_TEMPLATE_LABEL_LOCATOR = By.xpath("//h4[text()=\"Select Template\"]");

	public BasicInfoPage WaitTillBasicInfoBoxIsVisibleAndEnable() throws InterruptedException {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(BASIC_INFO_LOCATOR, "Basic Info"));
		onClick(BASIC_INFO_LOCATOR);
		// onClick(BASIC_INFO_LOCATOR);
		basicInfoPage = new BasicInfoPage(driver);
		return basicInfoPage;
	}

	public KnowledgeBasePage fillBasicInfoOfBOT() throws InterruptedException {

		Util util = new Util(driver);
		wait.until(ExpectedConditions.elementToBeClickable(BOT_NAME_LOCATOR)).sendKeys(util.generateRandomText(10));
		scrollTillView(DESCRIPTION_LOCATOR);
		util.selectValueFromDropDownList(DESCRIPTION_TEMPLATE_LOCATOR, COMMON_XPATH_LOCATOR, "Customer Support Bot");
		waitTillTextBoxIsFilled(DESCRIPTION_LOCATOR);
		smoothScrollToElement(FIRST_MESSAGE_LABEL_LOCATOR);
		util.selectValueFromDropDownList(LLM_PROVIDER_LOCATOR, COMMON_XPATH_LOCATOR, "OpenAI");
		util.selectValueFromDropDownList(LLM_MODEL_LOCATOR, LLM_COMMON_LOCATOR, "GPT-4o (Multimodal)");

		scrollTillView(SYSTEM_PROMPT_LABEL_LOCATOR);
		// util.selectValueFromDropDownList(LANGUAGES_SELECTOR_LOCATOR,
		// COMMON_XPATH_LOCATOR, "English");
		// scrollToField(LANGUAGE_LABEL_LOCATOR);
		enterTextInto(FIRST_MESSAGE_TEXTBOX_LOCATOR, "This is my first bot");
		enterTextInto(SYSTEM_PROMPT_LOCATOR,
				"A friendly and knowledgeable agent that helps users resolve their queries and provides information about our products and services.");
		enterTextInto(TURN_TIME_OUT_LOCATOR, "60");
		scrollTillView(CREATE_AGENT_BUTTON_LOCATOR);
		onClick(CREATE_AGENT_BUTTON_LOCATOR);
		knowledgeBasePage = new KnowledgeBasePage(driver);
		return knowledgeBasePage;
	}

	public KnowledgeBasePage fillBasicInfoOfBOTDynamic(String channelType) throws InterruptedException {
		Util util = new Util(driver);
		String randomBotName = util.generateRandomText(10);
		wait.until(ExpectedConditions.elementToBeClickable(BOT_NAME_LOCATOR)).sendKeys(randomBotName);

		switch (channelType) {
		case "Chat Bot":
			scrollTillView(DESCRIPTION_LOCATOR);
			util.selectValueFromDropDownList(DESCRIPTION_TEMPLATE_LOCATOR, COMMON_XPATH_LOCATOR,
					"Customer Support Bot");
			waitTillTextBoxIsFilled(DESCRIPTION_LOCATOR);
			util.selectValueFromDropDownList(LLM_PROVIDER_LOCATOR, COMMON_XPATH_LOCATOR, "OpenAI");
			util.selectValueFromDropDownList(LLM_MODEL_LOCATOR, LLM_COMMON_LOCATOR, "GPT-4o (Multimodal)");
			scrollTillView(SYSTEM_PROMPT_LABEL_LOCATOR);
			enterTextInto(FIRST_MESSAGE_TEXTBOX_LOCATOR, "This is my first bot");
			enterTextInto(SYSTEM_PROMPT_LOCATOR,
					"A friendly and knowledgeable agent that helps users resolve their queries and provides information about our products and services.");
			enterTextInto(TURN_TIME_OUT_LOCATOR, "60");
			scrollTillView(CREATE_AGENT_BUTTON_LOCATOR);
			onClick(CREATE_AGENT_BUTTON_LOCATOR);
			return new KnowledgeBasePage(driver);
		case "Web Widget":
			scrollTillView(DESCRIPTION_LOCATOR);
			util.selectValueFromDropDownList(DESCRIPTION_TEMPLATE_LOCATOR, COMMON_XPATH_LOCATOR,
					"Customer Support Bot");
			waitTillTextBoxIsFilled(DESCRIPTION_LOCATOR);
			util.selectValueFromDropDownList(LLM_PROVIDER_LOCATOR, COMMON_XPATH_LOCATOR, "OpenAI");
			util.selectValueFromDropDownList(LLM_MODEL_LOCATOR, LLM_COMMON_LOCATOR, "GPT-4 Turbo");
			scrollTillView(LANGUAGE_LABEL_LOCATOR);
			util.selectValueFromDropDownList(LANGUAGES_SUPPORTED_SELECTOR_LOCATOR, COMMON_XPATH_LOCATOR, "English");
			onClick(LANGUAGE_LABEL_LOCATOR);
			scrollTillView(SYSTEM_PROMPT_LABEL_LOCATOR);
			enterTextInto(FIRST_MESSAGE_TEXTBOX_LOCATOR, "This is my first bot");
			enterTextInto(SYSTEM_PROMPT_LOCATOR,
					"A friendly and knowledgeable agent that helps users resolve their queries and provides information about our products and services.");
			enterTextInto(TURN_TIME_OUT_LOCATOR, "60");
			scrollTillView(CREATE_AGENT_BUTTON_LOCATOR);
			onClick(CREATE_AGENT_BUTTON_LOCATOR);
			return new KnowledgeBasePage(driver);

		case "Telephony":
			scrollTillView(DESCRIPTION_LOCATOR);
			util.selectValueFromDropDownList(DESCRIPTION_TEMPLATE_LOCATOR, COMMON_XPATH_LOCATOR,
					"Customer Support Bot");
			waitTillTextBoxIsFilled(DESCRIPTION_LOCATOR);
			util.selectValueFromDropDownList(AGENT_TYPE, COMMON_XPATH_LOCATOR, "Inbound Agent");
			scrollTillView(LLM_MODEL_LOCATOR);
			Thread.sleep(1000);
			util.selectValueFromDropDownList(LLM_PROVIDER_LOCATOR, COMMON_XPATH_LOCATOR, "OpenAI");
			util.selectValueFromDropDownList(LLM_MODEL_LOCATOR, LLM_COMMON_LOCATOR, "GPT-4o Mini");
			scrollTillView(LANGUAGE_LABEL_LOCATOR);
			util.selectValueFromDropDownList(LANGUAGES_SUPPORTED_SELECTOR_LOCATOR, COMMON_XPATH_LOCATOR, "English");
			// onClick(LANGUAGE_LABEL_LOCATOR);
			// onClick(LANGUAGE_LABEL_LOCATOR);
			scrollTillView(SYSTEM_PROMPT_LABEL_LOCATOR);
			enterTextInto(FIRST_MESSAGE_TEXTBOX_LOCATOR, "This is my first bot");
			enterTextInto(SYSTEM_PROMPT_LOCATOR,
					"A friendly and knowledgeable agent that helps users resolve their queries and provides information about our products and services.");
			enterTextInto(TURN_TIME_OUT_LOCATOR, "60");
			scrollTillView(CREATE_AGENT_BUTTON_LOCATOR);
			onClick(CREATE_AGENT_BUTTON_LOCATOR);
			return new KnowledgeBasePage(driver);

		case "RCS":
			scrollTillView(DESCRIPTION_LOCATOR);
			util.selectValueFromDropDownList(DESCRIPTION_TEMPLATE_LOCATOR, COMMON_XPATH_LOCATOR,
					"Customer Support Bot");
			waitTillTextBoxIsFilled(DESCRIPTION_LOCATOR);
			scrollTillView(AGENT_TYPE);
			Thread.sleep(1000);
			util.selectValueFromDropDownList(AGENT_TYPE, COMMON_XPATH_LOCATOR, "Inbound Agent");
			Thread.sleep(1000);
			scrollTillView(LLM_MODEL_LOCATOR);
			Thread.sleep(1000);
			util.selectValueFromDropDownList(LLM_PROVIDER_LOCATOR, COMMON_XPATH_LOCATOR, "OpenAI");
			// scrollTillView(LLM_MODEL_LOCATOR);
			Thread.sleep(1000);
			util.selectValueFromDropDownList(LLM_MODEL_LOCATOR, LLM_COMMON_LOCATOR, "GPT-3.5 Turbo");
			scrollTillView(SYSTEM_PROMPT_LABEL_LOCATOR);
			enterTextInto(FIRST_MESSAGE_TEXTBOX_LOCATOR, "This is my first bot");
			enterTextInto(SYSTEM_PROMPT_LOCATOR,
					"A friendly and knowledgeable agent that helps users resolve their queries and provides information about our products and services.");
			enterTextInto(TURN_TIME_OUT_LOCATOR, "60");
			scrollTillView(CREATE_AGENT_BUTTON_LOCATOR);
			onClick(CREATE_AGENT_BUTTON_LOCATOR);
			return new KnowledgeBasePage(driver);

		case "WhatsApp Business":
			scrollTillView(DESCRIPTION_LOCATOR);
			util.selectValueFromDropDownList(DESCRIPTION_TEMPLATE_LOCATOR, COMMON_XPATH_LOCATOR,
					"Customer Support Bot");
			waitTillTextBoxIsFilled(DESCRIPTION_LOCATOR);
//			scrollTillView(AGENT_TYPE);
			Thread.sleep(1000);
//			util.selectValueFromDropDownList(AGENT_TYPE, COMMON_XPATH_LOCATOR, "Inbound Agent");
			scrollTillView(LLM_PROVIDER_LOCATOR);
			Thread.sleep(1000);
			util.selectValueFromDropDownList(LLM_PROVIDER_LOCATOR, COMMON_XPATH_LOCATOR, "OpenAI");
			Thread.sleep(1000);
			util.selectValueFromDropDownList(LLM_MODEL_LOCATOR, LLM_COMMON_LOCATOR, "GPT-4.1 nano");
			scrollTillView(SYSTEM_PROMPT_LABEL_LOCATOR);
			enterTextInto(FIRST_MESSAGE_TEXTBOX_LOCATOR, "This is my first bot");
			enterTextInto(SYSTEM_PROMPT_LOCATOR,
					"A friendly and knowledgeable agent that helps users resolve their queries and provides information about our products and services.");
			enterTextInto(TURN_TIME_OUT_LOCATOR, "60");
			scrollTillView(CREATE_AGENT_BUTTON_LOCATOR);
			onClick(CREATE_AGENT_BUTTON_LOCATOR);
			return new KnowledgeBasePage(driver);

		default:
			throw new IllegalArgumentException("Invalid Channel Found: " + channelType);
		}

	}

}
