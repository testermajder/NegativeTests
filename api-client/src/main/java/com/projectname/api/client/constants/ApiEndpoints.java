package com.projectname.api.client.constants;

//List of endpoint as constants
public class ApiEndpoints {

    public static final String USERS = "api/users";
    public static final String users(String userId) {
        return USERS + "/" + userId;
    }

    // CROCODILES

    public static final String LOGIN_USER = "auth/token/login/";

    public static final String GET_MY_CROCODILES = "my/crocodiles/";

    public static final String GET_MY_CROCODILE(Integer id) {
        return "my/crocodiles/" + id+ "/";

    }

    public static final String CREATE_CROCODILE = "my/crocodiles/";

    public static final String GET_POSTS = "posts";

    public static final String REGISTER_USER = "user/register/";

}
