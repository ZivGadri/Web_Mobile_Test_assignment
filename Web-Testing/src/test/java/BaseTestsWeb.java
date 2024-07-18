import ui.manager.drivers.WebDriverFactory;
import ui.manager.models.BrowserTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import ui.manager.WebUiManager;


public class BaseTestsWeb {
    private static final Logger LOGGER = LogManager.getLogger(BaseTestsWeb.class);
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