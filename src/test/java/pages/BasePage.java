package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    private final String BASE_URL = "http://training.skillo-bg.com:4200/";
    private final String HOME_URL_ADD = "/posts/all";
    private final String LOGIN_URL_ADD = "/users/login";
    private final String REGISTER_URL_ADD = "/users/register";
    private final String PROFILE_URL_ADD = "/users/";
    private final String POST_URL_ADD = "/posts/create";


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void populatedInputField(WebElement element, String inputString) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(inputString);
    }

}
