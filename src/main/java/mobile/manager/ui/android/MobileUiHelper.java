package mobile.manager.ui.android;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import mobile.manager.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static mobile.manager.drivers.AndroidDriverManager.getDriver;

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
                checkForThePresenceOfPopupAndRemove();
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
            } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
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
            swipe();
            List<WebElement> ingredientsPlusButtons = androidDriver.findElements(by);
            return ingredientsPlusButtons.get(ingredientsPlusButtons.size() - 1);
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
        WebDriverWait popupWait = new WebDriverWait(androidDriver, Duration.ofSeconds(2));
        try {
            popupWait.until(ExpectedConditions.elementToBeClickable(by)).click();
            threadSleep(5, "right after ");
        } catch (NoSuchElementException | TimeoutException e) {
            LOGGER.info("No popup detected");
        }
    }

    /*protected void swipe(Point start, Point end, Duration duration) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(MouseButton.LEFT.ordinal()));
        duration = duration.dividedBy(*//*ANDROID_SCROLL_DIVISOR*//*2);

    }*/


    protected void swipe() {
        Dimension dimension = getWindowSize();
        int width = dimension.getWidth();
        int height = dimension.getHeight();
        ((JavascriptExecutor) androidDriver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "left", width * 0.5, "top", height * 0.5, "width", width, "height", height,
                "direction", "up",
                "percent", 0.75
        ));
    }

    private Dimension getWindowSize() {
        Dimension windowSize;
            windowSize = getDriver().manage().window().getSize();
        return windowSize;
    }

    /*protected void swipe(double startXPct, double startYPct, double endXPct, double endYPct, Duration duration) {
        Dimension size = getWindowSize();
        Point start = new Point((int)(size.width * startXPct), (int)(size.height * startYPct));
        Point end = new Point((int)(size.width * endXPct), (int)(size.height * endYPct));
        swipe(start, end, duration);
    }*/


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