package org.cts.hybrid.ExtentReports;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

	private static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	private static ExtentReports extent = ExtentManager.getInstance();

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get(getCurrentThread());
	}

	public static synchronized void endTest() {
		extent.removeTest(extentTestMap.get(getCurrentThread()));
	}

	public static synchronized ExtentTest startTest(String testName, String desc) {
		ExtentTest test = extent.createTest(testName, desc);
		extentTestMap.put(getCurrentThread(), test);
		System.out.println("TEST STarted " );
		return test;
	}
	
	private static synchronized int getCurrentThread(){
		int threadValue=(int)(Thread.currentThread().getId());
		return threadValue;
	}

}
