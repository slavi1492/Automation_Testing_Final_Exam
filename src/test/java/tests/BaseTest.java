package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    WebDriver driver;
    public final String DOWNLOAD_FOLDER = "src/test/resources/downloads";
    public final String SCREENSHOTS_FOLDER = "src/test/resources/screenshots";


    @BeforeMethod
    public void driverSetup() throws IOException {
        cleanFolder(DOWNLOAD_FOLDER);
        cleanFolder(SCREENSHOTS_FOLDER);

        WebDriverManager.chromedriver().setup();

        // Setting a download folder
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> preferences = new HashMap<>();
        preferences.put("download.default_directory", System.getProperty("user.dir").concat("\\").concat("src/test/resources/downloads"));
        options.setExperimentalOption("prefs", preferences);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

    }

    public void cleanFolder(String filepath) throws IOException {
        File folder = new File(filepath);
        FileUtils.cleanDirectory(folder);
    }

}
