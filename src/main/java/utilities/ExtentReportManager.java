package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    // ---------- Initialize Extent Report ----------
    public static ExtentReports getReportInstance() {
        if (extent == null) {
            // ---------- Reports folder inside current project directory ----------
            String projectPath = System.getProperty("user.dir");
            File reportsDir = new File(projectPath + File.separator + "reports");
            if (!reportsDir.exists()) {
                reportsDir.mkdirs();
            }

            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportPath = reportsDir.getAbsolutePath()
                    + File.separator + "TestReport_" + timeStamp + ".html";

            System.out.println(">>> Report will be created at : " + reportPath);

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("Zigwheels Automation Report");
            spark.config().setReportName("Zigwheels Test Execution");
            spark.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Project", "Zigwheels Automation");
            extent.setSystemInfo("Team", "QA Automation Team");
            extent.setSystemInfo("Module", "Bikes & Cars");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }

    // ---------- Create a new test entry ----------
    public static ExtentTest createTest(String testName) {
        test = getReportInstance().createTest(testName);
        return test;
    }

    // ---------- Get the current running test ----------
    public static ExtentTest getTest() {
        return test;
    }

    // ---------- Flush report at the end ----------
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}