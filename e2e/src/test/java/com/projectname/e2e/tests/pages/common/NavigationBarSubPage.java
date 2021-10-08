package com.projectname.e2e.tests.pages.common;

import com.projectname.e2e.tests.pages.LoginPage;
import com.projectname.e2e.tests.selectors.CustomBy;
import com.projectname.e2e.tests.webdriver.CustomWebDriver;
import org.openqa.selenium.WebElement;

import static com.projectname.api.client.utils.Allure.logStep;

public class NavigationBarSubPage {
    private CustomWebDriver driver;

    public NavigationBarSubPage(CustomWebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed() {
        return getNavigationBar().isDisplayed();
    }

    private WebElement getNavigationBar() {
        try {
            return driver.findElement(CustomBy.className("navigation-menu-content"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Could not find navigation bar", e);
        }
    }

    private WebElement getUserProfileButton() {
        try {
            return driver.findElement(CustomBy.testAutomationId("userProfileButton"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Could not find user profile button on navigation bar", e);
        }
    }

    private WebElement getLogoutButton() {
        try {
            return driver.findElement(CustomBy.testAutomationId("logOutButton"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Could not find logout button in user profile dropdown", e);
        }
    }

    public LoginPage logout() {
        logStep("INFO: Click on user profile in Navigation bar");
        getUserProfileButton().click();
        logStep("PASS: Clicked on user profile in Navigation bar");
        logStep("INFO: Click on logout button in user profile dropdown");
        getLogoutButton().click();
        logStep("PASS: Clicked on logout button in user profile dropdown");
        return new LoginPage(driver, "", "", "");
    }

}
