package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    WebDriver driver;
    public final String DOWNLOAD_FOLDER = "src\\test\\resources\\downloads\\";
    public final String SCREENSHOTS_FOLDER = "src\\test\\resources\\screenshots\\";




    @DataProvider(name = "validUser")
    public Object[][] getValidUser() {
        return new Object[][]{
                {"pesho12345678", "1qaz2wsx"}
        };
    }

    @BeforeClass
    public void screenShotsClean() throws IOException {
        cleanFolder(SCREENSHOTS_FOLDER);
    }


    @BeforeMethod
    public void driverSetup() throws IOException {
        cleanFolder(DOWNLOAD_FOLDER);


        // Setup browser driver
        WebDriverManager.chromedriver().setup();

        // Setting a download folder
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> preferences = new HashMap<>();
        preferences.put("download.default_directory", System.getProperty("user.dir").concat("\\").concat("src/test/resources/downloads"));
        options.setExperimentalOption("prefs", preferences);


        // Setup driver options and timeouts
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); //If we use explicit timeouts why we set this?
        driver.manage().window().maximize();


    }

    public void takeScreenshot(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenShot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String testName = testResult.getName();
            try {
                FileUtils.copyFile(screenShot, new File(SCREENSHOTS_FOLDER.concat(testName).concat(".jpg")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public void cleanFolder(String filepath) throws IOException {
        File folder = new File(filepath);
        FileUtils.cleanDirectory(folder);
    }

    public void loginWithTestUser(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.populateUsernameField(username);
        loginPage.populatePasswordField(password);
        loginPage.clickSignInButton();
        HomePage homePage = new HomePage(driver);
        homePage.verifyHomePageUrl();
    }

    @AfterMethod
    public void driverCleanup(ITestResult testResult) {
        takeScreenshot(testResult);
        driver.close();
    }

}
