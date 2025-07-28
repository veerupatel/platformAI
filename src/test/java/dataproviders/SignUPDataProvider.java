package dataproviders;

import org.testng.annotations.DataProvider;

public class SignUPDataProvider {

	@DataProvider(name = "PlanOptionsforSignUpUser")
	public Object[][] selectYourPlanforSignUp() {
		return new Object[][] { { "Free" }, { "Agency" }, { "Enterprise" } };
	}

}
