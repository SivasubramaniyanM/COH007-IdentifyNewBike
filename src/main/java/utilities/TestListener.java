package utilities;

import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.getReportInstance();
        Log.info("=== Test Suite Started ===");
    }
    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportManager.createTest(result.getName());
        Log.info("Test Started : " + result.getName());
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS,
                "Test Passed : " + result.getName());
        Log.info("Test Passed : " + result.getName());
    }
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReportManager.getTest().log(Status.FAIL,
                "Test Failed : " + result.getName());
        ExtentReportManager.getTest().log(Status.FAIL, result.getThrowable());
        Log.error("Test Failed : " + result.getName());
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().log(Status.SKIP,
                "Test Skipped : " + result.getName());
        Log.info("Test Skipped : " + result.getName());
    }
    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flushReport();
        Log.info("=== Test Suite Finished ===");
    }
}