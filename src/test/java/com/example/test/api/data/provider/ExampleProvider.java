package com.example.test.api.data.provider;

import com.example.test.api.constants.DataProviderNames;
import org.testng.annotations.DataProvider;

public class ExampleProvider {

    @DataProvider(name = DataProviderNames.VERIFY_EXAMPLE)
    public static Object[][] verifyExample() {
        return new Object[][]{
                {"FirstTestDataCombination", "accessToken", new String()},
                {"SecondTestDataCombination", "accessToken", new String()},
                {"ThirdTestDataCombination", "accessToken", new String()},
                {"ForthTestDataCombination", "accessToken", new String()},
                {"FifthTestDataCombination", "accessToken", new String()},
                {"SixthTestDataCombination", "accessToken", new String()},
                {"SeventhTestDataCombination", "accessToken", new String()},
        };
    }

}
