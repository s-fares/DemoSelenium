package tests;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void login() throws InterruptedException {
        test = extent.createTest("LoginTest - Verify Login functionality"); // new test for reporting

        test.log(Status.INFO, "Open saucedemo.com");
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        test.log(Status.INFO, "Login submitted");

        Assert.assertEquals(driver.getTitle(), "Swag Labs");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }
}
