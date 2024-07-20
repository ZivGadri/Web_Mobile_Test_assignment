import manager.Constants;

public class TestFlowMobile extends BaseTestsMobile {

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
