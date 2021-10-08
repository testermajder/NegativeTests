package com.projectname.e2e.tests.pages.common;

import com.projectname.e2e.tests.data.enums.Module;
import com.projectname.e2e.tests.pages.DashboardPage;
import com.projectname.e2e.tests.webdriver.CustomWebDriver;

import static com.projectname.api.client.utils.Allure.logStep;

public abstract class PageBase {
    protected CustomWebDriver driver;
    protected String url;
    protected String email;
    protected String password;

    private NavigationBarSubPage navigationBarSubPage;

    public PageBase(CustomWebDriver driver, String url, String email, String password)
    {
        this.driver = driver;
        this.url = url;
        this.email = email;
        this.password = password;
        this.navigationBarSubPage = new NavigationBarSubPage(driver);
    }

    public <T extends PageBase> T navigateTo(Module module) {
        try {
            logStep("Navigate to " + module + " page");
            switch (module) {
                case DASHBOARD:
                    return (T) new DashboardPage(this.driver, this.url, this.email, this.password).show();
                case PROFILE:
                    //return (T) new Profile(this.driver, this.url, this.email, this.password).show();
                case REPORT:
                    //return (T) new Report(this.driver, this.url, this.email, this.password).show();
                default:
                    return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract PageBase show();

    public abstract boolean isDisplayed();
}
