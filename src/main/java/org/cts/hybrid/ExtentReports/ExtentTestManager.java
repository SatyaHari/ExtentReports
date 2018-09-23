package org.cts.hybrid.ExtentReports;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

	private static int counter = 0;

	private static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	private static ExtentReports extent = ExtentManager.getInstance();

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get(getCurrentThread());
	}

	public static synchronized void endTest() {
		extent.removeTest(extentTestMap.get(getCurrentThread()));
	}

	public static synchronized ExtentTest startTest(String testName, final String desc) {
		counter++;
		ExtentTest test = extent.createTest(testName + "_" + String.valueOf(counter), desc);
		extentTestMap.put(getCurrentThread(), test);
		return test;
	}

	private static synchronized int getCurrentThread() {
		int threadValue = (int) (Thread.currentThread().getId());
		return threadValue;
	}

}
