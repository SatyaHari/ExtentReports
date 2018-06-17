package org.cts.hybrid.utils;


import org.cts.hybrid.ExtentReports.ExtentTestManager;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class AssertionLibrary {

	public static void assertEquals(String actual, String expected, String message) {
		Assert.assertEquals(actual, expected, message);
		ExtentTestManager.getTest().log(Status.PASS, message);
	}

	public static void assertTrue(boolean actual, boolean expected, String message) {
		Assert.assertEquals(actual, expected, message);
		ExtentTestManager.getTest().log(Status.PASS, message);
	}

	
}
