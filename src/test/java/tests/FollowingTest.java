package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HeaderPage;
import pages.HomePage;
import pages.PostModal;
import pages.ProfilePage;

public class FollowingTest extends BaseTest {

    // Very unstable
    @Test(testName = "followTest", dataProvider = "validUser")
    public void followUser(String username, String password)  {

        System.out.println("Login with test user");
        loginWithTestUser(username, password);

        ProfilePage profilePage = new ProfilePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        HomePage homePage = new HomePage(driver);

        System.out.println("Get the initial number of following users");
        headerPage.goToProfile();
        int initialNumberOfFollowing = profilePage.totalNumberOfUserFollowings();

        System.out.println("Follow user");
        headerPage.goToHome();
        for (int i=0; i<100; ++i) {
            driver.findElement(By.cssSelector("html")).sendKeys(Keys.DOWN);
        }
        homePage.openPost(0);
        PostModal postModal = new PostModal(driver);
        String stateOfFollowButton = postModal.stateOfFollowButton();
        int newNumberOfFollowing = initialNumberOfFollowing;
        if (stateOfFollowButton.equals("Follow")){
            newNumberOfFollowing += 1;
        }else { newNumberOfFollowing -= 1;}
        postModal.clickFollowUnloowButton();
        homePage.invisibilityOfPopupMessageBox();

        System.out.println("Verify followed user on profile page");
        Assert.assertEquals(newNumberOfFollowing, initialNumberOfFollowing + 1,
                "Number of following users is not increased by 1");
    }

    @Test(testName = "unfollowUserTest", dataProvider = "validUser")
    public void followUserTest(String username, String password) {
        System.out.println("Login with test user");
        loginWithTestUser(username, password);

        ProfilePage profilePage = new ProfilePage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        HomePage homePage = new HomePage(driver);

        System.out.println("Get the initial number of following users");
        headerPage.goToProfile();
        int initialNumberOfFollowing = profilePage.totalNumberOfUserFollowings();

        System.out.println("Go to profile page and open list of following");
        profilePage.clickFollowingLink();

        System.out.println("Unfollow User");
        profilePage.unfollowUser();
        homePage.invisibilityOfPopupMessageBox();

        System.out.println("Verify that following users decreased with 1");
        int newNumberOfFollowing = profilePage.totalNumberOfUserFollowings();
        Assert.assertEquals(newNumberOfFollowing, initialNumberOfFollowing-1,
                "Number of following users doesn't decreased with 1");
    }

}