package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{

    @FindBy(id = "toast-container")
    private WebElement popupMessages;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage(){
        driver.get(BASE_URL);
    }

    public void verifyHomePageUrl() {
        wait.until(ExpectedConditions.urlToBe(BASE_URL.concat(HOME_URL_ADD)));
    }

    public String popupMessageGetText(){
        checkVisibilityOfWebElement(popupMessages);
        return popupMessages.getText().trim();
    }

    public void invisibilityOfPopupMessageBox(){
        checkInvisibilityOfWebElement(popupMessages);
    }

}
