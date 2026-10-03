package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * Singleton manager for ExtentReports HTML test reporting.
 */
public class ExtentManager {

    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            extent = createInstance();
        }
        return extent;
    }

    private static ExtentReports createInstance() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportDir = System.getProperty("user.dir") + File.separator + "TestReport";
        File dir = new File(reportDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String reportPath = reportDir + File.separator + "BStackDemo_Report_" + timestamp + ".html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("bstackdemo.com Automation Report");
        sparkReporter.config().setReportName("bstackdemo Test Execution Results");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

        ExtentReports reports = new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("Application", "bstackdemo.com");
        reports.setSystemInfo("Domain", "E-Commerce Web Application");
        reports.setSystemInfo("Tester", System.getProperty("user.name"));
        reports.setSystemInfo("Environment", "QA");
        reports.setSystemInfo("OS", System.getProperty("os.name"));
        reports.setSystemInfo("Java Version", System.getProperty("java.version"));

        return reports;
    }
}
