package web.manager.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web.manager.PageObject;

public class MainPage extends PageObject {

    @FindBy(id = "login-form-username")
    private WebElement usernameField;

    @FindBy(id = "login-form-password")
    private WebElement passwordField;

    @FindBy(id = "login")
    private WebElement loginBtn;

    public MainPage(WebDriver driver, String xmUrl) {
        super(driver);
        navigateToUrl(xmUrl);
        PageFactory.initElements(driver, this);
    }

    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getLoginBtn() {
        return loginBtn;
    }
}