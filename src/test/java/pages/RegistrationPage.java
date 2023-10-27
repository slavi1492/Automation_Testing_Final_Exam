package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class RegistrationPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameInputField;

    @FindBy(css = ".form-control[type=\"email\"]")
    private WebElement emailInputField;

    @FindBy(id = "defaultRegisterFormPassword")
    private WebElement passwordInputField;

    @FindBy(id = "defaultRegisterPhonePassword")
    private WebElement confirmPasswordInputField;

    @FindBy(className = "ng-invalid")
    private List<WebElement> inputFieldsValidity;

    @FindBy(id="sign-in-button")
    private WebElement signInButton;


    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyRegistrationPageUrl() {
        wait.until(ExpectedConditions.urlToBe(BASE_URL.concat(REGISTER_URL_ADD)));
    }

    public void populateUsernameField(String username) {
        populatedInputField(usernameInputField, username);
    }

    public void checkValidityOfUsernameInput(){
        checkClassOfElement(usernameInputField).contains("is-valid");
    }


    public void populateEmailInputField(String email) {
        populatedInputField(emailInputField, email);
    }

    public void checkValidityOfEmailInput(){
        checkClassOfElement(emailInputField).contains("is-valid");
    }

    public void populatePasswordField(String password) {
        populatedInputField(passwordInputField, password);
    }

    public void checkValidityOfPasswordInput(){
        checkClassOfElement(passwordInputField).contains("is-valid");
    }

    public void populateConfirmPasswordField(String password) {
        populatedInputField(confirmPasswordInputField, password);
    }

    public void checkValidityOfConfirmPasswordInput(){
        checkClassOfElement(confirmPasswordInputField).contains("is-valid");
    }

    public void clickSignInButton(){
        clickElement(signInButton);
    }

    public void checkInputValidity() {
        inputFieldsValidity.isEmpty();
    }

}
