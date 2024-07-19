package ui.manager;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PageObject {
    private static final Logger LOGGER = LogManager.getLogger(PageObject.class);
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Dimension screenSize;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        this.screenSize = driver.manage().window().getSize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
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

    public void clickButton(WebElement element, String buttonTitle, boolean scrollIntoView) {
        try {
            LOGGER.info("Clicking on {} button", buttonTitle);
            clickButtonByWebElement(element, Duration.ofSeconds(5), scrollIntoView);
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

    public void clickButtonIfPresent(WebElement element, String buttonTitle, boolean scrollIntoView, boolean failIfNotPresent) {
        if (isElementFoundInPage(element)) {
            clickButton(element, buttonTitle, scrollIntoView);
        } else {
            if (failIfNotPresent) {
                throw new NoSuchElementException(String.format("The button %s could not be found.", buttonTitle));
            }
        }
    }

    private void clickButtonByWebElement(WebElement element, Duration seconds, boolean scrollIntoView) {
        if (scrollIntoView) scrollIntoCenterView(element);
        try {
            waitForClickable(seconds, element).click();
        } catch (ElementNotInteractableException enie) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click", element);
        }
    }

    public void scrollIntoCenterView(WebElement element) {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        } catch (Exception e) {
            LOGGER.debug("Unable to scroll into element view: " + element, e);
        }
    }

    public void scrollIntoBottomView(WebElement element) {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'end'});", element);
        } catch (Exception e) {
            LOGGER.debug("Unable to scroll into element view: " + element, e);
        }
    }

    public void scrollIntoTopView(WebElement element) {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true)", element);
        } catch (Exception e) {
            LOGGER.debug("Unable to scroll into element view: " + element, e);
        }
    }

    public String getElementTextUsingJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return  (String) js.executeScript("return arguments[0].innerText;", element);
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

    public List<WebElement> getTableRows(WebElement tableElement) {
        try {
            return tableElement.findElements(By.xpath("./tbody/tr"));
        } catch (NoSuchElementException nse) {
            throw new NoSuchElementException("Could not find the table rows");
        }
    }
}