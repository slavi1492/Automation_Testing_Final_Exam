package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

    @FindBy(css = "app-profile-section  * .profile-user-settings h2")
    private WebElement userProfileName;

    @FindBy(xpath = "//*[contains(text(),\"posts\")]/*")
    private WebElement totalNumberOfPosts;

    @FindBy(css = "#followers>*")
    private WebElement numberOfFollowers;

    @FindBy(css = "#following>*")
    private WebElement numberOfFollowing;

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void verifyProfilePageUrl(){
        wait.until(ExpectedConditions.urlContains(BASE_URL.concat(PROFILE_URL_ADD)));
    }

    public String displayedUserName() {
        wait.until(ExpectedConditions.visibilityOf(userProfileName));
        return userProfileName.getText();
    }

    public int totalNumberOfUserPosts() {
        return Integer.parseInt(totalNumberOfPosts.getText().trim());
    }

    public int totalNumberOfUserFollowers() {
        return Integer.parseInt(numberOfFollowers.getText().trim());
    }

    public int totalNumberOfUserFollowings() {
        return Integer.parseInt(numberOfFollowing.getText().trim());
    }
}
