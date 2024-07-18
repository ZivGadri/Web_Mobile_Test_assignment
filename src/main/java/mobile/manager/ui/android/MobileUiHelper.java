package mobile.manager.ui.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import mobile.manager.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MobileUiHelper {
    private static final Logger LOGGER = LogManager.getLogger(MobileUiHelper.class);
    private AndroidDriver androidDriver;
    private WebDriverWait wait;

    public MobileUiHelper() {}

    public MobileUiHelper(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
        wait = new WebDriverWait(androidDriver, Constants.EXPLICIT_WAIT);
    }
    protected void clickButtonIfExists(By by, String elementTitle, boolean failTestIfNotFound) {
        if (androidDriver != null) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(androidDriver.findElement(by)));
                clickButtonIfExists(element, elementTitle, failTestIfNotFound);
            } catch (NoSuchElementException | TimeoutException nsee) {
                LOGGER.info("Element '{}' was not found. Skipping click", elementTitle);
                if (failTestIfNotFound) {
                    throw new NoSuchElementException("Failed test for not being able to click on element");
                }
            }
        }
    }

    protected void clickButtonIfExists(WebElement element, String elementTitle, boolean failTestIfNotFound) {
        if (androidDriver != null) {
            try {
                checkForThePresenceOfPopupAndRemove();
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                LOGGER.info("Clicked on: '{}'", elementTitle);
                threadSleep(3);
            } catch (NoSuchElementException | TimeoutException nsee) {
                LOGGER.info("Element '{}' was not found. Skipping click", elementTitle);
                if (failTestIfNotFound) {
                    throw new NoSuchElementException("Failed test for not being able to click on element");
                }
            }
        }
    }

    public void insertKeys(By by, String text) {
        if (androidDriver != null) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                element.sendKeys(text);
                threadSleep(2);
            } catch (NoSuchElementException | TimeoutException e) {
                throw new RuntimeException("Test Failed\nMore details:\n" + e.getMessage());
            }
        }
    }

    protected WebElement getLastIngredientPlusButton(By by) {
        if (androidDriver != null) {
            threadSleep(3);
            List<WebElement> ingredients = androidDriver.findElements(by);
            WebElement lastIngredient = ingredients.get(ingredients.size() - 1);
            return lastIngredient.findElement(ElementsLocators.lastIngredientPlusBtn);
        }
        throw new RuntimeException();
    }

    protected void clickAndroidKey(AndroidKey key) {
        if (androidDriver != null) {
            androidDriver.pressKey(new KeyEvent(key));
            threadSleep(2);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            androidDriver.findElement(by);
            return true;
        } catch (NoSuchElementException nse) {
            return false;
        }
    }

    private void checkForThePresenceOfPopupAndRemove() {
        By by = ElementsLocators.noThanksBtn;
        WebDriverWait popupWait = new WebDriverWait(androidDriver, Duration.ofSeconds(5));
        try {
            popupWait.until(ExpectedConditions.elementToBeClickable(by)).click();
            threadSleep(5, "right after ");
        } catch (NoSuchElementException nse) {
            LOGGER.info("No popup detected");
        }

    }

    private void threadSleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            LOGGER.info("Thread has paused for {} seconds\n", seconds);
        } catch (InterruptedException ie) {
        }
    }

    private void threadSleep(int seconds, String message) {
        threadSleep(seconds);
        LOGGER.info(message);
    }
}