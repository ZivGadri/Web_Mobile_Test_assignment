package ui.manager.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.manager.PageObject;

public class MainPage extends PageObject {

    private static final Logger LOGGER = LogManager.getLogger(MainPage.class);

    @FindBy(xpath = "//button[contains(@class,'acceptDefaultCookieFirstVisit')]")
    private WebElement acceptAllCookiesBtn;

    @FindBy(className = "toggleLeftNav")
    private WebElement mainMenuHamburgerIcon;

    @FindBy(xpath = "//a[@aria-controls='tradingMenu']")
    private WebElement smallResTradingMenuBtn;

    @FindBy(className = "main_nav_trading")
    private WebElement medResTradingMenuBtn;

    @FindBy(linkText = "Stocks")
    private WebElement stocksBtn;

    @FindBy(linkText = "Open an Account")
    private WebElement openAnAccountBtn;

    public MainPage(WebDriver driver, String xmUrl) {
        super(driver);
        navigateToUrl(xmUrl);
        PageFactory.initElements(driver, this);
    }

    public WebElement getAcceptAllCookiesBtn() {
        return acceptAllCookiesBtn;
    }

    public WebElement getMainMenuHamburgerIcon() { return mainMenuHamburgerIcon; }

    public WebElement getSmallResTradingMenuBtn() {
        return smallResTradingMenuBtn;
    }

    public WebElement getStocksBtn() {
        return stocksBtn;
    }

    public WebElement getMedResTradingMenuBtn() {
        return medResTradingMenuBtn;
    }
    public WebElement getOpenAnAccountBtn() {
        return openAnAccountBtn;
    }
}