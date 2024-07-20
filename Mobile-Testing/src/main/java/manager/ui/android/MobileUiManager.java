package manager.ui.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.openqa.selenium.WebElement;

import static manager.drivers.AndroidDriverManager.createAndroidDriver;

public class MobileUiManager {
    private AndroidDriver androidDriver;
    private MobileUiHelper mobileUiHelper;

    public MobileUiManager() {
        this.androidDriver = createAndroidDriver();
        mobileUiHelper = new MobileUiHelper(androidDriver);
    }

    public void clickAskMeLaterBtn() {
        mobileUiHelper.clickButtonIfExists(ElementsLocators.askMeLaterBtn,
                "Ask Me Later", false);
    }

    public void clickNoThanksBtn() {
        mobileUiHelper.clickButtonIfExists(ElementsLocators.noThanksBtn,
                "No Thanks", false);
    }

    public void clickInviteFriendsNoThanksBtn() {
        mobileUiHelper.clickButtonIfExists(ElementsLocators.inviteFriendsNoThanksBtn,
                "No Thanks", false);
    }

    public void clickSearchField() {
        mobileUiHelper.clickButtonIfExists(ElementsLocators.searchField,
                "Search...", true);
    }

    public void searchForItem(String text) {
        mobileUiHelper.insertKeys(ElementsLocators.searchFieldInput,
                text);
        mobileUiHelper.clickAndroidKey(AndroidKey.ENTER);
    }

    public void selectFirstSearchResult() {
        mobileUiHelper.clickButtonIfExists(ElementsLocators.firstSearchResult,
                "First search result", true);
    }

    public void navigateToIngredients() {
        mobileUiHelper.clickButtonIfExists(ElementsLocators.ingredientsLabel,
                "Ingredients", true);
    }

    public void selectLastIngredientPlusButton() {
        WebElement lastIngredientPlusButton = mobileUiHelper.getLastIngredientPlusButton(ElementsLocators.ingredientsPlusButtonsList);
        mobileUiHelper.clickButtonIfExists(lastIngredientPlusButton,
                "Ingredients", true);
    }

    public void clickOnBuyButton() {
        mobileUiHelper.clickButtonIfExists(ElementsLocators.buyBtn,
                "Buy", true);
    }

    public void closePopup() {
        mobileUiHelper.clickAndroidKey(AndroidKey.BACK);
    }
}