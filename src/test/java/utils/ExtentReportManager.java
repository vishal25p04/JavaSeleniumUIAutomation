package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getInstance(String filePath) {
        if (extent == null) {
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(filePath);
            htmlReporter.config().setTheme(Theme.DARK);
            htmlReporter.config().setDocumentTitle("Test Result Report");
            htmlReporter.config().setReportName("CucumberTestExecution");

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);

            // Add system information
//            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
//            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
//            extent.setSystemInfo("Browser", "Edge");
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}