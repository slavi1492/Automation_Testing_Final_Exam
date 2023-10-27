package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class LikeDislikeTest extends BaseTest{
    @Test(testName = "likeDislikePost", dataProvider = "validUser")
    public void likeDislikePost(String username, String password) throws InterruptedException {

        System.out.println("Login with test user");
        loginWithTestUser(username, password);

        HomePage homePage = new HomePage(driver);
        homePage.invisibilityOfPopupMessageBox();

        System.out.println("Initial state of Like and Dislike buttons");
        boolean initialLikeState = homePage.checkStatusOflike(0);
        System.out.println(initialLikeState);
        boolean initialDislikeState = homePage.checkStatusOfDislike(0);
        System.out.println(initialDislikeState);

        System.out.println("Click Like on last post");
        homePage.clickLikeOnPost(0);

        System.out.println("Verify that like is clicked");
        Assert.assertEquals(!homePage.checkStatusOflike(0), initialLikeState,
                "State of button is not changed");

        System.out.println("Click Dislike on last post");
        homePage.clickDislikeOnPost(0);

        System.out.println("Verify that dislike is clicked");
        Assert.assertEquals(!homePage.checkStatusOfDislike(0), initialDislikeState,
                "State of button is not changed");
    }
}
