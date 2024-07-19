package ui.manager.pageHelpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import ui.manager.pages.StocksPage;

import java.util.HashMap;
import java.util.List;

public class StocksPageHelper extends StocksPage {
    private static final Logger LOGGER = LogManager.getLogger(StocksPageHelper.class);
    public StocksPageHelper(WebDriver driver) {
        super(driver);
    }

    public void clickNorwayFilter() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", getNorwayFilterBtn());
        clickButton(getNorwayFilterBtn(), "Norway", false);
    }

    public HashMap<String, String> fetchRowData(String requestedRow) {
        HashMap<String, String> dataMap = new HashMap<>();
        List<WebElement> tableHeaders = getDataTable().findElements(By.xpath("./thead/tr/th"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true)", tableHeaders.get(0));
        List<WebElement> relevantRowColumns = getRelevantRowColumns(getRelevantRow(requestedRow));
        for (int i = 0; i < tableHeaders.size() - 1; i++) { // Skipping last columns
            String key = getElementTextUsingJavaScript(tableHeaders.get(i));
            String value = getElementTextUsingJavaScript(relevantRowColumns.get(i));
            dataMap.put(key, value);
        }
        return dataMap;
    }

    private int getNumberOfTablePages() {
        return getTablePagesNumbering().size();
    }

    private WebElement getRelevantRow(String searchedValue) {
        int numOfPages = getNumberOfTablePages();
        for (int i = 1; i <= numOfPages; i++) {
            for (WebElement element : getTableRows(getDataTable())) {
                if (element.getText().startsWith(searchedValue)) {
                    return element;
                }
            }
            if (i < numOfPages) {
                clickNextPage();
            }
        }
        throw new NoSuchElementException("Could not find the requested table row: " + searchedValue);
    }

    private List<WebElement> getRelevantRowColumns(WebElement relevantRow) {
        return relevantRow.findElements(By.xpath("./td"));
    }

    private void clickNextPage() {
        scrollIntoCenterView(getNextPageBtn());
        clickButton(getNextPageBtn(), "Next", false);
        new StocksPageHelper(driver);
    }

    /**
     * This Method clicks the 'Read More' button of the requested stock.
     * ** It checks for the element's click-ability and makes attempts to click it
     *    using webdriver command and javascript executor.
     *
     * @param symbolAndDescription  Requested stock
     *
     * @return A new instance of the ReadMorePageHelper to instantiate the elements
     * @throws ElementNotInteractableException
     */
    public ReadMorePageHelper clickReadMore(String symbolAndDescription) {
        List<WebElement> relevantRowColumns = getRelevantRowColumns(getRelevantRow(symbolAndDescription));
        WebElement readMoreColumn = relevantRowColumns.get(relevantRowColumns.size() - 1);
        try {
            clickButton(readMoreColumn, "Read More", false);
        } catch (NoSuchElementException nsee) {
            LOGGER.error("Element Read More button is not intractable");
            throw new ElementNotInteractableException("Element Read More button is not intractable");
        }
        return new ReadMorePageHelper(driver);
    }

    public String getStocksPageTitle() {
        return driver.findElement(By.xpath("//h1")).getText();
    }
}
