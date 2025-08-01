package tests;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AiAgentsListPage;
import pages.DashboardPage;
import pages.LoginPage;

public class AI_AgentListTest {

	WebDriver driver;
	LoginPage loginPage;
	DashboardPage dashboardPage;
	AiAgentsListPage agentsListPage;

	@BeforeClass
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "E:\\Browser_driver\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://platform.fonada.ai/login");
	}

	@Test
	public void login() throws InterruptedException {
		loginPage = new LoginPage(driver);
		loginPage.loginIntoApplication("admin","rajesh@gmail.com", "Admin@1234");

	}

	@Test(dependsOnMethods = "login")
	public void openAiAgentsListForm() throws InterruptedException {
		dashboardPage = new DashboardPage(driver);
		dashboardPage.clickOnAIAgentsMenu();
	}

	@Test(dependsOnMethods = "openAiAgentsListForm", enabled = true)
	public void verifyHorizontalTabLists() throws InterruptedException {
		agentsListPage = new AiAgentsListPage(driver);
		List<String> expectedTabNamesList = Arrays.asList("AI Agents", "Playground", "Tools", "Tool Logs",
				"Chat Summaries", "Chat Sessions", "Media Manager", "Voice Chat");
		List<String> tabNamesList = agentsListPage.verifyTextOfAllTabOfAIListForm();
		Assert.assertEquals(tabNamesList, expectedTabNamesList);
	}

	@Test(description = "Verify On click every Tab agents details showing or not", dependsOnMethods = "openAiAgentsListForm", enabled = true)
	public void verifyAIAgentsListTabWorkingorNot() throws InterruptedException {
		agentsListPage = new AiAgentsListPage(driver);
		agentsListPage.verifyAllTabOfAIListForm();
	}

	@Test(dependsOnMethods = "openAiAgentsListForm", enabled = true)
	public void verifyRecordCountBasedOnSelectedChannelInFilter() throws InterruptedException {
		agentsListPage = new AiAgentsListPage(driver);
		agentsListPage.verifySearchFilter("💬 Chat Bot");
		Thread.sleep(2000);
		String recordCount = agentsListPage.verifyTotalRecordCount();
		System.out.println(recordCount);
	}

	@Test(dataProvider = "filterOptions", dependsOnMethods = "openAiAgentsListForm")
	public void selectEachFilterOptionsOneByOne2(String optionToSelect) throws InterruptedException {
		agentsListPage = new AiAgentsListPage(driver);
		Thread.sleep(2000);
		agentsListPage.selectEachFilterOptionsOneByOne(optionToSelect);
	}

	@DataProvider(name = "filterOptions")
	public Object[][] filterOptionsDataProvider() {
		return new Object[][] { { "All Channels" }, { "Telephony" }, { "Web Widget" }, { "Chat Bot" }, { "RCS" },
				{ "WhatsApp" }, { "Instagram" }, { "Messenger" }, { "Twitter" }, { "LinkedIn" } };
	}
	
	@Test(dependsOnMethods = "openAiAgentsListForm")
	public void verifylegendsButtonStatus() throws InterruptedException {
		agentsListPage = new AiAgentsListPage(driver);
		agentsListPage.verifylegendsButton("Chat Bot");
	}
	
	@AfterClass(enabled = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
