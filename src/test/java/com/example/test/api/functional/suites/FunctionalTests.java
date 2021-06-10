package com.example.test.api.functional.suites;

import com.example.test.api.calls.ExampleAPI;
import com.example.test.api.common.init.TestBase;
import com.example.test.api.constants.DataProviderNames;
import com.example.test.api.data.model.example.ExampleRequest;
import com.example.test.api.data.model.example.ExampleResponse;
import com.example.test.api.data.model.example.ObjectValue;
import com.example.test.api.data.provider.ExampleProvider;
import com.example.test.api.functional.asserts.ExampleAssert;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.util.Arrays;
// each test class should extend TestBase in order to inherit all common behaviors and to be logged properly in report
public class FunctionalTests extends TestBase {

    // each test case should be simple, readable, in few lines and without any to technical steps. It should represent
    // user actions like as those are done manually on UI
    @Test(groups = {"regression", "smoke"})
    @Description("Allure description for this test")
    public static void createExample() {
        ExampleRequest createExample = new ExampleRequest("string", true, 1, Arrays.asList("list"),
                new ObjectValue("property"));

        logStep("INFO: Create example");
        ExampleResponse exampleActual = ExampleAPI.createExample("123456", createExample);
        logStep("PASS: Example is created");

        ExampleResponse exampleExpected = new ExampleResponse();
        exampleExpected.parseResponse(createExample);

        ExampleAssert exampleAssert = new ExampleAssert();
        logStep("INFO: Verify created example");
        exampleAssert.assertCreatedNoi(exampleActual, exampleExpected);
        logStep("PASS: Created example is verified");
    }

    // test case with provider
    @Test(groups = {"regression", "smoke"}, dataProvider = DataProviderNames.VERIFY_EXAMPLE, dataProviderClass = ExampleProvider.class)
    @Description("Allure description for this test")
    public static void providerExample(String methodNameSuffix, String accessToken, String newString) {

        logStep("INFO: Create example");
        ExampleResponse exampleActual = ExampleAPI.anotherCreateExample("accessToken", newString);
        logStep("PASS: Example is created");

        ExampleResponse exampleExpected = new ExampleResponse();
        exampleExpected.parseResponse(newString);

        ExampleAssert exampleAssert = new ExampleAssert();
        logStep("INFO: Verify created example");
        exampleAssert.assertCreatedNoi(exampleActual, exampleExpected);
        logStep("PASS: Created example is verified");
    }

}
