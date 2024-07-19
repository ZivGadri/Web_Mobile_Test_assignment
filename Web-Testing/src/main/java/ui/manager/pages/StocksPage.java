package ui.manager.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.manager.PageObject;

import java.util.List;

public class StocksPage extends PageObject {

    @FindBy(xpath = "//button[@data-value='Norway']")
    private WebElement norwayFilterBtn;

    @FindBy(xpath = "//table[starts-with(@class,'dataTB link dataTable') and @id='DataTables_Table_0']")
    private WebElement dataTable;

    @FindBy(xpath = "//div[@id='DataTables_Table_0_paginate']/span/a")
    private List<WebElement> tablePagesNumbering;

    @FindBy(id = "DataTables_Table_0_next")
    private WebElement nextPageBtn;

    public StocksPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getNorwayFilterBtn() {
        return norwayFilterBtn;
    }

    public WebElement getDataTable() {
        return dataTable;
    }

    public List<WebElement> getTablePagesNumbering() {
        return tablePagesNumbering;
    }

    public WebElement getNextPageBtn() {
        return nextPageBtn;
    }

}
