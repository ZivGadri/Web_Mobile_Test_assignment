package ui.manager.pageHelpers;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import ui.manager.pages.StocksPage;

import java.util.HashMap;
import java.util.List;

public class StocksPageHelper extends StocksPage {
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
        clickButton(getNextPageBtn(), "Next", false);
        new StocksPageHelper(driver);
    }

    public ReadMorePageHelper clickReadMore(String symbolAndDescription) {
        Actions actions = new Actions(driver);
        List<WebElement> relevantRowColumns = getRelevantRowColumns(getRelevantRow(symbolAndDescription));
        WebElement readMoreColumn = relevantRowColumns.get(relevantRowColumns.size() - 1);
        if (screenSize.width < 1030) {
            try {
                actions.moveToElement(readMoreColumn).click().perform();
            } catch (Exception e) {
                driver.get(readMoreColumn.findElement(By.xpath("./a")).getAttribute("href"));
            }
        } else {
            clickButton(readMoreColumn, "Read More", false);
        }
        return new ReadMorePageHelper(driver);
    }

    public String getStocksPageTitle() {
        return driver.findElement(By.xpath("//h1")).getText();
    }
}
