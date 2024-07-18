package mobile;

import mobile.manager.Constants;
import mobile.manager.ui.android.MobileUiManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static mobile.manager.drivers.AndroidDriverManager.quitSession;

public class BaseTestsMobile {
    protected String mobilePlatform;
    protected MobileUiManager mobileUiManager;

    @BeforeClass(alwaysRun = true)
    public void testSetup() {
        mobileUiManager = new MobileUiManager(Constants.ANDROID_PLATFORM);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        quitSession();
    }
}
