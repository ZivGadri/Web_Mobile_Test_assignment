package web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestFlows extends BaseTests {

    private static final Logger logger = LogManager.getLogger(TestFlows.class);
    protected void orkalaAsaData() {
        uiManager.navigateToMainPage();
        logger.info("");
    }
}
