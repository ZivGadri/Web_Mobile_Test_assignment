package mobile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class AndroidTests extends BaseTests {
    private static final Logger logger = LogManager.getLogger(AndroidTests.class);
    @Test
    public void androidTest() {
        logger.info("");
        // An empty test to check the mobile driver connection
    }

}
