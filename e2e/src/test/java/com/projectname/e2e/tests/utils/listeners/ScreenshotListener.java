package com.projectname.e2e.tests.utils.listeners;

import com.projectname.e2e.tests.suites.common.TestBase;
import com.projectname.e2e.tests.webdriver.CustomWebDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ScreenshotListener extends TestListenerAdapter {

    @Attachment(value = "Failure screenshot", type = "image/png")
    public byte[] attachFailedScreenshot(CustomWebDriver driver) {
        return driver.getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (!result.isSuccess()) {
            Object currentClass = result.getInstance();
            CustomWebDriver driver = ((TestBase) currentClass).getDriver();
            attachFailedScreenshot(driver);
        }
    }
}