package com.example.test.api.integration.suites;

import com.example.test.api.calls.ExampleAPI;
import com.example.test.api.common.init.TestBase;
import com.example.test.api.integration.asserts.CommonAssert;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

// example for integration test
public class IntegrationTests extends TestBase {

    @Test(groups = {"integration"})
    @Description("Allure description for this test")
    public static void verifyExample() {
        logStep("INFO: Verify required fields");
        new CommonAssert().assertResponseStructure(ExampleAPI.validateExampleResponse("123456"));
        logStep("PASS: Required fields are verified");
    }

    @Test(groups = {"integration"})
    @Description("Allure description for this test")
    public static void verifyExampleList() {
        logStep("INFO: Verify required fields");
        new CommonAssert().assertResponseStructure(ExampleAPI.validateExampleResponse("123456"));
        logStep("PASS: Required fields are verified");
    }
}
