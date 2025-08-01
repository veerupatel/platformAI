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
import pages.HomePage;
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
	HomePage homePage;

	@BeforeClass
	public void launchBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "E:\\Browser_driver\\chromedriver-win64\\chromedriver.exe");
		browserUtility = new BrowserUtility("chrome", false);
		driver = browserUtility.getDriver();
		driver.get("https://platform.fonada.ai/");
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		homePage = new HomePage(driver);
		agentsListPage = new AiAgentsListPage(driver);
		homePage.clickOnsignInlink().dologinWith("admin", "rajesh@gmail.com", "Admin@1234");
	}

	@Test(retryAnalyzer = com.ui.listenters.MyRetryAnalyzer.class, enabled = true, dataProvider = "channelProvider", dataProviderClass = dataproviders.ChannelProvider.class)
	public void openCreateNewBotForm(String channelName) throws InterruptedException {
		dashboardPage.clickOnAIAgentsMenu().clickOnCreatenewBotButton().waitTillSelectChannelTextClikable()
				.selectChannelByChoice(channelName).clickOnContinueInfoButton().WaitTillBasicInfoBoxIsVisibleAndEnable()
				.fillBasicInfoOfBOT().clickOnNextStepButton().clickOnAgentDeployButton();
	}
	

	@Test(retryAnalyzer = com.ui.listenters.MyRetryAnalyzer.class,enabled = true, dataProvider = "channelProvider", dataProviderClass = dataproviders.ChannelProvider.class)
	public void verifyAllBotGeneration(String channelName) throws InterruptedException {
		dashboardPage.clickOnAIAgentsMenu().clickOnCreatenewBotButton().waitTillSelectChannelTextClikable()
				.selectChannelByChoice(channelName).clickOnContinueInfoButton().WaitTillBasicInfoBoxIsVisibleAndEnable()
				.fillBasicInfoOfBOTDynamic(channelName).clickOnNextStepButton().clickOnAgentDeployButton();
	}

	@Test(retryAnalyzer = com.ui.listenters.MyRetryAnalyzer.class, enabled = false)
	public void openCreateNewDynamicBotForm(String botname) throws InterruptedException {
		dashboardPage.clickOnAIAgentsMenu().clickOnCreatenewBotButton().waitTillSelectChannelTextClikable()
				.selectChannelByChoice("Chat Bot").clickOnContinueInfoButton().WaitTillBasicInfoBoxIsVisibleAndEnable()
				.fillBasicInfoOfBOT().clickOnNextStepButton().clickOnAgentDeployButton();
	}

	@AfterClass(enabled = false)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
