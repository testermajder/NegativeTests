package com.projectname.api.tests.data.provider;

import com.projectname.api.client.data.model.crocodile.CrocodileRequest;
import com.projectname.api.client.data.model.crocodile.RequiredFieldErrorResponse;
import com.projectname.api.client.data.model.users.RequiredFieldErrorReponseUsers;
import com.projectname.api.client.data.model.users.UserRequest;
import com.projectname.api.client.data.model.users.create.CreateUserRequest;
import com.projectname.api.client.data.model.users.update.UpdateUserRequest;
import com.projectname.api.client.utils.Dates;
import com.projectname.api.client.utils.RandomStringGenerator;
import com.projectname.api.tests.constants.DataProviderNames;
import com.projectname.api.tests.constants.ErrorMessages;
import org.testng.annotations.DataProvider;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

public class UserProvider {

    @DataProvider(name = DataProviderNames.VERIFY_CREATE_USER)
    public static Object[][] verifyCreateUser() {
        LocalDateTime date = LocalDateTime.now();
        return new Object[][]{
                {"WithRandomNameAndJob", new CreateUserRequest(RandomStringGenerator.createRandomStringAlphanumericWithLen(5),
                        RandomStringGenerator.createRandomStringAlphanumericWithLen(5))},
                {"AsQaAutomationEngineer", new CreateUserRequest("Jason Kidd " + date,
                        "QA Automation Engineer")},
        };
    }

    @DataProvider(name = DataProviderNames.VERIFY_UPDATE_USER)
    public static Object[][] verifyUpdateUser() {
        return new Object[][]{
                {"WithRandomNameAndJob", prepareRandomUpdateUserRequest(), getRandomId()},
                {"AsQaAutomationEngineer", new UpdateUserRequest("Jason Kidd ",
                        "QA Automation Engineer"), getRandomId()}
        };
    }

    public static UpdateUserRequest prepareRandomUpdateUserRequest() {
        UpdateUserRequest userRequest = new UpdateUserRequest();
        userRequest.setJob(RandomStringGenerator.createRandomStringWithLen(5));
        userRequest.setName(RandomStringGenerator.createRandomStringAlphanumericWithLen(6));
        return userRequest;
    }

    public static String getRandomId() {
        return "2";
    }


    @DataProvider(name = DataProviderNames.VERIFY_CANNOT_CREATE_USER_WITHOUT_REQUIRED_FIELD)
    public static Object[][] verifyCannotCreateUserWithoutRequiredField() {
        return new Object[][] {
                {"whenUsernameIsNull", new UserRequest(null, RandomStringGenerator.getRandomSex(), Dates.getRandomDate()),
                        RequiredFieldErrorReponseUsers.prepareErrorForUsername(Arrays.asList(ErrorMessages.FIELD_IS_REQUIRED))},
                {"whenEmailIsNull", new UserRequest(RandomStringGenerator.createRandomStringWithLen(6), null, Dates.getRandomDate()), RequiredFieldErrorReponseUsers.prepareErrorForEmail(Arrays.asList(ErrorMessages.FIELD_IS_REQUIRED))},
                {"whenPasswordIsNull", new UserRequest(RandomStringGenerator.createRandomStringWithLen(6), RandomStringGenerator.getRandomSex(), null), RequiredFieldErrorReponseUsers.prepareErrorForPassword(Arrays.asList(ErrorMessages.FIELD_IS_REQUIRED))}
        };
    }

//    @DataProvider(name = DataProviderNames.VERIFY_CANNOT_CREATE_CROCODILE_WITHOUT_REQUIRED_FIELD)
//    public static Object[][] verifyCannotCreateUserWithoutRequiredField() {
//        return new Object[][] {
//                {"whenNameIsNull", new CrocodileRequest(null, RandomStringGenerator.getRandomSex(), Dates.getRandomDate()), RequiredFieldErrorResponse.prepareErrorForName(Arrays.asList(ErrorMessages.FIELD_IS_REQUIRED))},
//                {"whenSexIsNull", new CrocodileRequest(RandomStringGenerator.createRandomStringWithLen(6), null, Dates.getRandomDate()), RequiredFieldErrorResponse.prepareErrorForSex(Arrays.asList(ErrorMessages.FIELD_IS_REQUIRED))},
//                {"whenDateIsNull", new CrocodileRequest(RandomStringGenerator.createRandomStringWithLen(6), RandomStringGenerator.getRandomSex(), null), RequiredFieldErrorResponse.prepareErrorForDate(Arrays.asList(ErrorMessages.FIELD_IS_REQUIRED))}
//        };
//    }
}
