package web.manager.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web.manager.PageObject;
import web.manager.pageHelpers.MainPageHelper;

public class MainPage extends PageObject {
    private static final Logger logger = LogManager.getLogger(MainPage.class);
    @FindBy(xpath = "//button[contains(@class,'acceptDefaultCookieFirstVisit')]")
    private WebElement acceptAllCookiesBtn;

    public MainPage(WebDriver driver, String xmUrl) {
        super(driver);
        navigateToUrl(xmUrl);
        PageFactory.initElements(driver, this);
    }

    public WebElement getAcceptAllCookiesBtn() {
        return acceptAllCookiesBtn;
    }

}