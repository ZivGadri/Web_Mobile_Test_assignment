package ui.manager.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

    private static final Logger LOGGER = LogManager.getLogger(WebDriverFactory.class);
    private final String BROWSER_TYPE;
    private WebDriver webDriver;

    public WebDriverFactory(String browserType) {
        this.BROWSER_TYPE = browserType;
    }

    public WebDriver initWebDriver(Dimension dimension) {
        try {
            webDriver = createLocalDriver(dimension);
            return webDriver;
        } catch (Exception e) {
            LOGGER.info("Web driver initialization process failed.");
            throw new WebDriverException();
        }
    }

    /**
     * Create a local Selenium instance.
     */
    private WebDriver createLocalDriver(Dimension dimension) {
        WebDriver webDriver;
        if ("chrome".equalsIgnoreCase(BROWSER_TYPE)) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver(new ChromeOptions().merge(getBrowserCapabilities(BROWSER_TYPE)));
        } else if ("firefox".equalsIgnoreCase(BROWSER_TYPE)) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver(new FirefoxOptions().merge(getBrowserCapabilities(BROWSER_TYPE)));
        } else if ("edge".equalsIgnoreCase(BROWSER_TYPE)) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver(new EdgeOptions().merge(getBrowserCapabilities(BROWSER_TYPE)));
        } else {
            throw new RuntimeException("Invalid browser: " + BROWSER_TYPE);
        }

        webDriver.manage().window().setSize(dimension);

        return webDriver;
    }

    private static MutableCapabilities getBrowserCapabilities(String browser) {
        Map<String, Object> prefs = new HashMap();
        switch (browser.toLowerCase()) {
            case "chrome":
                prefs.put("profile.default_content_setting_values.notifications", 2);
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);
                options.setExperimentalOption("w3c", true);
                return options;
            case "firefox":
                FirefoxOptions ffo = new FirefoxOptions();
                ffo.addPreference("dom.webnotifications.enabled", false);
                return ffo;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setCapability("ms:inPrivate", true);
                return edgeOptions;
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }
    }
}