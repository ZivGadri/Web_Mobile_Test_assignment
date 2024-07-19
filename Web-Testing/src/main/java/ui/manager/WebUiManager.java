package ui.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import ui.manager.pageHelpers.MainPageHelper;
import ui.manager.pageHelpers.ReadMorePageHelper;
import ui.manager.pageHelpers.StocksPageHelper;

import java.util.HashMap;

public class WebUiManager {
    private static final Logger LOGGER = LogManager.getLogger(WebUiManager.class);
    private final WebDriver driver;
    private MainPageHelper mainPageHelper;
    private StocksPageHelper stocksPageHelper;
    private ReadMorePageHelper readMorePageHelper;
    private final String xmUrl = "https://www.xm.com";

    public WebUiManager(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToMainPage() {
        mainPageHelper = new MainPageHelper(driver, xmUrl);
        mainPageHelper.clickAcceptAllCookiesIfFoundOnPage();
    }

    public void navigateToStocksPage() {
        stocksPageHelper = mainPageHelper.navigateToStocksPage();
    }

    public HashMap<String, String> getRelevantTableData(String symbolAndDescription) {
        stocksPageHelper.clickNorwayFilter();
        return stocksPageHelper.fetchRowData(symbolAndDescription);
    }

    public void clickReadMore(String symbolAndDescription) {
        readMorePageHelper = stocksPageHelper.clickReadMore(symbolAndDescription);
    }

    public HashMap<String, String> getReadMoreTablesData() {
        clickReadMore("Orkla ASA (ORK.OL)");
        return readMorePageHelper.getRowsElementsFromBothTables();
    }

    // Assertion Methods
    public boolean isOpenAnAccountBtnClickable() {
        return mainPageHelper.isOpenAnAccountBtnClickable();
    }

    public String getStocksPageTitle() {
        return stocksPageHelper.getStocksPageTitle();
    }
}