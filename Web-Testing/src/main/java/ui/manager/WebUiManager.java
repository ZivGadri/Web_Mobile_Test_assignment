package ui.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import ui.manager.pageHelpers.MainPageHelper;
import ui.manager.pageHelpers.StocksPageHelper;

import java.util.HashMap;

public class WebUiManager {
    private static final Logger LOGGER = LogManager.getLogger(WebUiManager.class);
    private Dimension screenSize;
    private final WebDriver driver;
    private MainPageHelper mainPageHelper;
    private StocksPageHelper stocksPageHelper;
    private final String xmUrl = "https://www.xm.com";

    public WebUiManager(WebDriver driver) {
        this.driver = driver;
        this.screenSize = driver.manage().window().getSize();
    }

    public void navigateToMainPage() {
        mainPageHelper = new MainPageHelper(driver, xmUrl, screenSize);
    }

    public void clickOnAcceptAllCookies() {
        mainPageHelper.clickAcceptAllCookiesIfFoundOnPage();
    }

    public void navigateToStocksPage() {
        stocksPageHelper = mainPageHelper.navigateToStocksPage();
    }

    public HashMap<String, String> getRelevantTableData(String symbolAndDescription) {
        stocksPageHelper.clickNorwayFilter();
        return stocksPageHelper.initRelevantRowData(symbolAndDescription);
    }
}