package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.AiAgentsListPage;
import pages.BasicInfoPage;
import pages.CreateNewBOT;
import pages.DashboardPage;
import pages.KnowledgeBasePage;
import pages.LoginPage;
import pages.ToolsPage;
import utility.BrowserUtility;

public class BotGenerationTest {

	WebDriver driver;
	WebDriverWait wait;
	LoginPage loginPage;
	DashboardPage dashboardPage;
	AiAgentsListPage agentsListPage;
	CreateNewBOT createNewBOT;
	BasicInfoPage basicInfoPage;
	KnowledgeBasePage knowledgeBasePage;
	ToolsPage toolsPage;
	BrowserUtility browserUtility;

	@BeforeClass
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "E:\\Browser_driver\\chromedriver-win64\\chromedriver.exe");
		browserUtility = new BrowserUtility("chrome", false);
		driver = browserUtility.getDriver();
		driver.get("https://platform.fonada.ai/login");
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		loginPage.loginIntoApplication("admin","rajesh@gmail.com", "Admin@1234");
	}

	@Test(retryAnalyzer = com.ui.listenters.MyRetryAnalyzer.class, enabled = false)
	public void openCreateNewBotForm() throws InterruptedException {
		agentsListPage = new AiAgentsListPage(driver);
		dashboardPage.clickOnAIAgentsMenu().clickOnCreatenewBotButton().waitTillSelectChannelTextClikable()
				.selectChannel().clickOnContinueInfoButton().WaitTillBasicInfoBoxIsVisibleAndEnable()
				.fillBasicInfoOfBOT().clickOnNextStepButton().clickOnAgentDeployButton();
	}

	@Test(retryAnalyzer = com.ui.listenters.MyRetryAnalyzer.class)
	public void openCreateNewDynamicBotForm(String botname) throws InterruptedException {
		agentsListPage = new AiAgentsListPage(driver);
		dashboardPage.clickOnAIAgentsMenu().clickOnCreatenewBotButton().waitTillSelectChannelTextClikable()
				.selectChannelByChoice(botname).clickOnContinueInfoButton().WaitTillBasicInfoBoxIsVisibleAndEnable()
				.fillBasicInfoOfBOT().clickOnNextStepButton().clickOnAgentDeployButton();
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
