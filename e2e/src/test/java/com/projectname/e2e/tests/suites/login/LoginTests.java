package com.projectname.e2e.tests.suites.login;

import com.projectname.e2e.tests.environment.ConfigSetup;
import com.projectname.e2e.tests.pages.DashboardPage;
import com.projectname.e2e.tests.pages.LoginPage;
import com.projectname.e2e.tests.suites.common.TestBase;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.projectname.api.client.utils.Allure.logStep;

public class LoginTests extends TestBase {

    @Test(groups = {"smoke", "ui" })
    @Description("Verify login")
    public void verifyLogin() {

        logStep("INFO: Fetch login screen");
        LoginPage loginPage = new LoginPage(getDriver(), "", ConfigSetup.getAdminUser(), ConfigSetup.getAdminPsw());
        loginPage.show();
        logStep("PASS: Login screen is fetched");

        DashboardPage dashboardPage = loginPage.login(ConfigSetup.getAdminUser(), ConfigSetup.getAdminPsw());

        logStep("INFO: Verify Dashboard page is shown");
        Assert.assertTrue(dashboardPage.isDisplayed(), "Dashboard page is not fetched");
        logStep("PASS: Dashboard page is fetched -  Login is verified");

    }
}
