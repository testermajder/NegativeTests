package com.projectname.e2e.tests.pages;

import com.projectname.e2e.tests.data.enums.Module;
import com.projectname.e2e.tests.pages.common.PageBase;
import com.projectname.e2e.tests.selectors.CustomBy;
import com.projectname.e2e.tests.utils.CheckIfElement;
import com.projectname.e2e.tests.webdriver.CustomWebDriver;

import static com.projectname.api.client.utils.Allure.logStep;

public class DashboardPage extends PageBase {

    public DashboardPage(CustomWebDriver driver, String url, String email, String password) {
        super(driver, url, email, password);
    }

    @Override
    public PageBase show() {
        logStep("INFO: Navigate to Dashboard page");
        if (!isDisplayed()) {
            LoginPage loginPage = new LoginPage(driver,"", email, password);
            if (loginPage.isDisplayed()) {
                loginPage.login(email, password);
            } else {
                navigateTo(Module.DASHBOARD);
            }
            logStep("PASS: Dashboard page is shown");
        }
        return this;
    }

    @Override
    public boolean isDisplayed() {
        return CheckIfElement.isDisplayed(CustomBy.testAutomationId("dashboardButton"), driver);
    }

}
