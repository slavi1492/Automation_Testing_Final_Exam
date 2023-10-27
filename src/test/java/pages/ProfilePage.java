package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProfilePage extends BasePage {

    @FindBy(css = "app-profile-section  * .profile-user-settings h2")
    private WebElement userProfileName;

    @FindBy(xpath = "//*[contains(text(),\"posts\")]/*")
    private WebElement totalNumberOfPosts;

    @FindBy(css = "#followers>*")
    private WebElement numberOfFollowers;
    @FindBy(css = "#following>*")
    private WebElement numberOfFollowing;
    @FindBy(id = "following")
    private WebElement followingLink;
    @FindBy(css = ".post-filter-buttons>*")
    private List<WebElement> postsTypeButtonList;
    @FindBy(css = "app-post")
    private List<WebElement> listOfPosts;
    @FindBy(css = "app-profile-posts-section")
    private WebElement postsSection;
    @FindBy(css = "app-small-user-profile button")
    private List<WebElement> listOfFollowingUsers;



    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyProfilePageUrl() {
        wait.until(ExpectedConditions.urlContains(BASE_URL.concat(PROFILE_URL_ADD)));
    }

    public String displayedUserName() {
        wait.until(ExpectedConditions.visibilityOf(userProfileName));
        return userProfileName.getText();
    }

    public int totalNumberOfUserPosts() {
        return Integer.parseInt(totalNumberOfPosts.getText().trim());
    }

    public int totalNumberOfUserFollowings() {
        return Integer.parseInt(numberOfFollowing.getText().trim());
    }

    public void selectTypeOfPostsList(String typeOfPosts) {
        for (WebElement element : postsTypeButtonList) {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            if (element.getText().trim().equals(typeOfPosts)) {
                element.click();
            }
        }
    }

    public int getNumberOfPosts(String typeOfPosts) {
        selectTypeOfPostsList(typeOfPosts);
        if (!listOfPosts.isEmpty()) {
        return listOfPosts.size();
        } else return 0;
    }

    public void visibilityOfPosts() {
        wait.until(ExpectedConditions.visibilityOf(postsSection));
    }

    public void clickFollowingLink(){
        clickElement(followingLink);
    }

    public void unfollowUser(){
        clickElement(listOfFollowingUsers.get(0));
    }


}
