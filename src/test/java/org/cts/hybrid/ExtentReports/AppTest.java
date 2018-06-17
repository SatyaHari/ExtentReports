package org.cts.hybrid.ExtentReports;

import org.cts.hybrid.utils.AssertionLibrary;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AppTest {

	@DataProvider(name = "browser-provider")
	public Object[][] provide() throws Exception {
		return new Object[][] { { "TABLETS" }, { "MICE" } };
	}

	@Test(priority = 0, description = "Invalid Login Scenario with wrong username and password.", dataProvider = "browser-provider")
	public void invalidLoginTest_InvalidUserNameInvalidPasswordGOINGFORWARD(String category) {
		AssertionLibrary.assertEquals(category, category, "String Matches" + category);
	}

	@AfterMethod
	public void handleException() {
		System.out.println(" i am in after method of login Test");

	}

}
