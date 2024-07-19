import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class TestFlowWeb extends BaseTestsWeb {
    Set<Map.Entry<String, String>> differences;

    protected void orkalaAsaData() {
        webUiManager.navigateToMainPage();
        softAssert.assertTrue(webUiManager.isOpenAnAccountBtnClickable(), "Open an account button is not available for clicking");
        webUiManager.navigateToStocksPage();
        softAssert.assertEquals(webUiManager.getStocksPageTitle(), "STOCKS", "Stocks page title is not as expected");
        HashMap<String, String> tableDataMap = webUiManager.getRelevantTableData("Orkla ASA (ORK.OL)");
        softAssert.assertEquals(tableDataMap.size(), 8, "Table data map size is not as expected");
        HashMap<String, String> readMoreDataMap = webUiManager.getReadMoreTablesData();
        softAssert.assertTrue(readMoreDataMap.get("Description").equals("Orkla ASA"));
        boolean dataSetsAreEqual = compareTwoDataMaps(tableDataMap, readMoreDataMap);
        softAssert.assertTrue(dataSetsAreEqual, "\nThere are differences between the two data sets. More info: " + getDifferences(differences));

        softAssert.assertAll();
    }

    private boolean compareTwoDataMaps(Map<String, String> map1, Map<String, String> map2) {
        differences = map1.entrySet().stream()
                .filter(e -> !Objects.equals(e.getValue(), map2.get(e.getKey())))
                .collect(Collectors.toSet());

        differences.addAll(map2.entrySet().stream()
                .filter(e -> !Objects.equals(e.getValue(), map1.get(e.getKey())))
                .collect(Collectors.toSet()));

        return differences.isEmpty();
    }

    private String getDifferences(Set<Map.Entry<String, String>> entrySet) {
        StringBuilder sb = new StringBuilder("\nThe key value sets were not found in both data sets:\n");
        for (Map.Entry<String, String> entry : entrySet) {
            sb.append("Key: ").append(entry.getKey()).append(", Value: ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}