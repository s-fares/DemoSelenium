package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.ExtentManager;
import utils.ScreenshotUtils;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void beforeSuite() {
        // Initialize ExtentReports once for the whole suite
        extent = ExtentManager.getInstance();
    }

    @AfterSuite
    public void afterSuite() {
        // Flush once at the end
        extent.flush();
    }

@BeforeMethod
public void setUp() {
    // 1. Point to the driver you installed via apt-get
    System.setProperty("webdriver.chrome.driver", "/usr/bin/chromium-driver");

    ChromeOptions options = new ChromeOptions();
    
    // 2. Point to the Chromium binary you installed
    options.setBinary("/usr/bin/chromium"); 

    // 3. Essential flags for Docker
    options.addArguments("--headless");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--disable-gpu");
    options.addArguments("--window-size=1920,1080");

    driver = new ChromeDriver(options);
}

    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, result.getName());

            test.log(Status.FAIL, result.getThrowable());
            test.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test skipped");
        }

        if (driver != null) {
            driver.quit();
        }
    }

}
