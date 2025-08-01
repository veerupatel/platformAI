package dataproviders;

import org.testng.annotations.DataProvider;

public class ChannelProvider {

	@DataProvider(name = "channelProvider")
	public Object[][] channelProverOptions() {
		return new Object[][] {{"Telephony"},{"Web Widget"},{"Chat Bot"},{"RCS"},{"WhatsApp Business"}};
	}

}
