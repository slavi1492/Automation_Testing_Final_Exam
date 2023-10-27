package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.List;

public class NewPostPage extends BasePage {

    @FindBy(css = "input[type='file']")
    WebElement uploadFileInput;

    @FindBy(className = "image-preview")
    WebElement uploadImagePreview;

    @FindBy(css = ".input-group *[type=\"text\"]")
    WebElement uploadFileName;

    @FindBy(css = ".input-group-addon input[type=\"text\"")
    WebElement postCaptionInput;

    @FindBy(className = "post-status-label")
    List<WebElement> postType;

    @FindBy(css = ".post-status-label[for=\"customSwitch2\"]")
    WebElement postTypeSwitchButton;

    @FindBy(id = "create-post")
    WebElement submitPostButton;


    public NewPostPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void fileUpload(File uploadFile) {
        uploadFileInput.sendKeys(uploadFile.getAbsolutePath());
    }

    public String getUploadedFileName() {
        wait.until(ExpectedConditions.visibilityOf(uploadFileName));
        return uploadFileName.getAttribute("placeholder");
    }

    public void verifyImagePreview() {
        wait.until(ExpectedConditions.visibilityOf(uploadImagePreview));
    }


    public void populateCaption(String captionText) {
        populatedInputField(postCaptionInput, captionText);
    }

    public String postTypeStatus() {
        if (postType.get(0).getAttribute("class").contains("active")) {
            return postType.get(0).getText();
        } else return postType.get(1).getText();
    }

    public void changePostType() {
        clickElement(postTypeSwitchButton);
    }

    public void clickSubmitButton() {
        clickElement(submitPostButton);
    }


}
