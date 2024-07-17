package web.manager.pageHelpers;

import org.openqa.selenium.WebDriver;
import web.manager.pages.MainPage;

public class MainPageHelper extends MainPage {

    public MainPageHelper(WebDriver driver, String xmUrl) {
        super(driver, xmUrl);
    }

    public void clickAcceptAllCookiesIfFoundOnPage() {
        if (isElementFoundInPage(getAcceptAllCookiesBtn())) {

        }
    }


}