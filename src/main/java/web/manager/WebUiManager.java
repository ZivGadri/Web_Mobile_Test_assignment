package web.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import web.manager.pageHelpers.MainPageHelper;

public class WebUiManager {
    private static final Logger LOGGER = LogManager.getLogger(WebUiManager.class);
    private Dimension screenSize;
    private final WebDriver driver;
    private MainPageHelper mainPageHelper;
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

    public void navigateToTradingPage() {

    }


}