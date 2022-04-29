package com.projectname.api.tests.data.provider;

import com.projectname.api.client.data.model.crocodile.CrocodileRequest;
import com.projectname.api.client.data.model.crocodile.RequiredFieldErrorResponse;
import com.projectname.api.client.utils.Dates;
import com.projectname.api.client.utils.RandomStringGenerator;
import com.projectname.api.tests.constants.DataProviderNames;
import com.projectname.api.tests.constants.ErrorMessages;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class CrocodileProvider {

    @DataProvider(name = DataProviderNames.VERIFY_CANNOT_CREATE_CROCODILE_WITHOUT_REQUIRED_FIELD)
    public static Object[][] verifyCannotCreateUserWithoutRequiredField() {
        return new Object[][] {
                {"whenNameIsNull", new CrocodileRequest(null, RandomStringGenerator.getRandomSex(), Dates.getRandomDate()), RequiredFieldErrorResponse.prepareErrorForName(Arrays.asList(ErrorMessages.FIELD_IS_REQUIRED))},
                {"whenSexIsNull", new CrocodileRequest(RandomStringGenerator.createRandomStringWithLen(6), null, Dates.getRandomDate()), RequiredFieldErrorResponse.prepareErrorForSex(Arrays.asList(ErrorMessages.FIELD_IS_REQUIRED))},
                {"whenDateIsNull", new CrocodileRequest(RandomStringGenerator.createRandomStringWithLen(6), RandomStringGenerator.getRandomSex(), null), RequiredFieldErrorResponse.prepareErrorForDate(Arrays.asList(ErrorMessages.FIELD_IS_REQUIRED))}
        };
    }

    public static CrocodileRequest prepareCrocodileRequest() {
        CrocodileRequest createCrocodile = new CrocodileRequest(RandomStringGenerator.createRandomStringWithLen(6), RandomStringGenerator.getRandomSex(), Dates.getRandomDate());
        return createCrocodile;
    }

    public static RequiredFieldErrorResponse prepareErrorMessageForSex() {
        RequiredFieldErrorResponse errorResponse = new RequiredFieldErrorResponse();
        errorResponse.setSex(Arrays.asList(ErrorMessages.FIELD_IS_REQUIRED));
        return errorResponse;
    }

}
