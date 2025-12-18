package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Dimension;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pages.LoginPage;
import utils.ExtentManager;

public class LoginBasicTest {

    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeTest
    public void setUp() {
        extent = ExtentManager.getInstance();
        test = extent.createTest("LoginBasicTest");

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) driver.quit();
        extent.flush(); // save report
    }

    @Test
    public void login() throws InterruptedException {
        test.log(Status.INFO, "Opening saucedemo.com");

        driver.get("https://www.saucedemo.com/");
        driver.manage().window().setSize(new Dimension(1350, 637));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        test.log(Status.INFO, "Login submitted");

        Assert.assertEquals(driver.getTitle(), "Swag Labs");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));

        test.log(Status.PASS, "Login verified successfully");
    }
}
