package com.projectname.e2e.tests.utils;

import com.projectname.e2e.tests.webdriver.CustomWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckIfElement {

    public static boolean isDisplayed(By by, SearchContext weRoot) {
        return weRoot.findElements(by).size() > 0;
    }

    public static boolean isDisplayed(By by, CustomWebDriver driver) {
        return driver.findElements(by).size() > 0;
    }

    public static boolean isClickable(By by, CustomWebDriver driver) {
        return checkExpectedCondition(ExpectedConditions.elementToBeClickable(by), driver);
    }

    public static boolean isPresent(By by, CustomWebDriver driver) {
        return checkExpectedCondition(ExpectedConditions.presenceOfElementLocated(by), driver);
    }

    public static boolean isPresent(WebElement webElement, By by, CustomWebDriver driver) {
        return checkExpectedCondition(ExpectedConditions.presenceOfNestedElementLocatedBy(webElement, by), driver);
    }

    public static boolean checkExpectedCondition(ExpectedCondition<?> expectedCondition, CustomWebDriver driver) {
        try {
            (new WebDriverWait(driver, StrategyConfig.CHECK_FOR_ELEMENT))
                    .until(expectedCondition);
            return true;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }
}
