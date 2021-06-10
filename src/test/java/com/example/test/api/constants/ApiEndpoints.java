package com.example.test.api.constants;

//List of endpoint as constants
public class ApiEndpoints {

    public static final String EXAMPLE_ENDPOINT = "api/example/test";
    public static final String EXAMPLE_ENDPOINT(String id) {
        return "api/example/test" + id;
    }

}
