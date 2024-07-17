package mobile.manager;

import java.nio.file.Path;

public class Constants {

    public static final String MOBILE_PLATFORM = "android";
    public static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723/wd/hub";
    public static final String SERVER_IP_ADDRESS = "127.0.0.1";
    public static final String BASE_PATH = "/wd/hub";
    public static final String DRIVER_NAME = "uiautomator2";
    public static final String AVD_NAME = "Pixel_4_API_35";
    public static final String DEVICE_NAME = "emulator-5554";
    public static final String APP_PATH = String.valueOf(Path.of(System.getProperty("user.dir"), "src/main/resources/app", "yummly-8-7.apk"));
    public static final String APP_PACKAGE = "com.yummly.android";
    public static final String APP_ACTIVITY = "com.yummly.android.feature.splash.SplashActivity";
}