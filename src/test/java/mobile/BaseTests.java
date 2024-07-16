package mobile;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static mobile.manager.drivers.AndroidDriverManager.createAndroidDriver;
import static mobile.manager.drivers.AndroidDriverManager.quitSession;

public class BaseTests {

    @BeforeClass(alwaysRun = true)
    public void testSetup() {
        createAndroidDriver();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        quitSession();
    }
}
