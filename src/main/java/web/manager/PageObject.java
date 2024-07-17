package web.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageObject {
    private static final Logger logger = LogManager.getLogger(PageObject.class);
    private static final Duration DEFAULT_EXPLICIT_WAIT = Duration.ofSeconds(10);
    protected WebDriver driver;
    protected WebDriverWait wait;

    public PageObject() {}

    public PageObject(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, DEFAULT_EXPLICIT_WAIT);
        waitForPageLoad();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void navigateToUrl(String url) {
        logger.info("navigating to url: {}", url);
        driver.get(url);
        waitForPageLoad();
    }

    public void waitForPageLoad() {
        logger.info("Waiting for page to load...");
        wait.until((ExpectedCondition<Boolean>) driver -> {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            return jsExecutor.executeScript("return document.readyState").equals("complete");
        });
    }
}