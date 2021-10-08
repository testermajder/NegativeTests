package com.projectname.api.client.constants;

//List of endpoint as constants
public class ApiEndpoints {

    public static final String USERS = "api/users";
    public static final String users(String userId) {
        return USERS + "/" + userId;
    }

}
