package pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    protected final String BASE_URL = "http://training.skillo-bg.com:4200";
    protected final String HOME_URL_ADD = "/posts/all";
    protected final String LOGIN_URL_ADD = "/users/login";
    protected final String REGISTER_URL_ADD = "/users/register";
    protected final String PROFILE_URL_ADD = "/users/";
    protected final String POST_URL_ADD = "/posts/create";


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        final String BASE_URL = "http://training.skillo-bg.com:4200/";
    }

    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void populatedInputField(WebElement element, String inputString) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(inputString);
    }

    public void checkVisibilityOfWebElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void checkInvisibilityOfWebElement(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public String checkClassOfElement(@NotNull WebElement element) {
        return element.getAttribute("class");
    }


}
