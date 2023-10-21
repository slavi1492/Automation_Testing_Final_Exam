package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HeaderPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;


public class LoginTests extends BaseTest {

    @DataProvider(name = "validUser")
    public Object[][] getValidUser() {
        return new Object[][]{
                {"auto_user", "auto_pass"}
        };
    }

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

    @Test(testName = "LoginTest", groups = "PositiveTest", dataProvider = "validUser")
    // @Parameters ({"username", "password"})
    // public void positiveLoginTest(String username, String password) {
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
        Assert.assertEquals(homePage.popupMessageGetText(), "Successful logout!", "Popup text differs from \"Succesfullogin\"");
        homePage.invisibilityOfPopupMessageBox();
    }

// Negative scenario test

    @Test(testName = "NegativeLoginTest", groups = "negativeTests", dataProvider = "invalidUser")
    public void negativeLoginTest(String invalidUsername, String invalidPassword, String errorMessage) {
//
//        System.out.println("Open home page");
//        driver.get(BASE_URL);
//
//        System.out.println("Click 'Login' button");
//        WebElement loginButton = driver.findElement(By.id("nav-link-login"));
//        clickButton(loginButton);
//
//        System.out.println("Verify that you reach login page");
//        wait.until(ExpectedConditions.urlToBe(LOGIN_URL));
//
//        System.out.println("Find 'Sign In' form");
//        WebElement signInForm = driver.findElement(By.cssSelector("form"));
//        wait.until(ExpectedConditions.visibilityOf(signInForm));
//
//        System.out.println("Populate 'Username' input");
//        WebElement usernameInputField = signInForm.findElement(By.id("defaultLoginFormUsername"));
//        wait.until(ExpectedConditions.visibilityOf(usernameInputField));
//        usernameInputField.sendKeys(invalidUsername);
//
//        System.out.println("Populate 'Password' input");
//        WebElement passwordInputField = signInForm.findElement(By.id("defaultLoginFormPassword"));
//        wait.until(ExpectedConditions.visibilityOf(passwordInputField));
//        passwordInputField.sendKeys(invalidPassword);
//
//        System.out.println("Click 'Sign In' button");
//        WebElement signInButton = signInForm.findElement(By.id("sign-in-button"));
//        clickButton(signInButton);
//
//        WebElement signInOutMessageBox = driver.findElement(By.id("toast-container"));
//        wait.until(ExpectedConditions.visibilityOf(signInOutMessageBox));
//        String signInOutMessageBoxText = signInOutMessageBox.getText();
//        Assert.assertEquals(signInOutMessageBoxText, errorMessage);
//        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL);
//
//
//    }


    }
}
