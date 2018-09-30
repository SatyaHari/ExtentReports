package org.cts.hybrid.listeners;

import java.util.logging.Logger;

import org.cts.hybrid.ExtentReports.ExtentManager;
import org.cts.hybrid.ExtentReports.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {

	private static Logger logger = Logger.getLogger(TestListener.class.getName());
	
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Override
	public void onStart(ITestContext iTestContext) {
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		ExtentManager.getInstance().flush();
		ExtentTestManager.endTest();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(), iTestResult.getMethod().getDescription());
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		//ExtentTestManager.getTest().log(Status.PASS, "Test passed.");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		logger.warning("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
		ExtentTestManager.getTest().log(Status.FAIL, "Test Step Failed is " + iTestResult.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped.");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	}

}
