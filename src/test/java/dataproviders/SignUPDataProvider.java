package dataproviders;

import org.testng.annotations.DataProvider;

public class SignUPDataProvider {

	@DataProvider(name = "PlanOptionsforSignUpUser")
	public Object[][] selectYourPlanforSignUp() {
		return new Object[][] { { "Free" }, { "Agency" }, { "Enterprise" } };
	}

	@DataProvider(name = "dataForSignUpAccount")
	public Object[][] dataForSignUpAccount() {
		return new Object[][] { { "vibha@gmail.com", "Admin@1234", "Free" },
				{ "vibha@gmail.com", "Admin@1234", "Agency" }, { "vibha@gmail.com", "Admin@1234", "Enterprise" } };
	}

}
