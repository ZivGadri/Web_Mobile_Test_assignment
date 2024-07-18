import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class TestFlowWeb extends BaseTestsWeb {
    private static final Logger LOGGER = LogManager.getLogger(TestFlowWeb.class);
    protected void orkalaAsaData() {
        webUiManager.navigateToMainPage();
        webUiManager.clickOnAcceptAllCookies();
        webUiManager.navigateToStocksPage();
        HashMap<String, String> tableDataMap = webUiManager.getRelevantTableData("Orkla ASA (ORK.OL)");
        LOGGER.info(tableDataMap);
        LOGGER.info("");
    }
}
