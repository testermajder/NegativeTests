package com.projectname.api.client.utils;

import io.qameta.allure.Step;

public class Allure {

    @Step("{0}")
    public static void logStep(final String message) {
    }
}
