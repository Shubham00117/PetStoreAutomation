package api.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

    private ExtentReports extent;
    private ExtentTest test;
    private ExtentSparkReporter sparkReporter;

    private String repName;

    // ===================== ON START =====================
    @Override
    public void onStart(ITestContext testContext) {

        String timeStamp =
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "API-Test-Report-" + timeStamp + ".html";

        // macOS-safe project root
        String reportsDir =
                System.getProperty("user.dir") + "/reports";

        // Create reports directory if not exists
        File reportDirectory = new File(reportsDir);
        if (!reportDirectory.exists()) {
            reportDirectory.mkdirs();
        }

        String reportPath = reportsDir + "/" + repName;

        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("PetStore API Automation Report");
        sparkReporter.config().setReportName("REST Assured Test Results");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // ===== System Information =====
        extent.setSystemInfo("Application", "PetStore");
        extent.setSystemInfo("Test Type", "API Automation");
        extent.setSystemInfo("Executed By", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        List<String> groups =
                testContext.getCurrentXmlTest().getIncludedGroups();
        if (!groups.isEmpty()) {
            extent.setSystemInfo("Groups", groups.toString());
        }
    }

    // ===================== TEST START =====================
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    // ===================== TEST SUCCESS =====================
    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed Successfully");
    }

    // ===================== TEST FAILURE =====================
    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable());
    }

    // ===================== TEST SKIPPED =====================
    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable());
    }

    // ===================== ON FINISH =====================
    @Override
    public void onFinish(ITestContext testContext) {

        extent.flush();

        File reportFile =
                new File(System.getProperty("user.dir") + "/reports/" + repName);

        try {
            Desktop.getDesktop().browse(reportFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}