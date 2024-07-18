package web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import web.manager.WebUiManager;
import web.manager.drivers.WebDriverFactory;
import web.manager.models.BrowserTypes;


public class WebBaseTests {
    private static final Logger LOGGER = LogManager.getLogger(WebBaseTests.class);
    private static WebDriverFactory webDriverFactory;
    protected WebUiManager webUiManager;
    private WebDriver driver;
    protected int width;
    protected int height;
    @BeforeClass
    public void beforeClass() {
        LOGGER.info("Starting before class initializations...");
        webDriverFactory = new WebDriverFactory(BrowserTypes.CHROME.getBrowserType());
    }

    @BeforeMethod
    @Parameters()
    public void beforeMethod() {
        Dimension dimension = new Dimension(width, height);
        driver = webDriverFactory.initWebDriver(dimension);
        webUiManager = new WebUiManager(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}