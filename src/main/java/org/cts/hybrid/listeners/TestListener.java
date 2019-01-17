package org.cts.hybrid.listeners;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.cts.hybrid.extentreports.ExtentConfiguration;
import org.cts.hybrid.extentreports.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {

	private static Logger logger = Logger.getLogger(TestListener.class.getName());
	private Map<String, String> allParameters = new HashMap<>();
	private Map<String, String> suiteParameters = new HashMap<>();
	private Map<String, String> localParameters = new HashMap<>();

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		allParameters = iTestContext.getSuite().getXmlSuite().getAllParameters();
		suiteParameters = iTestContext.getSuite().getXmlSuite().getParameters();
		localParameters = iTestContext.getCurrentXmlTest().getLocalParameters();
	}

	public Map<String, String> getAllParameters() {
		return allParameters;
	}

	public Map<String, String> getSuiteParameters() {
		return suiteParameters;
	}

	public Map<String, String> getLocalParameters() {
		return localParameters;
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		ExtentConfiguration.getInstance().flush();
		ExtentTestManager.endTest();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(), iTestResult.getMethod().getDescription());
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		logger.info(iTestResult.getName() + " passed successfully!!");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		logger.warning(getTestMethodName(iTestResult) + " failed");
		ExtentTestManager.getTest().log(Status.FAIL, "Test Step Failed: " + iTestResult.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		ExtentTestManager.getTest().log(Status.SKIP, iTestResult.getName() + " execution got skipped.");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		logger.info("");
	}

}
