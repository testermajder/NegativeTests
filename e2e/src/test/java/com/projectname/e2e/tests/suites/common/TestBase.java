package com.projectname.e2e.tests.suites.common;

import com.projectname.api.client.utils.MethodNGListener;
import com.projectname.e2e.tests.environment.ConfigSetup;
import com.projectname.e2e.tests.selectors.CustomBy;
import com.projectname.e2e.tests.utils.CapabilityHelper;
import com.projectname.e2e.tests.utils.listeners.ScreenshotListener;
import com.projectname.e2e.tests.webdriver.CustomWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IInvokedMethodListener;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.internal.BaseTestMethod;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Listeners({ScreenshotListener.class, MethodNGListener.class})
public class TestBase implements ITest, IInvokedMethodListener {

    private static String testName = "";
    private static Map<String, CustomWebDriver> mapWebDriver = new HashMap<String, CustomWebDriver>();
    private CustomWebDriver driver;
    public static final String BROWSERSTACK_HUB_URL = "https://" + ConfigSetup.getBSUser() + ":" + ConfigSetup.getBSKey() + "@hub-cloud.browserstack.com/wd/hub";
    private final String baseUrl = ConfigSetup.getUIBaseURL();

    public CustomWebDriver getDriver() {
        return driver;
    }

    @BeforeSuite(alwaysRun = true)
    @Parameters(value = {"localRun"})
    public void beforeSuite(@Optional("true") String localRun, ITestContext context) throws Exception {
        RestAssured.baseURI = ConfigSetup.getUIBaseURL();
    }

    @BeforeTest(alwaysRun=true)
    @Parameters(value = {"localRun", "env_cap_id"})
    public void setUp(@Optional("true") String localRun, @Optional("0") int env_cap_id, ITestContext context) throws Exception {
        String platform = context.getCurrentXmlTest().getParameter("platform");
        if (localRun.equals("true")) {
            if (mapWebDriver.containsKey(context.getCurrentXmlTest().getName()))
                this.driver = mapWebDriver.get(context.getCurrentXmlTest().getName());
            else {
                if (platform == null || platform.contains("Chrome")) {
                    WebDriverManager.chromedriver().setup();
                    this.driver = new CustomWebDriver(new ChromeDriver());
                } else if (platform.contains("Firefox")) {
                    WebDriverManager.firefoxdriver().setup();
                    this.driver = new CustomWebDriver(new FirefoxDriver());
                } else if (platform.contains("Edge")) {
                    WebDriverManager.edgedriver().setup();
                    this.driver = new CustomWebDriver(new EdgeDriver());
                }
            }
        } else {
            DesiredCapabilities caps = CapabilityHelper.generateCapabilities(env_cap_id);
            if (mapWebDriver.containsKey(context.getCurrentXmlTest().getName()))
                this.driver = mapWebDriver.get(context.getCurrentXmlTest().getName());
            else
                this.driver = new CustomWebDriver(new RemoteWebDriver(new URL(BROWSERSTACK_HUB_URL), caps));
        }

        mapWebDriver.put(context.getCurrentXmlTest().getName(), this.driver);

        getDriver().get(baseUrl);
        getDriver().manage().window().maximize();
        getDriver().waitForElementToBePresent(CustomBy.testAutomationId("emailInput"));
        getDriver().addMouseFollowOnClick();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method, Object[] testData, ITestResult iTestResult) {
        if (testData.length > 0) {
            testName = (method.getName() + "_" + testData[0]);
            try {
                BaseTestMethod baseTestMethod = (BaseTestMethod) iTestResult.getMethod();
                java.lang.reflect.Field f = baseTestMethod.getClass().getSuperclass().getDeclaredField("m_methodName");
                f.setAccessible(true);
                f.set(baseTestMethod, testName);
            } catch (Exception e) {
            }
        } else {
            testName = method.getName();
        }
    }

    @Override
    public String getTestName() {
        return testName;
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(ITestContext context) {
        driver.quit();
        if (mapWebDriver.containsKey(context.getCurrentXmlTest().getName()))
            mapWebDriver.remove(context.getCurrentXmlTest().getName());
    }

}

