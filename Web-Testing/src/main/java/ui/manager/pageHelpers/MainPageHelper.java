package ui.manager.pageHelpers;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import ui.manager.pages.MainPage;

public class MainPageHelper extends MainPage {

    public MainPageHelper(WebDriver driver, String xmUrl, Dimension screenSize) {
        super(driver, xmUrl, screenSize);
    }

    public void clickAcceptAllCookiesIfFoundOnPage() {
        if (isElementFoundInPage(getAcceptAllCookiesBtn())) {
            clickButton(getAcceptAllCookiesBtn(), "Accept All");
        }
    }

    public StocksPageHelper navigateToStocksPage() {
        if (screenSize.width < 1000) {
            clickButtonIfPresent(getMainMenuHamburgerIcon(), "Menu", true);
        }
        clickButton(getTradingMenuBtn(), "Trading");
        clickButton(getStocksBtn(), "Stocks");
        return new StocksPageHelper();
    }


}