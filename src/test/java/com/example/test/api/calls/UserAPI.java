package com.example.test.api.calls;

import com.example.test.api.common.GsonFunctions;
import com.example.test.api.common.ResponseValidation;
import com.example.test.api.common.RestAssuredFunctions;
import com.example.test.api.constants.ApiEndpoints;
import com.example.test.api.data.model.users.byid.GetUserByIdResponse;
import com.example.test.api.data.model.users.create.CreateUserRequest;
import com.example.test.api.data.model.users.create.CreateUserResponse;
import com.example.test.api.data.model.users.list.ListUsersResponse;
import io.restassured.response.Response;

//Methods that help parse json into model classes
public class UserAPI {

    public static CreateUserResponse createUser(CreateUserRequest createRequest) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.post(createRequest, ApiEndpoints.USERS), CreateUserResponse.class);
    }

    public static GetUserByIdResponse getUserById(String userId) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.get(ApiEndpoints.USERS(userId)), GetUserByIdResponse.class);
    }

    //Method used to parse and validate response for Integration tests
    public static ResponseValidation validateGetUserByIdResponse(String userId) {
        Response jsonResponse = RestAssuredFunctions.get(ApiEndpoints.USERS(userId));
        return GsonFunctions.verifyResponse(jsonResponse, GetUserByIdResponse.class);
    }

//example for retrieving list as response
//    public static List<ListOfObject> getListResponse(String accessToken) {
//        return GsonFunctions.parseSuccessResponseAsListToModel(RestAssuredFunctions.get(accessToken, ApiEndpoints.USERS), ListOfObject[].class);
//    }

//example for retrieving list as response for integration test
//    public static ResponseValidation validateListResponse(String accessToken) {
//        Response jsonResponse = RestAssuredFunctions.get(accessToken, ApiEndpoints.USERS);
//        return GsonFunctions.verifyResponse(jsonResponse, ResponseValidation[].class);
//    }
}
