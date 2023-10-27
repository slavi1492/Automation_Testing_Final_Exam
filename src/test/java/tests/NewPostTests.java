package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.io.File;

public class NewPostTests extends BaseTest {
    @Test(testName = "createNewPost", dataProvider = "validUser")
    public void verifyCreatedPosts(String username, String password) throws InterruptedException {

        System.out.println("Login with test user");
        loginWithTestUser(username, password);

        System.out.println("Get number of user's posts");
        ProfilePage profilePage = new ProfilePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToProfile();
        int initialNumberOfPublicPosts = profilePage.getNumberOfPosts("Public");
        int initialNumberOfPrivatePosts = profilePage.getNumberOfPosts("Private");

        System.out.println("Create Public post");
        createNewPost("Public");

        System.out.println("Verify that number of public posts is increased");
        Thread.sleep(5000);
        int newNumberOfPublicPosts = profilePage.getNumberOfPosts("Public");
        Assert.assertEquals(newNumberOfPublicPosts, initialNumberOfPublicPosts+1,
                "Number of posts differs expected number");

        System.out.println("Create Private post");
        createNewPost("Private");

        System.out.println("Verify that number of private posts is increased");
        Thread.sleep(5000);
        int newNumberOfPrivatePosts = profilePage.getNumberOfPosts("Private");
        Assert.assertEquals(newNumberOfPrivatePosts, initialNumberOfPrivatePosts+1,
                "Number of posts differs expected number");

        System.out.println("Verify that number of all posts is sum of public and private posts");
        int numberOfAllPosts = profilePage.getNumberOfPosts("All");
        Assert.assertEquals(numberOfAllPosts, newNumberOfPublicPosts+newNumberOfPrivatePosts,
                "Number of all posts differs from sum of public and private posts");


    }

    public void createNewPost(String typeOfPosts) {
        System.out.println("Open 'NewPost' link'");
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToNewpost();

        System.out.println("Set upload file");
        File uploadFile = new File("D:\\Work\\InteliJ_Projects\\Automation_Testing_Final_Exam\\src\\test\\resources\\uploads\\post_image.jpg");
        NewPostPage newPostPage = new NewPostPage(driver);
        newPostPage.fileUpload(uploadFile);

        System.out.println("Compare uploaded file name with actual file name");
        Assert.assertEquals(newPostPage.getUploadedFileName(), uploadFile.getName(),
                "Uploaded file name do not match the name of test image");

        System.out.println("Verify image preview existence");
        newPostPage.verifyImagePreview();

        System.out.println("Populate 'Caption' field");
        newPostPage.populateCaption("This is test message");

        System.out.println("Make a 'Public' post");
        if (newPostPage.postTypeStatus().equals(typeOfPosts)) {
            newPostPage.clickSubmitButton();
        } else {
            newPostPage.changePostType();
            newPostPage.clickSubmitButton();
        }

        System.out.println("Wait for profile page");
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.verifyProfilePageUrl();
        profilePage.visibilityOfPosts();
    }

}

