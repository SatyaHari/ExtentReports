package org.cts.hybrid.listeners;

import org.cts.hybrid.ExtentReports.ExtentManager;
import org.cts.hybrid.ExtentReports.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	public static String getClassName(ITestResult iTestResult) {
		return iTestResult.getClass().getSimpleName();
	}

	public void onStart(ITestContext iTestContext) {
		System.out.println("I am in onStart method " + iTestContext.getName());
		// iTestContext.setAttribute("WebDriver", this.driver);
	}

	public void onFinish(ITestContext iTestContext) {
		System.out.println("I am in onFinish method " + iTestContext.getName());
		// Do tier down operations for extentreports reporting!
		// ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
		ExtentTestManager.endTest();
	}

	public void onTestStart(ITestResult iTestResult) {
		System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
		ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(), "");
	}

	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
		// Extentreports log operation for passed tests.
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");

	}

	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
		ExtentTestManager.getTest().log(Status.FAIL, "Test Step Failed is " + iTestResult.getThrowable());
	}

	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
		// Extentreports log operation for skipped tests.
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

}
