import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginScenariosTests {

    ChromeDriver driver;
    WebDriverWait wait;


    final String BASE_URL = "http://training.skillo-bg.com:4200";
    final String HOME_URL = BASE_URL + "/posts/all";
    final String LOGIN_URL = BASE_URL + "/users/login";
    final String USER_URL = BASE_URL + "/users/4068";

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


    @BeforeMethod
    public void driverSetup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }
// Positive scenario test

    @Test(groups = "PositiveTest", dataProvider = "validUser")
    // @Parameters ({"username", "password"})
    // public void positiveLoginTest(String username, String password) {
    public void positiveLoginTest(String username, String password) {

        System.out.println("Open home page");
        driver.get(BASE_URL);

        System.out.println("Click 'Login' button");
        WebElement loginButton = driver.findElement(By.id("nav-link-login"));
        clickButton(loginButton);

        System.out.println("Verify that you reach login page");
        wait.until(ExpectedConditions.urlToBe(LOGIN_URL));

        System.out.println("Find 'Sign In' form");
        WebElement signInForm = driver.findElement(By.cssSelector("form"));
        wait.until(ExpectedConditions.visibilityOf(signInForm));

        System.out.println("Populate 'Username' input");
        WebElement usernameInputField = signInForm.findElement(By.id("defaultLoginFormUsername"));
        wait.until(ExpectedConditions.visibilityOf(usernameInputField));
        usernameInputField.sendKeys(username);

        System.out.println("Populate 'Password' input");
        WebElement passwordInputField = signInForm.findElement(By.id("defaultLoginFormPassword"));
        wait.until(ExpectedConditions.visibilityOf(passwordInputField));
        passwordInputField.sendKeys(password);

        System.out.println("Click 'Sign In' button");
        WebElement signInButton = signInForm.findElement(By.id("sign-in-button"));
        clickButton(signInButton);

        System.out.println("Verify that the user is logged in");
        WebElement signInOutMessageBox = driver.findElement(By.id("toast-container"));
        wait.until(ExpectedConditions.visibilityOf(signInOutMessageBox));
        String signInOutMessageBoxText = signInOutMessageBox.getText();
        Assert.assertEquals(signInOutMessageBoxText.trim(), "Successful login!");
        wait.until(ExpectedConditions.invisibilityOf(signInOutMessageBox));
        wait.until(ExpectedConditions.urlToBe(HOME_URL));
        WebElement navBarProfileButton = driver.findElement(By.id("nav-link-profile"));
        wait.until(ExpectedConditions.visibilityOf(navBarProfileButton));

        System.out.println("Click 'Profile' button");
        clickButton(navBarProfileButton);

        System.out.println("Verify User profile page is visible");
        wait.until(ExpectedConditions.urlToBe(USER_URL));
        WebElement profileSectionElement = driver.findElement(By.cssSelector("app-profile-section"));
        String userNameText = profileSectionElement.findElement(By.cssSelector("app-profile-section .profile-user-settings h2")).getText();
        Assert.assertEquals(userNameText, username);


        System.out.println("Click 'Sign out' button");
        WebElement signOutButton = driver.findElement(By.cssSelector(".fa-sign-out-alt"));
        clickButton(signOutButton);

        System.out.println("Verify that we are signed out of profile");
        wait.until(ExpectedConditions.visibilityOf(signInOutMessageBox));
        signInOutMessageBoxText = signInOutMessageBox.getText();
        Assert.assertEquals(signInOutMessageBoxText.trim(), "Successful logout!");
        wait.until(ExpectedConditions.invisibilityOf(navBarProfileButton));
    }

// Negative scenario test

    @Test(groups = "negativeTests", dataProvider = "invalidUser")
    public void negativeLoginTest(String invalidUsername, String invalidPassword, String errorMessage) {

        System.out.println("Open home page");
        driver.get(BASE_URL);

        System.out.println("Click 'Login' button");
        WebElement loginButton = driver.findElement(By.id("nav-link-login"));
        clickButton(loginButton);

        System.out.println("Verify that you reach login page");
        wait.until(ExpectedConditions.urlToBe(LOGIN_URL));

        System.out.println("Find 'Sign In' form");
        WebElement signInForm = driver.findElement(By.cssSelector("form"));
        wait.until(ExpectedConditions.visibilityOf(signInForm));

        System.out.println("Populate 'Username' input");
        WebElement usernameInputField = signInForm.findElement(By.id("defaultLoginFormUsername"));
        wait.until(ExpectedConditions.visibilityOf(usernameInputField));
        usernameInputField.sendKeys(invalidUsername);

        System.out.println("Populate 'Password' input");
        WebElement passwordInputField = signInForm.findElement(By.id("defaultLoginFormPassword"));
        wait.until(ExpectedConditions.visibilityOf(passwordInputField));
        passwordInputField.sendKeys(invalidPassword);

        System.out.println("Click 'Sign In' button");
        WebElement signInButton = signInForm.findElement(By.id("sign-in-button"));
        clickButton(signInButton);

        WebElement signInOutMessageBox = driver.findElement(By.id("toast-container"));
        wait.until(ExpectedConditions.visibilityOf(signInOutMessageBox));
        String signInOutMessageBoxText = signInOutMessageBox.getText();
        Assert.assertEquals(signInOutMessageBoxText, errorMessage);
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL);


    }

    @AfterMethod
    public void closeDriver() {
        driver.close();
    }

    public void clickButton(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();

    }

}
