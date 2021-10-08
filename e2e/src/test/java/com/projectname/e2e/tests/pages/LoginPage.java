package com.projectname.e2e.tests.pages;

import com.projectname.e2e.tests.pages.common.NavigationBarSubPage;
import com.projectname.e2e.tests.pages.common.PageBase;
import com.projectname.e2e.tests.selectors.CustomBy;
import com.projectname.e2e.tests.utils.CheckIfElement;
import com.projectname.e2e.tests.webdriver.CustomWebDriver;
import org.openqa.selenium.WebElement;

import static com.projectname.api.client.utils.Allure.logStep;

public class LoginPage extends PageBase {

    public LoginPage(CustomWebDriver driver, String url, String email, String password) {
        super(driver, url, email, password);
    }

    @Override
    public PageBase show() {
        if (!isDisplayed()) {
            logStep("INFO: Navigate to Login page");
            new NavigationBarSubPage(driver).logout();
            logStep("PASS: User is logged out in order to navigate to Login page");
            driver.waitForElementToBePresent(CustomBy.testAutomationId("emailInput"));
        }
        return this;
    }

    @Override
    public boolean isDisplayed() {
        return CheckIfElement.isDisplayed(CustomBy.testAutomationId("emailInput"), driver);
    }

    public DashboardPage login(String email, String password){
        WebElement weGetEmailInput = getEmailInput();
        //you can directly send characters, but idea with click is to see mouse follow from one element to another, since that action is triggered on click
        weGetEmailInput.click();
        weGetEmailInput.sendKeys(email);
        WebElement weGetPasswordInput = getPasswordInput();
        weGetPasswordInput.click();
        weGetPasswordInput.sendKeys(password);
        getLoginButton().click();
        driver.waitForElementToBePresent(CustomBy.testAutomationId("dashboardButton"));
        return new DashboardPage(driver, "", email, password);
    }

    private WebElement getEmailInput() {
        try {
            return driver.findElement(CustomBy.testAutomationId("emailInput"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Could not find email input field on Login Page", e);
        }
    }

    private WebElement getPasswordInput() {
        try {

            return driver.findElement(CustomBy.testAutomationId("passwordInput"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Could not find password input field on Login page", e);
        }
    }

    private WebElement getLoginButton() {
        try {
            return driver.findElement(CustomBy.testAutomationId("logInButton"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Could not find Log in button on Login page", e);
        }
    }
}
