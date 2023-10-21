package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderPage extends BasePage {
    @FindBy(id = "homeIcon")
    WebElement homeIcon;

    @FindBy(id = "nav-link-home")
    WebElement homeLink;

    @FindBy(id = "nav-link-login")
    WebElement loginLink;

    @FindBy(id = "nav-link-profile")
    WebElement profileLink;

    @FindBy(id = "nav-link-new-post")
    WebElement newpostLink;

    @FindBy(className = "fa-sign-out-alt")
    WebElement signoutButton;

    public HeaderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void visibilityOfProfileLink() {
        checkVisibilityOfWebElement(profileLink);
    }

    public void visibilityOfNewpostLink() {
        checkVisibilityOfWebElement(newpostLink);
    }

    public void visibilityOfSignOutButton() {
        checkVisibilityOfWebElement(signoutButton);
    }
    public void invisibilityOfProfileLink() {
        checkInvisibilityOfWebElement(profileLink);
    }

    public void invisibilityOfNewpostLink() {
        checkInvisibilityOfWebElement(profileLink);
    }

    public void invisibilityOfSignOutButton() {
        checkInvisibilityOfWebElement(signoutButton);
    }

    public void goToLogin() {
        clickElement(loginLink);
    }

    public void goToProfile() {
        clickElement(profileLink);
    }

    public void goToNewpost() {
        clickElement(newpostLink);
    }

    public void goToHome() {
        clickElement(homeLink);
    }

    public void signOut() {
        clickElement(signoutButton);
    }
}
