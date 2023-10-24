package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(css = ".form-container")
    private WebElement signInForm;

    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameInputField;

    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordInputField;

    @FindBy(id = "sign-in-button")
    private WebElement signInButton;

    @FindBy(xpath = "//*[contains(text(),\"Register\")]")
    private WebElement registrationLink;

    @FindBy(css = "form *[type=\"checkbox\"]")
    private WebElement loginRememberCheckBox;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyLoginPageUrl() {
        wait.until(ExpectedConditions.urlToBe(BASE_URL.concat(LOGIN_URL_ADD)));
    }

    public void verifyLoginFormVisibility() {
        wait.until(ExpectedConditions.visibilityOf(signInForm));
    }

    public void populateUsernameField(String username) {
        populatedInputField(usernameInputField, username);
    }

    public void populatePasswordField(String password) {
        populatedInputField(passwordInputField, password);
    }

    public void clickSignInButton() {
        clickElement(signInButton);
    }

    public void clickRegistrationLink() {
        clickElement(registrationLink);
    }

    public void loginWithTestUser(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(signInForm));
        populatedInputField(usernameInputField, username);
        populatedInputField(passwordInputField, password);
        clickElement(signInButton);
    }

    public void clickLoginRememberCheckBox() {
        wait.until(ExpectedConditions.elementToBeClickable(loginRememberCheckBox));
        loginRememberCheckBox.click();

    }

    public boolean statusOfLoginRememberCheckBox() {
        return loginRememberCheckBox.isEnabled();

    }


}
