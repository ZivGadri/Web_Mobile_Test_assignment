package web.manager.pageHelpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import web.manager.pages.MainPage;

public class MainPageHelper extends MainPage {
    private static final Logger logger = LogManager.getLogger(MainPageHelper.class);
    public MainPageHelper(WebDriver driver, String xmUrl) {
        super(driver, xmUrl);
    }
}