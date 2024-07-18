package web.manager.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web.manager.PageObject;

public class MainPage extends PageObject {

    private static final Logger LOGGER = LogManager.getLogger(MainPage.class);

    @FindBy(xpath = "//button[contains(@class,'acceptDefaultCookieFirstVisit')]")
    private WebElement acceptAllCookiesBtn;

    @FindBy(className = "fa fa-bars")
    private WebElement mainMenuHamburgerIcon;

    public MainPage(WebDriver driver, String xmUrl, Dimension screenSize) {
        super(driver, screenSize);
        navigateToUrl(xmUrl);
        PageFactory.initElements(driver, this);
    }

    public WebElement getAcceptAllCookiesBtn() {
        return acceptAllCookiesBtn;
    }

    public WebElement getMainMenuHamburgerIcon() { return mainMenuHamburgerIcon; }


}