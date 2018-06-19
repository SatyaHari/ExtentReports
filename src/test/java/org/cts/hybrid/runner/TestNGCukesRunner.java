package org.cts.hybrid.runner;

import org.cts.hybrid.listeners.ExtentProperties;
import org.cts.hybrid.listeners.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;

@CucumberOptions(
        features = {"src/test/resources/features/MyFeature.feature"},
        glue = {"org.cts.hybrid.stepdefinitions"},
        plugin = {"org.cts.hybrid.listeners.ExtentCucumberFormatter:"}
)
public class TestNGCukesRunner extends AbstractTestNGCucumberTests {
    @BeforeClass
    public  static void setup() {
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath("output/myreport.html");
    }

    @AfterClass
    public static void teardown() {
        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "Mac OSX");
        Reporter.setTestRunnerOutput("Sample test runner output message");
    }
}
