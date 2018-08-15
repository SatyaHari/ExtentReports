package org.cts.hybrid.listeners;

import org.cts.hybrid.ExtentReports.ExtentManager;
import org.cts.hybrid.ExtentReports.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {

	private static final  Logger logger=Logger.getLogger(TestListener.class);
	
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	public static String getClassName(ITestResult iTestResult) {
		return iTestResult.getClass().getSimpleName();
	}

	public void onStart(ITestContext iTestContext) {
		logger.info("I am in onStart method " + iTestContext.getName());
	}

	public void onFinish(ITestContext iTestContext) {
		logger.info("I am in onFinish method " + iTestContext.getName());
		ExtentManager.getInstance().flush();
		ExtentTestManager.endTest();
	}

	public void onTestStart(ITestResult iTestResult) {
		logger.info("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
		ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(), iTestResult.getMethod().getDescription());
	}

	public void onTestSuccess(ITestResult iTestResult) {
		logger.info("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed.");

	}

	public void onTestFailure(ITestResult iTestResult) {
		logger.error("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
		ExtentTestManager.getTest().log(Status.FAIL, "Test Step Failed is " + iTestResult.getThrowable());
	}

	public void onTestSkipped(ITestResult iTestResult) {
		logger.warn("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped.");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		logger.warn("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

}
