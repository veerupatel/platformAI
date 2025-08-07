package dataproviders;

import org.testng.annotations.DataProvider;

public class RoleDataProvider {

	@DataProvider(name = "urserRoles")
	public Object[][] provideUsersRoles() {
		return new Object[][] { { "user", "rajesh@gmail.com", "Admin@1234" },
				{ "admin", "rajesh@gmail.com", "Admin@1234" },
				{ "superadmin", "saddam.groot@gmail.com", "Admin@1234" } };
	}

}
