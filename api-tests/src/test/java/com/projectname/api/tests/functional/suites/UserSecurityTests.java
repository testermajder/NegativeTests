package com.projectname.api.tests.functional.suites;

import com.projectname.api.client.calls.CrocodilesAPI;
import com.projectname.api.client.calls.UserAPI;
import com.projectname.api.client.data.model.crocodile.CrocodileRequest;
import com.projectname.api.client.data.model.crocodile.RequiredFieldErrorResponse;
import com.projectname.api.client.data.model.users.RequiredFieldErrorReponseUsers;
import com.projectname.api.client.data.model.users.UserRequest;
import com.projectname.api.tests.constants.DataProviderNames;
import com.projectname.api.tests.data.provider.CrocodileProvider;
import com.projectname.api.tests.data.provider.UserProvider;
import com.projectname.api.tests.functional.asserts.CommonErrorAssert;
import com.projectname.api.tests.init.TestBase;
import org.testng.annotations.Test;



public class UserSecurityTests extends TestBase {

@Test(dataProvider = DataProviderNames.VERIFY_CANNOT_CREATE_USER_WITHOUT_REQUIRED_FIELD, dataProviderClass = UserProvider.class)
 public void verifyCannotCreateUserWithoutRequiredFields(String suffix, UserRequest userRequest, RequiredFieldErrorReponseUsers expectedError){
    RequiredFieldErrorReponseUsers actualError = UserAPI.createUserWithRequiredFieldError(userRequest);

    CommonErrorAssert commonErrorAssert = new CommonErrorAssert();
    commonErrorAssert.assertRequiredFieldErrorsUsers(actualError, expectedError);
}

}
