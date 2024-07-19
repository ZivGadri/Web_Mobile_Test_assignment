package ui.manager.pageHelpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui.manager.pages.ReadMorePage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadMorePageHelper extends ReadMorePage {
    private static final Logger LOGGER = LogManager.getLogger(ReadMorePageHelper.class);
    public ReadMorePageHelper(WebDriver driver) {
        super(driver);
    }

    public HashMap<String, String> getRowsElementsFromBothTables() {
        By titileExtractionBy = By.xpath("./td[1]");
        By valueExtractionBy = By.xpath("./td[2]");
        HashMap<String, String> dataMap = new HashMap<>();
        List<WebElement> bothTablesRows = new ArrayList<>();
        scrollIntoTopView(getLeftSideTableRows().get(0));
        bothTablesRows.addAll(getLeftSideTableRows());
        bothTablesRows.addAll(getRightSideTableRows());

        for (int i = 0; i < bothTablesRows.size(); i++) {
                String key = "";
                String value = "";
                try {
                    WebElement keyElement = findElementByParentElement(bothTablesRows.get(i), titileExtractionBy);
                    WebElement valueElement = findElementByParentElement(bothTablesRows.get(i), valueExtractionBy);
                    key = getElementTextUsingJavaScript(keyElement).trim();
                    value = getElementTextUsingJavaScript(valueElement).trim();
                } catch (NoSuchElementException nse) {
                    LOGGER.info("Could not extract data elements from item number {} in the 'bothTableRows list", i + 1);
                }
                if (!key.isEmpty()) {
                    try {
                        dataMap.put(key, value);
                    } catch (Exception e) {
                        LOGGER.info("The key '{}' is already found in map", key);
                    }
                }
        }
        return dataMap;
    }
}