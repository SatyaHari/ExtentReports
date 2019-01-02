package org.cts.hybrid.ExtentReports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentConfiguration {

	private static ExtentReports extent;
	public static final String WORKING_DIR = System.getProperty("user.dir");
	private static final String TIME_STAMP = new SimpleDateFormat("dd.MM.yyyy.HH.mm").format(new Date());
	private static final String EXTENT_REPORTS_FOLDER = WORKING_DIR + "/AutomationReports";
	private static final String REPORT_NAME = "ExtentReport_" + TIME_STAMP + ".html";
	private static final String EXTENT_REPORTS_PATH = EXTENT_REPORTS_FOLDER + "/" + REPORT_NAME;
	private static Logger logger = Logger.getLogger(ExtentConfiguration.class.getName());

	public static ExtentReports getInstance() {
		if (extent == null) {
			createReportsFolder();
			createInstance();
		}
		return extent;
	}

	private static void createReportsFolder() {
		File file = new File(EXTENT_REPORTS_FOLDER);
		if (!file.exists()) {
			if (!file.mkdir()) {
				logger.warning("Failed to create directory!");
			}
		}
	}

	private static ExtentReports createInstance() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(EXTENT_REPORTS_PATH);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(REPORT_NAME);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Execution-Status");
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
	}
}
