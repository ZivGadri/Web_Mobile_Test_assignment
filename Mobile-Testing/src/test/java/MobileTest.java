import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class MobileTest extends TestFlowMobile {
    private static final Logger LOGGER = LogManager.getLogger(MobileTest.class);
    public MobileTest() {}

    public MobileTest(String mobilePlatform) {
        this.mobilePlatform = mobilePlatform;
    }
    @Test
    public void androidTest() {
        LOGGER.info("Starting Yummly app test flow");
        testYummlyApp();
    }

}
