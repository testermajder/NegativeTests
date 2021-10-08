package com.projectname.e2e.tests.webdriver;

import com.projectname.e2e.tests.utils.StrategyConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class CustomWebDriver implements WebDriver, JavascriptExecutor, TakesScreenshot {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CustomWebDriver(WebDriver driver) {
        this(driver, StrategyConfig.ELEMENT_WAIT_SECONDS, StrategyConfig.SLEEP_IN_MILLIS);
    }

    public CustomWebDriver(WebDriver driver, long timeOutInSeconds) {
        this(driver, timeOutInSeconds, StrategyConfig.SLEEP_IN_MILLIS);
    }

    public CustomWebDriver(WebDriver driver, long timeOutInSeconds, long sleepInMillis) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeOutInSeconds, sleepInMillis);
    }

    @Override
    public void get(String url) {
        driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        waitForElementToBePresent(by);
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }

    @Override
    public Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeAsyncScript(script, args);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return ((TakesScreenshot) driver).getScreenshotAs(target);
    }

    public void addMouseFollowOnClick(){
        executeScript("if(!(\"seleniumFollowerImg\" in window)) {\n" +
                "seleniumFollowerImg = document.createElement(\"img\");\n" +
                "seleniumFollowerImg.setAttribute('src', 'data:image/png;base64,'\n" +
                "    + 'iVBORw0KGgoAAAANSUhEUgAAABQAAAAeCAQAAACGG/bgAAAAAmJLR0QA/4ePzL8AAAAJcEhZcwAA'\n" +
                "    + 'HsYAAB7GAZEt8iwAAAAHdElNRQfgAwgMIwdxU/i7AAABZklEQVQ4y43TsU4UURSH8W+XmYwkS2I0'\n" +
                "    + '9CRKpKGhsvIJjG9giQmliHFZlkUIGnEF7KTiCagpsYHWhoTQaiUUxLixYZb5KAAZZhbunu7O/PKf'\n" +
                "    + 'e+fcA+/pqwb4DuximEqXhT4iI8dMpBWEsWsuGYdpZFttiLSSgTvhZ1W/SvfO1CvYdV1kPghV68a3'\n" +
                "    + '0zzUWZH5pBqEui7dnqlFmLoq0gxC1XfGZdoLal2kea8ahLoqKXNAJQBT2yJzwUTVt0bS6ANqy1ga'\n" +
                "    + 'VCEq/oVTtjji4hQVhhnlYBH4WIJV9vlkXLm+10R8oJb79Jl1j9UdazJRGpkrmNkSF9SOz2T71s7M'\n" +
                "    + 'SIfD2lmmfjGSRz3hK8l4w1P+bah/HJLN0sys2JSMZQB+jKo6KSc8vLlLn5ikzF4268Wg2+pPOWW6'\n" +
                "    + 'ONcpr3PrXy9VfS473M/D7H+TLmrqsXtOGctvxvMv2oVNP+Av0uHbzbxyJaywyUjx8TlnPY2YxqkD'\n" +
                "    + 'dAAAAABJRU5ErkJggg==');\n" +
                "seleniumFollowerImg.setAttribute('id', 'selenium_mouse_follower');\n" +
                "seleniumFollowerImg.setAttribute('style', 'position: absolute; z-index: 99999999999; pointer-events: none;');\n" +
                "\n" +
                "// Add mouse follower to the web page.\n" +
                "document.body.appendChild(seleniumFollowerImg);\n" +
                "\n" +
                "// Track mouse movements and re-position the mouse follower.\n" +
                "document.onmousemove = function(e) {\n" +
                "const mousePointer = document.getElementById('selenium_mouse_follower');\n" +
                "mousePointer.style.left = e.pageX + 'px';\n" +
                "mousePointer.style.top = e.pageY + 'px';\n" +
                "}\n" +
                "}");
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBePresent(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForNestedElementToBePresent(WebElement webElement, By by) {
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(webElement, by));
    }

    public WebElement waitForVisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}

