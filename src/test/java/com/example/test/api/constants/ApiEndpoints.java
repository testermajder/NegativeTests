package com.example.test.api.constants;

//List of endpoint as constants
public class ApiEndpoints {

    public static final String USERS = "api/users";
    public static final String USERS(String userId) {
        return USERS + "/" + userId;
    }

}
