package web.manager;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageObject {
    private static final Logger LOGGER = LogManager.getLogger(PageObject.class);
    private static final Duration DEFAULT_EXPLICIT_WAIT = Duration.ofSeconds(10);
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Dimension screenSize;

    public PageObject() {
        waitForPageLoad();
    }

    public PageObject(WebDriver driver, Dimension screenSize) {
        this.driver = driver;
        this.screenSize = screenSize;
        wait = new WebDriverWait(driver, DEFAULT_EXPLICIT_WAIT);
        waitForPageLoad();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void navigateToUrl(String url) {
        LOGGER.info("navigating to url: {}", url);
        driver.get(url);
        waitForPageLoad();
    }

    public void waitForPageLoad() {
        LOGGER.info("Waiting for page to load...");
        wait.until((ExpectedCondition<Boolean>) driver -> {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            return jsExecutor.executeScript("return document.readyState").equals("complete");
        });
    }

    public void clickButton(WebElement element, String buttonTitle) {
        try {
            LOGGER.info("Clicking on {} button", buttonTitle);
            clickButtonByWebElement(element, Duration.ofSeconds(5));
            threadSleepLog(2, "after clicking button " + buttonTitle);
        } catch (NoSuchElementException nsee) {
            LOGGER.error("Failed to click on " + buttonTitle + " button.\nMore info: " + nsee.getMessage());
            throw new NoSuchElementException("Failed to click on " + buttonTitle + " button");
        } catch (TimeoutException te) {
            LOGGER.error("Wait interrupted.\nMore info: " + te.getMessage());
            throw new NoSuchElementException("Wait interrupted");
        } catch (Exception e) {
            LOGGER.error("Failed to click on " + buttonTitle + " button.\nMore info: " + e.getMessage());
            throw new RuntimeException("Failed to click on " + buttonTitle + " button");
        }
    }

    public void clickButtonIfPresent(WebElement element, String buttonTitle, boolean failIfNotPresent) {
        if (isElementFoundInPage(element)) {
            clickButton(element, buttonTitle);
        } else {
            if (failIfNotPresent) {
                throw new NoSuchElementException(String.format("The button %s could not be found.", buttonTitle));
            }
        }
    }

    public void sendText(WebElement element, String textToSend) {
        LOGGER.info("Inserting text to text field");
        clearTextField(element);
        element.sendKeys(textToSend);
        threadSleepLog(1, String.format("after text '%s' inserted to text box.", textToSend));
    }

    private void clearTextField(WebElement element) {
        int textSize = element.getAttribute("value").length();
        String backSpace = StringUtils.repeat("\b", textSize);

        while (!element.getAttribute("value").isEmpty()) {
            element.sendKeys(backSpace);
        }
    }

    private void clickButtonByWebElement(WebElement element, Duration seconds) {
        scrollIntoView(element);
        try {
            waitForClickable(seconds, element).click();
        } catch (ElementNotInteractableException enie) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click", element);
        }
    }

    private Object scrollIntoView(WebElement element) {
        try {
            return ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true)", element);
        } catch (Exception e) {
            LOGGER.debug("Unable to scroll into element view: " + element, e);
        }
        return null;
    }

    private WebElement waitForClickable(Duration seconds, WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), seconds);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void threadSleepLog(long sec, String extraDetails) {
        LOGGER.info("Thread is sleeping for {} second(s) {}", sec, extraDetails);
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public boolean isElementFoundInPage(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException | TimeoutException nsee) {
            LOGGER.info("Element was not found in page");
            return false;
        }
    }

    public WebElement findElementByParentElement(WebElement parent, By findChildBy) {
        WebElement elementToReturn;
        try {
            elementToReturn = parent.findElement(findChildBy);
        } catch (NoSuchElementException nsee) {
            LOGGER.error("Could not find the child element by the WebElement and provided search info.");
            throw new NoSuchElementException("Could not find the child element by the WebElement and provided search info.");
        }
        return elementToReturn;
    }
}