package com.projectname.performance.tests;

import com.projectname.api.client.calls.UserAPI;
import com.projectname.api.client.data.model.users.create.CreateUserRequest;
import com.projectname.api.client.utils.RandomStringGenerator;
import com.projectname.api.tests.init.TestBase;
import org.testng.annotations.Test;

import static com.projectname.api.client.utils.Allure.logStep;

public class PrepareDataForTests extends TestBase {

    //If you are doing performance benchmarking and you need to prepare data you can reuse testng and your written API methods
    //to generate those data automatically before you start performance tests. If you are using jenkins for example first run this job and
    //if it failed then abort execution of performance tests
    @Test(groups = {"performance"})
    public void prepareTestData() {
        logStep("INFO: Add new data for performance user");
        UserAPI.createUser(new CreateUserRequest(RandomStringGenerator.createRandomStringAlphanumericWithLen(5),
                RandomStringGenerator.createRandomStringAlphabeticWithLen(6)));
        logStep("PASS: Data is added");
    }
}