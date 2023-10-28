package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(id = "toast-container")
    private WebElement popupMessages;
    @FindBy(tagName = "app-post-detail")
    private List<WebElement> allPosts;
    @FindBy(className = "post-info")
    private List<WebElement> postsInfoBar;


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        driver.get(BASE_URL);
    }

    public void verifyHomePageUrl() {
        wait.until(ExpectedConditions.urlToBe(BASE_URL.concat(HOME_URL_ADD)));
    }

    public String popupMessageGetText() {
        checkVisibilityOfWebElement(popupMessages);
        return popupMessages.getText().trim();
    }

    public void invisibilityOfPopupMessageBox() {
        checkInvisibilityOfWebElement(popupMessages);
    }

    public boolean checkStatusOfDislike(int i) {
        WebElement dislikesButton = postsInfoBar.get(i).findElement(By.className("fa-thumbs-down"));
        String tempString = dislikesButton.getAttribute("class").trim();
        System.out.println(tempString);
        return tempString.contains("liked");
    }

    public boolean checkStatusOflike(int i) {
        WebElement likesButton = postsInfoBar.get(i).findElement(By.className("fa-heart"));
        String tempString = likesButton.getAttribute("class").trim();
        System.out.println(tempString);
        return tempString.contains("liked");
    }

    public void clickLikeOnPost(int i) throws InterruptedException {
        WebElement likesButton = postsInfoBar.get(i).findElement(By.className("fa-heart"));
        clickElement(likesButton);
        Thread.sleep(3000);
        System.out.println(likesButton.getAttribute("class"));
    }

    public void clickDislikeOnPost(int i) throws InterruptedException {
        WebElement dislikesButton = postsInfoBar.get(i).findElement(By.className("fa-thumbs-down"));
        clickElement(dislikesButton);
        Thread.sleep(3000);
        invisibilityOfPopupMessageBox();
    }

    public void openPost(int i){
        clickElement(allPosts.get(i));
    }
}
