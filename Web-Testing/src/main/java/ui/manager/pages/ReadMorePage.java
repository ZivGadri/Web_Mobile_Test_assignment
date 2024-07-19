package ui.manager.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.manager.PageObject;

import java.util.List;

public class ReadMorePage extends PageObject {

    @FindBy(xpath = "//table[@class='table pull-right']/tbody/tr")
    private List<WebElement> rightSideTableRows;

    @FindBy(xpath = "//table[@class='table pull-left']/tbody/tr")
    private List<WebElement> leftSideTableRows;

    public ReadMorePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public List<WebElement> getRightSideTableRows() {
        return rightSideTableRows;
    }

    public List<WebElement> getLeftSideTableRows() {
        return leftSideTableRows;
    }
}
