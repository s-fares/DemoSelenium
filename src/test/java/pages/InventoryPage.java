package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class InventoryPage {

    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Title/header
    By pageTitle = By.className("title");

    // Sort dropdown
    By sortDropdown = By.className("product_sort_container");

    // All products container
    By allProducts = By.className("inventory_item");

    // Individual properties for a product card
    By productName = By.className("inventory_item_name");
    By productPrice = By.className("inventory_item_price");
    By addToCartBtn = By.cssSelector("button.btn_inventory"); // generic add buttons

    // Cart icon badge
    By cartBadge = By.className("shopping_cart_badge");

    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public void sortBy(String optionValue) {
        driver.findElement(sortDropdown).sendKeys(optionValue); // use visible text or value
    }

    public List<WebElement> getProducts() {
        return driver.findElements(allProducts);
    }

    public void addProductToCart(String productNameText) {
        // loop through products
        for (WebElement product : driver.findElements(allProducts)) {
            String name = product.findElement(productName).getText();
            if (name.equals(productNameText)) {
                product.findElement(addToCartBtn).click();
                break;
            }
        }
    }

    public int getCartCount() {
        String count = driver.findElement(cartBadge).getText();
        return Integer.parseInt(count);
    }
}
