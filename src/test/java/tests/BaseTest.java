package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    WebDriver driver;
    public final String DOWNLOAD_FOLDER = "src\\test\\resources\\downloads\\";
    public final String SCREENSHOTS_FOLDER = "src\\test\\resources\\screenshots\\";


    @BeforeMethod
    public void driverSetup() throws IOException {
        cleanFolder(DOWNLOAD_FOLDER);
        cleanFolder(SCREENSHOTS_FOLDER);

        // Setup browser driver
        WebDriverManager.chromedriver().setup();

        // Setting a download folder
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> preferences = new HashMap<>();
        preferences.put("download.default_directory", System.getProperty("user.dir").concat("\\").concat("src/test/resources/downloads"));
        options.setExperimentalOption("prefs", preferences);


        // Setup driver options and timeouts
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); //If we use explicit timeouts why we set this?
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void driverCleanup() {
        driver.close();
    }

    public void takeScreenshot(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenShot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String testName = testResult.getName();
            FileUtils.copyFile(screenShot, new File(SCREENSHOTS_FOLDER.concat(testName).concat(".jpg")));
        }


    }

    public void cleanFolder(String filepath) throws IOException {
        File folder = new File(filepath);
        FileUtils.cleanDirectory(folder);
    }

}
