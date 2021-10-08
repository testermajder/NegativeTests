package com.projectname.e2e.tests.selectors;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CustomBy extends By{
    

    public static By testAutomationId(String testDataId) {
        return By.cssSelector("[test-auto-id='" + testDataId + "']");
    }

    public static By xpathWithMoreConditions(XpathBuilder builder) {
        return builder.build();
    }

    @Override
    public List<WebElement> findElements(SearchContext searchContext) {
        return null;
    }

}
