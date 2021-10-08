package com.projectname.api.client.utils;

import io.qameta.allure.Allure;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
// listener that is used for allure (manipulate with data shown in reports)
public class MethodNGListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    @SuppressWarnings("deprecation")
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.getTestMethod().isTest()) {
            Allure.getLifecycle().updateTestCase(Allure.getLifecycle().getCurrentTestCase().get(),
                    executable -> executable.getParameters().removeIf(filter -> filter.getName().contains("arg")));
        }
    }

}
