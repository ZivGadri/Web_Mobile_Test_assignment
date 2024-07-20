package manager.ui.android;

import org.openqa.selenium.By;

public class ElementsLocators {
    public static final By askMeLaterBtn = new By.ById("com.yummly.android:id/skip_view"); // Initial popup
    public static final By noThanksBtn = new By.ByXPath("//android.widget.TextView[@text=\"No Thanks\"]"); // "No Thanks" button
    public static final By inviteFriendsNoThanksBtn = new By.ById("com.yummly.android:id/no_thanks_button"); // Invite friends "No Thanks" button
    public static final By searchField = new By.ByXPath("//android.widget.TextView[@resource-id=\"search_field\"]"); // Search field
    public static final By searchFieldInput = new By.ById("com.yummly.android:id/search_src_text"); // Search input field
    public static final By firstSearchResult = new By.ById("com.yummly.android:id/clickFeedbackView"); // First search result
    public static final By ingredientsLabel = new By.ByXPath("//android.widget.TextView[@resource-id=\"com.yummly.android:id/text1\" and @text=\"Ingredients\"]"); // Ingredients label
    public static final By ingredientsPlusButtonsList = new By.ById("com.yummly.android:id/related_recipe_favorite"); // Ingredients list
    //public static final By lastIngredientPlusBtn = new By.ById("com.yummly.android:id/related_recipe_favorite"); // Last ingredient plus button - to be found using parent element
    public static final By buyBtn = new By.ByXPath("//android.widget.TextView[@text=\"Buy\"]"); // Buy button
}
