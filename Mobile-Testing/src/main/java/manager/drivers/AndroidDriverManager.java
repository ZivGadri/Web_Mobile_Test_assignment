package manager.drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import manager.Constants;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static manager.server.AppiumServerManager.startServer;
import static manager.server.AppiumServerManager.stopServer;

public class AndroidDriverManager {
    private static final ThreadLocal<AndroidDriver> ANDROID_DRIVER = new ThreadLocal<>();
    private static final Logger LOGGER = LogManager.getLogger(AndroidDriverManager.class);

    public static AndroidDriver getDriver() {
        return ANDROID_DRIVER.get();
    }

    public static AndroidDriver createAndroidDriver() {
        startServer(Constants.ANDROID_PLATFORM);
        try {
            setAndroidDriver(new AndroidDriver(new URL(Constants.APPIUM_SERVER_URL), uiAutomator2Options()));
        } catch (final MalformedURLException e) {
            throw new RuntimeException (e);
        }
        setupDriverTimeouts();
        return ANDROID_DRIVER.get();
    }

    public static AndroidDriver getAndroidDriver() {
        return (AndroidDriver) ANDROID_DRIVER.get();
    }

    public static void quitSession() {
        if (ANDROID_DRIVER.get() != null) {
            LOGGER.info("Closing the driver...");
            getAndroidDriver().quit();
            ANDROID_DRIVER.remove();
            stopServer();
        }
    }

    private static void setAndroidDriver(final AndroidDriver androidDriver) {
        ANDROID_DRIVER.set(androidDriver);
    }

    private static void setupDriverTimeouts() {
        ANDROID_DRIVER.get().manage()
                .timeouts()
                .implicitlyWait(Constants.IMPLICIT_WAIT);
    }

    private static UiAutomator2Options uiAutomator2Options() {
        final UiAutomator2Options uiAutomator2Options;
        uiAutomator2Options = new UiAutomator2Options()
                .setAvd(Constants.AVD_NAME)
                .setAvdLaunchTimeout(Duration.ofSeconds(300))
                .setAvdReadyTimeout(Duration.ofSeconds(60))
                .setAndroidInstallTimeout(Duration.ofSeconds(120))
                .setDeviceName(Constants.DEVICE_NAME)
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setApp(Constants.APK_APP_PATH)
                .setNewCommandTimeout(Duration.ofSeconds(300))
                .setAppPackage(Constants.APP_PACKAGE)
                .setAppActivity(Constants.APP_ACTIVITY)
                .setNoReset(false);
        return uiAutomator2Options;
    }
}