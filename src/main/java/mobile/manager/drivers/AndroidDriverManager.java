package mobile.manager.drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import mobile.manager.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;

import static mobile.manager.server.AppiumServerManager.*;

public class AndroidDriverManager {
    private static final ThreadLocal<AndroidDriver> ANDROID_DRIVER = new ThreadLocal<>();
    private static final Logger LOGGER = LogManager.getLogger(AndroidDriverManager.class);

    public static void createAndroidDriver () {
        startServer(Constants.MOBILE_PLATFORM);
        try {
            setAndroidDriver(new AndroidDriver(new URL(Constants.APPIUM_SERVER_URL), uiAutomator2Options()));
        } catch (final MalformedURLException e) {
            throw new RuntimeException (e);
        }
        setupDriverTimeouts();
    }

    public static AndroidDriver getAndroidDriver() {
        return AndroidDriverManager.ANDROID_DRIVER.get ();
    }

    public static void quitSession() {
        if (null != ANDROID_DRIVER.get ()) {
            LOGGER.info ("Closing the driver...");
            getAndroidDriver().quit();
            ANDROID_DRIVER.remove();
            stopServer();
        }
    }

    private static void setAndroidDriver(final AndroidDriver androidDriver) {
        AndroidDriverManager.ANDROID_DRIVER.set(androidDriver);
    }

    private static void setupDriverTimeouts () {
        getAndroidDriver().manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
    }

    private static UiAutomator2Options uiAutomator2Options() {
        final UiAutomator2Options uiAutomator2Options;
        uiAutomator2Options = new UiAutomator2Options()
                .setAvd(Constants.AVD_NAME)
                .setAvdLaunchTimeout(Duration.ofSeconds(300))
                .setAvdReadyTimeout(Duration.ofSeconds(100))
                .setDeviceName(Constants.DEVICE_NAME)
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setApp(Constants.APP_PATH)
                .setNewCommandTimeout(Duration.ofSeconds(300))
                .setAppPackage(Constants.APP_PACKAGE)
                .setAppActivity(Constants.APP_ACTIVITY)
                .setNoReset(false);
        return uiAutomator2Options;
    }
}