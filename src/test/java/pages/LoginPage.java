package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(css = ".form-container")
    WebElement signInForm;

    @FindBy(id = "defaultLoginFormUsername")
    WebElement usernameInputField;

    @FindBy(id = "defaultLoginFormPassword")
    WebElement passwordInputField;

    @FindBy(id = "sign-in-button")
    WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyLoginPageUrl() {
        wait.until(ExpectedConditions.urlToBe(BASE_URL.concat(LOGIN_URL_ADD)));
    }

    public void verifyLoginFormVisibility(){
        wait.until(ExpectedConditions.visibilityOf(signInForm));
    }

    public void populateUsernameField(String username) {
        populatedInputField(usernameInputField, username);
    }

    public void populatePasswordField(String password) {
        populatedInputField(passwordInputField,password);
    }

    public void clickSignInButton(){
        clickElement(signInButton);
    }


}
