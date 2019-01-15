package org.cts.hybrid.extentreports;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

	private ExtentTestManager() {

	}

	private static int counter = 0;

	private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
	private static ExtentReports extent = ExtentConfiguration.getInstance();

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get(getCurrentThread());
	}

	public static synchronized void endTest() {
		extent.removeTest(extentTestMap.get(getCurrentThread()));
	}

	public static synchronized ExtentTest startTest(String testName, final String desc) {
		counter++;
		ExtentTest test = extent.createTest(testName + "_" + counter, desc);
		extentTestMap.put(getCurrentThread(), test);
		return test;
	}

	private static synchronized int getCurrentThread() {
		return (int) (Thread.currentThread().getId());
	}

}
