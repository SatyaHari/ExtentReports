package org.cts.hybrid.extentreports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.cts.hybrid.configprovider.ConfigProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentConfiguration {

	private static ExtentReports extent;
	public static final String WORKING_DIR = System.getProperty("user.dir");
	private static final String TIME_STAMP = new SimpleDateFormat("dd.MM.yyyy.HH.mm").format(new Date());
	private static final String EXTENT_REPORTS_FOLDER = WORKING_DIR + "/AutomationReports";
	private static final String REPORT_NAME = "ExtentReport_" + TIME_STAMP + ".html";
	private static final String EXTENT_REPORTS_PATH = EXTENT_REPORTS_FOLDER + File.separator + REPORT_NAME;
	private static Logger logger = Logger.getLogger(ExtentConfiguration.class.getName());

	private ExtentConfiguration() {

	}

	public static ExtentReports getInstance() {
		if (extent == null) {
			createReportsFolder();
			attachReporters();
		}
		return extent;
	}

	private static void createReportsFolder() {
		File file = new File(EXTENT_REPORTS_FOLDER);
		if (!file.exists() && !file.mkdir()) {
			logger.warning("Failed to create directory!");
		}
	}

	private static ExtentHtmlReporter initHtmlReporter() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(EXTENT_REPORTS_PATH);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(REPORT_NAME);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Execution-Status");
		return htmlReporter;
	}

	private static ExtentKlovReporter initKlovReporter() {
		ExtentKlovReporter klovReporter = new ExtentKlovReporter(ConfigProvider.getAsString("project.name"), ConfigProvider.getAsString("report.name"));
		klovReporter.initMongoDbConnection(ConfigProvider.getAsString("mongodb.host"), ConfigProvider.getAsInt("mongodb.port"));
		klovReporter.initKlovServerConnection(ConfigProvider.getAsString("klov.host"));
		return klovReporter;
	}

	private static ExtentReports attachReporters() {
		extent = new ExtentReports();
		extent.attachReporter(initHtmlReporter());
		if (ConfigProvider.getAsString("KlovReport").equalsIgnoreCase("true") || ConfigProvider.getAsString("KlovReport").equalsIgnoreCase("yes"))
			extent.attachReporter(initKlovReporter());
		return extent;
	}

}
