package com.projectname.api.tests.functional.asserts;

import com.projectname.api.client.data.model.users.RequiredFieldErrorReponseUsers;
import com.projectname.api.client.data.model.users.create.CreateUserResponse;
import com.projectname.api.client.data.model.users.update.UpdateUserResponse;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class UserAssert {

    private SoftAssert softAssert;

    public UserAssert() {
        this.softAssert = new SoftAssert();
    }

    // soft assert that do complete check and than log all errors found in test case
    public void assertCreatedUser(CreateUserResponse actualResponse, CreateUserResponse expectedResponse) {
        if (actualResponse == null) {
            Assert.fail("User was not created");
        }
        softAssert.assertEquals(actualResponse.getName(), expectedResponse.getName(), "name didn't match");
        softAssert.assertEquals(actualResponse.getJob(), expectedResponse.getJob(), "job didn't match");
        softAssert.assertNotNull(actualResponse.getCreatedAt(), "createdAt is null");
        softAssert.assertNotNull(actualResponse.getId(), "id is null");
        this.softAssert.assertAll();
    }

    public void assertUpdateUser(UpdateUserResponse actualResponse, UpdateUserResponse expectedUserResponse) {
        if (actualResponse == null) {
            Assert.fail("User is not updated");
        }
        softAssert.assertEquals(actualResponse.getName(), expectedUserResponse.getName(), "Name didn't match");
        softAssert.assertEquals(actualResponse.getJob(), expectedUserResponse.getJob(), "Job didn't match");
        softAssert.assertNotNull(actualResponse.getUpdatedAt(), "Updated at is null");
        softAssert.assertAll();
    }


}
