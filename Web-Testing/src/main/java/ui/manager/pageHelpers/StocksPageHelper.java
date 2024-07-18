package ui.manager.pageHelpers;

import org.openqa.selenium.*;
import ui.manager.pages.StocksPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StocksPageHelper extends StocksPage {
    public StocksPageHelper() {
        super();
    }

    public void clickNorwayFilter() {
        clickButton(getNorwayFilterBtn(), "Norway");
    }

    public HashMap<String, String> initRelevantRowData(String requestedRow) {
        HashMap<String, String> dataMap = new HashMap<>();
        List<WebElement> tableHeaders = getDataTable().findElements(By.xpath("./thead/tr/th"));
        List<WebElement> relevantRowColumns = getRelevantRowColumns(getRelevantRow(requestedRow));
        for (int i = 0; i < tableHeaders.size(); i++) { // Skipping last columns
            String key = tableHeaders.get(i).getText();
            String value = relevantRowColumns.get(i).getText();
            dataMap.put(key, value);
        }
        return dataMap;
    }


    private int getDataTableColumnIndexByAttribute(String attributeName) {
        int index = 0;
        List<WebElement> tableHeaders = getDataTable().findElements(By.xpath("./thead/tr/th"));
        for (WebElement element : tableHeaders) {
            if (element.getText().equalsIgnoreCase(attributeName)) {
                return index;
            }
            index++;
        }
        throw new NoSuchElementException("Could not locate the table header by the data provided");
    }

    private int getNumberOfTablePages() {
        return getTablePagesNumbering().size();
    }

    private List<WebElement> getTableRows() {
        try {
            return getDataTable().findElements(By.xpath("./tbody/tr"));
        } catch (NoSuchElementException nse) {
            // LOGGER
            throw new NoSuchElementException("Could not find the table rows");
        }
    }

    private WebElement getRelevantRow(String searchedValue) {
        int numOfPages = getNumberOfTablePages();
        for (int i = 1; i <= numOfPages; i++) {
            for (WebElement element : getTableRows()) {
                if (element.getText().equalsIgnoreCase(searchedValue)) {
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
        clickButton(getNextPageBtn(), "Next");
        new StocksPageHelper();
    }

}
