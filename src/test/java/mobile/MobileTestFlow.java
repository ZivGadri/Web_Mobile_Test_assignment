package mobile;

import mobile.manager.Constants;

public class MobileTestFlow extends MobileBaseTests {

    protected void testYummlyApp() {
        mobileUiManager.clickAskMeLaterBtn();
        mobileUiManager.clickNoThanksBtn();
        mobileUiManager.clickInviteFriendsNoThanksBtn();
        mobileUiManager.clickSearchField();
        mobileUiManager.searchForItem(Constants.SEARCH_VALUE);
        mobileUiManager.selectFirstSearchResult();
        mobileUiManager.navigateToIngredients();
        mobileUiManager.selectLastIngredientPlusButton();
        mobileUiManager.clickOnBuyButton();
        mobileUiManager.closePopup();
    }
}
