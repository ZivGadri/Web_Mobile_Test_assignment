package manager;

import java.nio.file.Path;
import java.time.Duration;

public class Constants {

    public static final String ANDROID_PLATFORM = "android";
    public static final String IOS_PLATFORM = "ios"; // Not yet setup
    public static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723/wd/hub";
    public static final String SERVER_IP_ADDRESS = "127.0.0.1";
    public static final String BASE_PATH = "/wd/hub";
    public static final String DRIVER_NAME = "uiautomator2";
    public static final String AVD_NAME = "Pixel_6_Pro_API_35";
    public static final String DEVICE_NAME = "emulator-5554";
    public static final String APK_APP_PATH = String.valueOf(Path.of(System.getProperty("user.dir"), "src/test/resources/app", "yummly-8-7.apk"));
    public static final String IPA_APP_PATH = ""; // Not set
    public static final String APP_PACKAGE = "com.yummly.android";
    public static final String APP_ACTIVITY = "com.yummly.android.feature.splash.SplashActivity";
    public static final String UDID = "123456789";
    public static final String SEARCH_VALUE = "tiramisu";
    public static final Duration IMPLICIT_WAIT = Duration.ofSeconds(10);
    public static final Duration EXPLICIT_WAIT = Duration.ofSeconds(5);
}