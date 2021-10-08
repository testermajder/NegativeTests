package com.projectname.api.tests.init;

import com.projectname.api.client.utils.GsonFunctions;
import com.projectname.api.client.utils.MethodNGListener;
import com.projectname.api.tests.environment.ConfigSetup;
import io.restassured.RestAssured;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.internal.BaseTestMethod;

import java.lang.reflect.Method;

//Use this class for common functions by using extend inside test classes
@Listeners({MethodNGListener.class})
public class TestBase implements ITest {

    //    Use for allure reports
    private static String testName = new String();

    //    Use to set base url for test methods; If not set, localhost:8080 is used as default
    @BeforeSuite(groups = {"integration", "regression", "smoke", "knownBugs"})
    public void beforeSuite(ITestContext context) {
        RestAssured.baseURI = ConfigSetup.getApiBaseURL();
    }
    
    @BeforeClass(groups = {"integration", "regression", "smoke", "knownBugs"})
    public void beforeClass() {
    }

    // Used for allure reports to read fist param from test data
    // if test method is public void testExample() add data like this public void testExample(String allureSuffix)
    // Use this for data that is loaded through TestNG's DataProviders so in Allure report you can know for each data test run what was the data setup
    @BeforeMethod(groups = {"integration", "regression", "smoke", "knownBugs"})
    public void beforeMethod(Method method, Object[] testData, ITestContext ctx, ITestResult iTestResult) {
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

    //    Use to clean validationResponse memory after each integration test
    @AfterMethod(groups = {"integration"})
    public void revalidate() {
        GsonFunctions.revalidateResponseValidationList();
    }
}
