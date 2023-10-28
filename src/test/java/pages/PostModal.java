package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PostModal extends BasePage {
    @FindBy(css = ".post-modal button")
    private WebElement followUnfollowButton;

    public PostModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String stateOfFollowButton(){
        return followUnfollowButton.getText();
    }

    public void clickFollowUnloowButton(){
        clickElement(followUnfollowButton);
    }

}
