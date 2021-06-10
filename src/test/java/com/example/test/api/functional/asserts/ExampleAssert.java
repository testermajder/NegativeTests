package com.example.test.api.functional.asserts;

import com.example.test.api.data.model.example.ExampleResponse;
import org.testng.asserts.SoftAssert;

public class ExampleAssert {

    private SoftAssert softAssert;

    public ExampleAssert() {
        this.softAssert = new SoftAssert();
    }

    // soft assert that do complete check and than log all errors found in test case
    public void assertCreatedNoi(ExampleResponse exampleActual, ExampleResponse exampleExpected) {
        this.softAssert.assertEquals(exampleActual.getStringValue(), exampleExpected.getStringValue(), "getStringValue didn't match");
        this.softAssert.assertEquals(exampleActual.getBooleanValue(), exampleExpected.getBooleanValue(), "getBooleanValue didn't match");
        this.softAssert.assertEquals(exampleActual.getIntValue(), exampleExpected.getIntValue(), "getIntValue didn't match");
        this.softAssert.assertEquals(exampleActual.getListValue(), exampleExpected.getListValue(), "getListValue didn't match");
        this.softAssert.assertEquals(exampleActual.getObjectValue(), exampleExpected.getObjectValue(), "getObjectValue didn't match");
        if (exampleActual.getListOfObjects().size() != exampleExpected.getListOfObjects().size()) {
            this.softAssert.fail("Actual list count of NOIS didn't match: Actual[" + exampleActual.getListOfObjects().size() + "], expected[" + exampleExpected.getListOfObjects().size() + "]");
        } else {
            for (int i = 0; i < exampleActual.getListOfObjects().size(); i++) {
                this.softAssert.assertEquals(exampleActual.getListOfObjects().get(i).getProperty(), exampleExpected.getListOfObjects().get(i).getProperty(), "getProperty didn't match");
                this.softAssert.assertEquals(exampleActual.getListOfObjects().get(i).getValue(), exampleExpected.getListOfObjects().get(i).getValue(), "getValue didn't match");
            }
        }
        // this line is used to trigger checks above
        this.softAssert.assertAll();
    }
}
