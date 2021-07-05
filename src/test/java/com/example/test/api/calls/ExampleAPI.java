package com.example.test.api.calls;

import com.example.test.api.common.GsonFunctions;
import com.example.test.api.common.ResponseValidation;
import com.example.test.api.common.RestAssuredFunctions;
import com.example.test.api.constants.ApiEndpoints;
import com.example.test.api.data.model.example.ExampleRequest;
import com.example.test.api.data.model.example.ExampleResponse;
import com.example.test.api.data.model.example.ListOfObject;
import io.restassured.response.Response;

import java.util.List;

//Methods that help parse json into model classes
public class ExampleAPI {

    public static ExampleResponse createExample(String accessToken, ExampleRequest createRequest) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.post(createRequest, accessToken, ApiEndpoints.EXAMPLE_ENDPOINT), ExampleResponse.class);
    }

    public static ExampleResponse anotherCreateExample(String accessToken, String request) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.post(accessToken, ApiEndpoints.EXAMPLE_ENDPOINT(request)), ExampleResponse.class);
    }

    public static ExampleResponse getExample(String accessToken) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.get(accessToken, ApiEndpoints.EXAMPLE_ENDPOINT), ExampleResponse.class);
    }

    //Method used to parse and validate response for Integration tests
    public static ResponseValidation validateExampleResponse(String accessToken) {
        Response jsonResponse = RestAssuredFunctions.get(accessToken, ApiEndpoints.EXAMPLE_ENDPOINT);
        return GsonFunctions.verifyResponse(jsonResponse, ExampleResponse.class);
    }

    //example for retrieving list as response
    public static List<ListOfObject> getListResponse(String accessToken) {
        return GsonFunctions.parseSuccessResponseAsListToModel(RestAssuredFunctions.get(accessToken, ApiEndpoints.EXAMPLE_ENDPOINT), ListOfObject[].class);
    }

    //example for retrieving list as response for integration test
    public static ResponseValidation validateListResponse(String accessToken) {
        Response jsonResponse = RestAssuredFunctions.get(accessToken, ApiEndpoints.EXAMPLE_ENDPOINT);
        return GsonFunctions.verifyResponse(jsonResponse, ResponseValidation[].class);
    }
}
