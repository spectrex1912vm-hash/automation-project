package listeners;

import base.BaseTest;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        logStep("START TEST: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logStep("TEST PASSED: " + result.getName());
        saveTextLog("SUCCESS: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logStep("TEST FAILED: " + result.getName());

        // получаем driver из BaseTest
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        if (driver != null) {
            saveScreenshot(driver);
        }

        saveTextLog("FAILED: " + result.getName());

        // лог ошибки
        if (result.getThrowable() != null) {
            saveTextLog("ERROR: " + result.getThrowable().getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logStep("TEST SKIPPED: " + result.getName());
        saveTextLog("SKIPPED: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        saveTextLog("=== TEST SUITE START ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        saveTextLog("=== TEST SUITE FINISH ===");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    // =========================
    // ATTACHMENTS
    // =========================

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // =========================
    // STEPS (для красивого отчета)
    // =========================

    @Step("{0}")
    public void logStep(String step) {
        // просто шаг для Allure
    }
}
