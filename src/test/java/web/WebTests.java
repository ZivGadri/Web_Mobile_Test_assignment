package web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class WebTests extends BaseTests {
    private static final Logger logger = LogManager.getLogger(WebTests.class);

    public WebTests() {}

    public WebTests(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Test()
    public void testWeb() {
        logger.info("");

    }
}
