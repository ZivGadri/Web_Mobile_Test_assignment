package web.manager.pageHelpers;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import web.manager.pages.MainPage;

public class MainPageHelper extends MainPage {

    public MainPageHelper(WebDriver driver, String xmUrl, Dimension screenSize) {
        super(driver, xmUrl, screenSize);
    }

    public void clickAcceptAllCookiesIfFoundOnPage() {
        if (isElementFoundInPage(getAcceptAllCookiesBtn())) {

        }
    }

    public void navigateToTradingPage() {
        if (screenSize.width < 1000) {

        }
    }


}