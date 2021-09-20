package com.example.test.api.responsevalidation.suites;

import com.example.test.api.calls.UserAPI;
import com.example.test.api.common.RandomStringGenerator;
import com.example.test.api.common.init.TestBase;
import com.example.test.api.data.model.users.create.CreateUserRequest;
import com.example.test.api.data.model.users.create.CreateUserResponse;
import com.example.test.api.responsevalidation.asserts.CommonAssert;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

// example for integration test
public class ResponseValidationTests extends TestBase {

    @Test(groups = {"integration"})
    @Description("Allure description for this test")
    public static void verifyGetUserByIdResponse() {
        logStep("INFO: Verify required fields");
        new CommonAssert().assertResponseStructure(UserAPI.validateGetUserByIdResponse("1"));
        logStep("PASS: Required fields are verified");
    }
}
