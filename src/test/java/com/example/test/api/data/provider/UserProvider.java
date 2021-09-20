package com.example.test.api.data.provider;

import com.example.test.api.common.Functions;
import com.example.test.api.common.RandomStringGenerator;
import com.example.test.api.constants.DataProviderNames;
import com.example.test.api.data.model.users.create.CreateUserRequest;
import org.testng.annotations.DataProvider;

import java.time.LocalDateTime;

public class UserProvider {

    @DataProvider(name = DataProviderNames.VERIFY_CREATE_USER)
    public static Object[][] verifyCreateUser() {
        LocalDateTime date = LocalDateTime.now();
        return new Object[][]{
                {"FirstTestDataCombination", new CreateUserRequest(RandomStringGenerator.createRandomStringAlphanumericWithLen(5),
                        RandomStringGenerator.createRandomStringAlphanumericWithLen(5))},
                {"SecondTestDataCombination", new CreateUserRequest("Jason Kidd " + date.toString(),
                        "QA Automation Engineer")},
        };
    }

}
