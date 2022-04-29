package com.projectname.api.tests.functional.suites;

import com.projectname.api.client.calls.UserAPI;
import com.projectname.api.client.data.model.users.create.CreateUserRequest;
import com.projectname.api.client.data.model.users.create.CreateUserResponse;
import com.projectname.api.client.data.model.users.update.UpdateUserRequest;
import com.projectname.api.client.data.model.users.update.UpdateUserResponse;
import com.projectname.api.tests.constants.DataProviderNames;
import com.projectname.api.tests.data.provider.UserProvider;
import com.projectname.api.tests.functional.asserts.UserAssert;
import com.projectname.api.tests.init.TestBase;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static com.projectname.api.client.utils.Allure.logStep;

// each test class should extend TestBase in order to inherit all common behaviors and to be logged properly in report
public class FunctionalTests extends TestBase {

    // each test case should be simple, readable, in few lines and without any to technical steps. It should represent
    // user actions like as those are done manually on UI
    @Test(groups = {"regression", "smoke"})
    @Description("Allure description for this test")
    public static void verifyCreateUser() {
        LocalDateTime date = LocalDateTime.now();

        CreateUserRequest createUserRequest = new CreateUserRequest("John Doe" + date, "QA Engineer");

        logStep("INFO: Create user");
        CreateUserResponse createdUserActual = UserAPI.createUser(createUserRequest);
        logStep("PASS: User is created");

        CreateUserResponse createdUserExpected = CreateUserResponse.parseCreatedUser(createUserRequest);

        UserAssert userAssert = new UserAssert();
        logStep("INFO: Verify user is created");
        userAssert.assertCreatedUser(createdUserActual, createdUserExpected);
        logStep("PASS: Response is verified");
    }

    // test case with provider
    @Test(groups = {"regression", "smoke"}, dataProvider = DataProviderNames.VERIFY_CREATE_USER, dataProviderClass = UserProvider.class)
    @Description("Verify can create user")
    public static void verifyCreateUserWithDataProvider(String methodNameSuffix, CreateUserRequest createUserRequest) {

        logStep("INFO: Create user");
        CreateUserResponse createdUserActual = UserAPI.createUser(createUserRequest);
        logStep("PASS: User is created");

        CreateUserResponse createdUserExpected = CreateUserResponse.parseCreatedUser(createUserRequest);

        UserAssert userAssert = new UserAssert();
        logStep("INFO: Verify user is created");
        userAssert.assertCreatedUser(createdUserActual, createdUserExpected);
        logStep("PASS: Response is verified");
    }
    @Test()
    @Description("Verify can update user")
    public static void verifyUpdateUser() {
        CreateUserResponse userResponse = UserAPI.createUser(new CreateUserRequest("John Doe", "QA Engineer"));

        UpdateUserRequest userRequest = new UpdateUserRequest("Stefan", "QA");

        UpdateUserResponse actualUpdateUserResponse = UserAPI.updateUser(userRequest, userResponse.getId());

        UpdateUserResponse expectedUpdateUserResponse = UpdateUserResponse.parseExpectedUserResponse(userRequest);

        UserAssert userAssert = new UserAssert();
        userAssert.assertUpdateUser(actualUpdateUserResponse, expectedUpdateUserResponse);

    }
    @Test(dataProvider = DataProviderNames.VERIFY_UPDATE_USER, dataProviderClass = UserProvider.class)
    @Description("Verify can update user")
    public static void verifyUpdateUserWithProvider(String suffix, UpdateUserRequest userRequest, String userId) {
        UpdateUserResponse actualResponse = UserAPI.updateUser(userRequest, userId);

        UpdateUserResponse expectedResponse = UpdateUserResponse.parseExpectedUserResponse(userRequest);

        UserAssert userAssert = new UserAssert();
        userAssert.assertUpdateUser(actualResponse, expectedResponse);
    }

    @Test
    //We will send the wrong user id on purpose to get a 404 error from the server
    public void verifyTestWillReturnError() {
        UserAPI.getUserById("55");
    }

    @Test
    public void verifyAssertWillFail() {
        LocalDateTime date = LocalDateTime.now();
        CreateUserRequest createUserRequest = new CreateUserRequest("milos" + date, "QA Engineer");
        CreateUserResponse actualResponse = UserAPI.createUser(createUserRequest);
        CreateUserResponse expectedResponse = CreateUserResponse.parseCreatedUser(createUserRequest);

        //We added hardcoded data to fail the assertion on purpose
        expectedResponse.setName("Made up name");

        UserAssert userAssert = new UserAssert();
        logStep("INFO: Verify user is created");
        userAssert.assertCreatedUser(actualResponse, expectedResponse);
        logStep("PASS: Response is verified");
    }

}
