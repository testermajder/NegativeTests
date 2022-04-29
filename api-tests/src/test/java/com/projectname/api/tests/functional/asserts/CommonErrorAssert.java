package com.projectname.api.tests.functional.asserts;

import com.projectname.api.client.data.model.crocodile.CrocodileErrorResponse;
import com.projectname.api.client.data.model.crocodile.RequiredFieldErrorResponse;
import com.projectname.api.client.data.model.users.RequiredFieldErrorReponseUsers;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class CommonErrorAssert {
    private SoftAssert softAssert;

    public CommonErrorAssert() {
        this.softAssert = new SoftAssert();
    }

    public void assertCrocodileErrorResponse(CrocodileErrorResponse actualError, CrocodileErrorResponse expectedError) {
        this.softAssert.assertEquals(actualError.getDetail(), expectedError.getDetail(), "Detail didn't match");
        this.softAssert.assertAll();
    }

    public void assertRequiredFieldErrors(RequiredFieldErrorResponse actualError, RequiredFieldErrorResponse expectedError) {
        if(actualError == null) {
            Assert.fail("Actual error is null");
        }
        if (actualError.getName() != null) {
            this.softAssert.assertEquals(actualError.getName().get(0), expectedError.getName().get(0), "Name error didn't match");
        }
        if(actualError.getSex() != null) {
            this.softAssert.assertEquals(actualError.getSex().get(0), expectedError.getSex().get(0), "Sex error didn't match");
        }
        if(actualError.getDateOfBirth() != null) {
            this.softAssert.assertEquals(actualError.getDateOfBirth().get(0), expectedError.getDateOfBirth().get(0), "Date of birth error didn't match");
        }
        this.softAssert.assertAll();
    }

    public void assertRequiredFieldErrorsUsers(RequiredFieldErrorReponseUsers actualError, RequiredFieldErrorReponseUsers expectedError) {
        if(actualError == null) {
            Assert.fail("Actual error is null");
        }
        if (actualError.getUsername() != null) {
            this.softAssert.assertEquals(actualError.getUsername().get(0), expectedError.getUsername().get(0), "Username didn't match");
        }
        if(actualError.getEmail() != null) {
            this.softAssert.assertEquals(actualError.getEmail().get(0), expectedError.getEmail().get(0), "Email didn't match");
        }
        if(actualError.getPassword() != null) {
            this.softAssert.assertEquals(actualError.getPassword().get(0), expectedError.getPassword().get(0), "Password didn't match");
        }
        this.softAssert.assertAll();
    }

}
