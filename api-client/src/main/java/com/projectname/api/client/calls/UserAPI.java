package com.projectname.api.client.calls;

import com.projectname.api.client.constants.ApiEndpoints;
import com.projectname.api.client.data.model.users.RequiredFieldErrorReponseUsers;
import com.projectname.api.client.data.model.users.UserRequest;
import com.projectname.api.client.data.model.users.byid.GetUserByIdResponse;
import com.projectname.api.client.data.model.users.create.CreateUserRequest;
import com.projectname.api.client.data.model.users.create.CreateUserResponse;
import com.projectname.api.client.data.model.users.update.UpdateUserRequest;
import com.projectname.api.client.data.model.users.update.UpdateUserResponse;
import com.projectname.api.client.utils.GsonFunctions;
import com.projectname.api.client.utils.ResponseValidation;
import com.projectname.api.client.utils.RestAssuredFunctions;
import io.restassured.response.Response;

//Methods that help parse json into model classes
public class UserAPI {

    public static CreateUserResponse createUser(CreateUserRequest createRequest) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.post(createRequest, ApiEndpoints.USERS), CreateUserResponse.class);
    }

    public static GetUserByIdResponse getUserById(String userId) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.get(ApiEndpoints.users(userId)), GetUserByIdResponse.class);
    }

    //Method used to parse and validate response for Integration tests
    public static ResponseValidation validateGetUserByIdResponse(String userId) {
        Response jsonResponse = RestAssuredFunctions.get(ApiEndpoints.users(userId));
        return GsonFunctions.verifyResponse(jsonResponse, GetUserByIdResponse.class);
    }

    public static UpdateUserResponse updateUser(UpdateUserRequest userRequest, String userId) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.put(userRequest, ApiEndpoints.users(userId)), UpdateUserResponse.class);
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


    public static RequiredFieldErrorReponseUsers createUserWithRequiredFieldError(UserRequest userRequest) {
        return  GsonFunctions.parseErrorResponseToModel(RestAssuredFunctions.post(userRequest, ApiEndpoints.REGISTER_USER), RequiredFieldErrorReponseUsers.class);
    }

}
