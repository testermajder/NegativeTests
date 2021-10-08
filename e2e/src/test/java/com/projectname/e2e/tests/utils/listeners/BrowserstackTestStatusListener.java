package com.projectname.e2e.tests.utils.listeners;

import com.projectname.e2e.tests.suites.common.TestBase;
import com.projectname.e2e.tests.webdriver.CustomWebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class BrowserstackTestStatusListener extends TestListenerAdapter {

    private void markTestStatus(String status, String reason, CustomWebDriver driver) {
        try {
            driver.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"" + status + "\", \"reason\": \"" + reason + "\"}}");
        } catch (Exception e) {
            System.out.print("Error executing javascript" + e.getCause().getLocalizedMessage());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Object currentClass = result.getInstance();
        CustomWebDriver driver = ((TestBase) currentClass).getDriver();
        markTestStatus("passed", "", driver);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        CustomWebDriver driver = ((TestBase) currentClass).getDriver();
        String message = result.getThrowable().getMessage();
        String reason = (message != null && message.length() > 254) ? message.substring(0, 254) : message;
        markTestStatus("failed", reason.replaceAll("[^a-zA-Z0-9._]", " "), driver);
    }

}
