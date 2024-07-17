package web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import web.manager.UiManager;
import web.manager.drivers.WebDriverFactory;
import web.manager.models.BrowserTypes;


public class BaseTests {
    private static final Logger logger = LogManager.getLogger(BaseTests.class);
    private static WebDriverFactory webDriverFactory;
    protected UiManager uiManager;
    private WebDriver driver;
    protected int width;
    protected int height;
    @BeforeClass
    public void beforeClass() {
        logger.info("Starting before class initializations...");
        webDriverFactory = new WebDriverFactory(BrowserTypes.CHROME.getBrowserType());
    }

    @BeforeMethod
    @Parameters()
    public void beforeMethod() {
        Dimension dimension = new Dimension(width, height);
        driver = webDriverFactory.initWebDriver(dimension);
        uiManager = new UiManager(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}