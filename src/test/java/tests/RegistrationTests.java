package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

public class RegistrationTests extends BaseTest {


    @Test(testName = "PositiveRegistrationTest", groups = "PositiveTests")
    @Parameters({"username", "password", "email"})
    public void positiveRegistrationTest(String username, String password, String email) {

        System.out.println("Open home page");
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();

        System.out.println("Click 'Login' button");
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToLogin();

        System.out.println("Verify that you reach login page");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginPageUrl();

        System.out.println("Click 'Register' link");
        loginPage.clickRegistrationLink();

        System.out.println("Verify registration page");
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.verifyRegistrationPageUrl();

        System.out.println("Populate Username");
        registrationPage.populateUsernameField(username);

        System.out.println("Check validity of Username input");
        registrationPage.checkValidityOfUsernameInput();

        System.out.println("Populate Email");
        registrationPage.populateEmailInputField(email);

        System.out.println("Check validity of Email input");
        registrationPage.checkValidityOfEmailInput();

        System.out.println("Populate Password");
        registrationPage.populatePasswordField(password);

        System.out.println("Check validity of Password input");
        registrationPage.checkValidityOfPasswordInput();

        System.out.println("Populate ConfirmPassword");
        registrationPage.populateConfirmPasswordField(password);

        System.out.println("Check validity of Confirm Password input");
        registrationPage.checkValidityOfConfirmPasswordInput();

        System.out.println("Check Input validity");
        registrationPage.checkInputValidity();

        System.out.println("Click 'SignIn' button");
        registrationPage.clickSignInButton();

        System.out.println("Check Popup message");
        Assert.assertEquals(homePage.popupMessageGetText(), "Successful register!",
                "Popup message is incorect. Actual message is: " + homePage.popupMessageGetText());

        System.out.println("Verify that we are on home page");
        homePage.verifyHomePageUrl();

        System.out.println("Open 'Profile' link");
        headerPage.goToProfile();

        System.out.println("Verify User profile page is visible");
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.verifyProfilePageUrl();

        System.out.println("Verify that Username is identical with profile name");
        Assert.assertEquals(profilePage.displayedUserName(), username, "Displayed User name differs from registered name");

        System.out.println("Verify that number of ports are equal to zero");
        Assert.assertEquals(profilePage.totalNumberOfUserPosts(),0,"Number of posts are not 0 for newly registered user");

    }
}
