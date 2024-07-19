package ui.manager.pageHelpers;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.manager.pages.MainPage;

public class MainPageHelper extends MainPage {

    public MainPageHelper(WebDriver driver, String xmUrl) {
        super(driver, xmUrl);
    }

    public void clickAcceptAllCookiesIfFoundOnPage() {
        if (isElementFoundInPage(getAcceptAllCookiesBtn())) {
            clickButton(getAcceptAllCookiesBtn(), "Accept All",true);
        }
    }

    public StocksPageHelper navigateToStocksPage() {
        clickButtonIfPresent(getMainMenuHamburgerIcon(), "Menu", true,false);
        clickButton(getTradingMenuBtn(), "Trading", false);
        clickButton(getStocksBtn(), "Stocks", true);
        return new StocksPageHelper(driver);
    }

    public boolean isOpenAnAccountBtnClickable() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(getOpenAnAccountBtn()));
            return true;
        } catch (TimeoutException toe) {
            return false;
        }
    }
}