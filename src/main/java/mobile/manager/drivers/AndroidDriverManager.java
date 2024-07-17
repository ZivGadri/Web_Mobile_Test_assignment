package mobile.manager.drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;

import static mobile.manager.server.AppiumServerManager.*;

public class AndroidDriverManager {
    private static final String APP_PATH = String.valueOf(Path.of(System.getProperty("user.dir"), "src/main/resources/app", "yummly-8-7.apk"));
    private static final ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<>();
    private static final Logger LOG = LogManager.getLogger(AndroidDriverManager.class);

    public static void createAndroidDriver () {
        startServer("android");
        try {
            setDriver(new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),uiAutomator2OptionsWdio()));
        } catch (final MalformedURLException e) {
            throw new RuntimeException (e);
        }
        setupDriverTimeouts();
    }

    public static AndroidDriver getDriver() {
        return AndroidDriverManager.DRIVER.get ();
    }

    public static void quitSession() {
        if (null != DRIVER.get ()) {
            LOG.info ("Closing the driver...");
            getDriver().quit();
            DRIVER.remove();
            stopServer();
        }
    }

    private static void setDriver (final AndroidDriver driver) {
        AndroidDriverManager.DRIVER.set(driver);
    }

    private static void setupDriverTimeouts () {
        getDriver().manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
    }

    private static UiAutomator2Options uiAutomator2OptionsWdio() {
        final UiAutomator2Options uiAutomator2Options;
        uiAutomator2Options = new UiAutomator2Options()
                .setAvd("Pixel_4_API_35")
                .setAvdLaunchTimeout(Duration.ofSeconds(300))
                .setAvdReadyTimeout(Duration.ofSeconds(100))
                .setDeviceName("emulator-5554")
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setApp(APP_PATH)
                .setNewCommandTimeout(Duration.ofSeconds(300))
                .setAppPackage("com.yummly.android")
                .setAppActivity("com.yummly.android.feature.splash.SplashActivity")
                .setNoReset(false);
        return uiAutomator2Options;
    }
}