package ui.manager.pageHelpers;

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
        if (screenSize.width < 1000) {
            clickButton(getSmallResTradingMenuBtn(), "Trading",true);
        } else {
            clickButton(getMedResTradingMenuBtn(), "Trading", true);
        }
        clickButton(getStocksBtn(), "Stocks", true);
        return new StocksPageHelper(driver);
    }

    public boolean isOpenAnAccountBtnClickable() {
        try {
            WebElement e = wait.until(ExpectedConditions.elementToBeClickable(getOpenAnAccountBtn()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}