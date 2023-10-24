package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HeaderPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;


public class LoginTests extends BaseTest {

//    @DataProvider(name = "validUser")
//    public Object[][] getValidUser() {
//        return new Object[][]{
//                {"auto_user", "auto_pass"}
//        };
//    }

    @DataProvider(name = "invalidUser")
    public Object[][] getInvalidUser() {
        return new Object[][]{
                {"wrong_user", "auto_pass", "User not found"},
                {"auto_user", "wrong_pass", "Ivalid password"},
                {"", "auto_pass", "UsernameOrEmail cannot be empty"},
                {"auto_user", "", "Password cannot be empty"}
        };
    }


// Positive scenario test

    @Test(testName = "LoginTest", groups = "PositiveTests", dataProvider = "validUser")
    public void positiveLoginTest(String username, String password) {

        System.out.println("Open home page");
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();

        System.out.println("Click 'Login' button");
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToLogin();

        System.out.println("Verify that you reach login page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginPageUrl();

        System.out.println("Find 'Sign In' form");
        loginPage.verifyLoginFormVisibility();

        System.out.println("Populate 'Username' input");
        loginPage.populateUsernameField(username);

        System.out.println("Populate 'Password' input");
        loginPage.populatePasswordField(password);

        System.out.println("Click 'Sign In' button");
        loginPage.clickSignInButton();

        System.out.println("Verify that the user is logged in");
        Assert.assertEquals(homePage.popupMessageGetText(), "Successful login!", "Popup text differs from \"Succesfullogin\"");
        homePage.invisibilityOfPopupMessageBox();

        System.out.println("Verify that you are send back to home page");
        homePage.verifyHomePageUrl();

        System.out.println("Verify that you have new links in navigation bar");
        headerPage.visibilityOfProfileLink();
        headerPage.visibilityOfNewpostLink();
        headerPage.visibilityOfSignOutButton();

        System.out.println("Click 'Profile' button");
        headerPage.goToProfile();

        System.out.println("Verify User profile page is visible");
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.verifyProfilePageUrl();

        System.out.println("Verify that Username is identical with profile name");
        Assert.assertEquals(profilePage.displayedUserName(), username, "Displayed User name differs from login name");

        System.out.println("Click 'Sign out' button");
        headerPage.signOut();

        System.out.println("Verify that we are signed out of profile");
        Assert.assertEquals(homePage.popupMessageGetText(), "Successful logout!", "Popup text differs from \"Succesful logout\"");
        homePage.invisibilityOfPopupMessageBox();
    }

    @Test(testName = "LoginRememberMeTest", dataProvider = "validUser")
    public void loginRememberMeTest(String username, String password) {

        System.out.println("Open home page");
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();

        System.out.println("Click 'Login' button");
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToLogin();

        System.out.println("Verify that you reach login page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginPageUrl();

        System.out.println("Click Remember checkbox and logein with test user");
        loginPage.clickLoginRememberCheckBox();
        Assert.assertTrue(loginPage.statusOfLoginRememberCheckBox());
        loginPage.loginWithTestUser(username, password);

        System.out.println("Signeout and go back to Login page");
        headerPage.signOut();
        headerPage.goToLogin();

        System.out.println("Check status of Username and Password");
        loginPage.clickSignInButton();
        Assert.assertEquals(homePage.popupMessageGetText(), "Successful login!", "Popup text differs from \"Succesfullogin\"");
        homePage.invisibilityOfPopupMessageBox();

    }

// Negative scenario test

    @Test(testName = "NegativeLoginTest", groups = "negativeTests", dataProvider = "invalidUser")
    public void negativeLoginTest(String invalidUsername, String invalidPassword, String errorMessage) {

        System.out.println("Open home page");
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();

        System.out.println("Click 'Login' button");
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToLogin();

        System.out.println("Verify that you reach login page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginPageUrl();

        System.out.println("Find 'Sign In' form");
        loginPage.verifyLoginFormVisibility();

        System.out.println("Populate 'Username' input");
        loginPage.populateUsernameField(invalidUsername);

        System.out.println("Populate 'Password' input");
        loginPage.populatePasswordField(invalidPassword);

        System.out.println("Click 'Sign In' button");
        loginPage.clickSignInButton();

        System.out.println("Check failed login attempt error message");
        Assert.assertEquals(homePage.popupMessageGetText(), errorMessage, "Error message doesn't match expected fail message");

        System.out.println("Check that we are still on login page");
        loginPage.verifyLoginPageUrl();
    }


}

