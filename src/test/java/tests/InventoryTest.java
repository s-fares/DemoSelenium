package tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import org.testng.Assert;

import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTest extends BaseTest {

    @Test
    public void addToCart() throws InterruptedException {
        // Create a new Extent test entry
        test = extent.createTest("InventoryTest - Add Item to Cart");

        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        test.log(Status.INFO, "Logged in successfully");

        InventoryPage inventoryPage = new InventoryPage(driver);

        inventoryPage.addProductToCart("Sauce Labs Backpack");

        Assert.assertEquals(inventoryPage.getCartCount(), 1);

    }
}
