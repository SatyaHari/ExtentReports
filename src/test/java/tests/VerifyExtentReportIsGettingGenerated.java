package tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.cts.oneframework.listeners.TestListener.class)
public class VerifyExtentReportIsGettingGenerated {

	@Test
	public void verifyExtentReportIsGettingGeneratedTest() {
		// This test class is just to show if extent report configurations are working or not.
		// By default, it will create report in AutomationReports folder.
		// for klov reprt, set klovReport flag to true in config.properties
		// and set db properties on klov.properties file
	}

}
