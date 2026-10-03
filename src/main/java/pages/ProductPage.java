package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

/**
 * Page Object representing the Product Catalog / Home Page of bstackdemo.com.
 */
public class ProductPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    // Locators
    private final By productCards = By.className("shelf-item");
    private final By productTitles = By.className("shelf-item__title");
    private final By addToCartButtons = By.className("shelf-item__buy-btn");
    private final By cartBagIcon = By.className("bag");
    private final By cartQuantityBadge = By.className("bag__quantity");
    private final By productsFoundText = By.className("products-found");
    private final By loggedInUsername = By.className("username");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public int getProductCount() {
        waitUtils.waitForVisibility(productCards);
        return driver.findElements(productCards).size();
    }

    public List<String> getAllProductTitles() {
        waitUtils.waitForVisibility(productTitles);
        List<WebElement> elements = driver.findElements(productTitles);
        List<String> titles = new ArrayList<>();
        for (WebElement el : elements) {
            titles.add(el.getText().trim());
        }
        return titles;
    }

    public CartPage addProductToCartByIndex(int index) {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (index < 0 || index >= buttons.size()) {
            throw new IllegalArgumentException("Product index " + index + " out of bounds. Total products: " + buttons.size());
        }
        buttons.get(index).click();
        return new CartPage(driver);
    }

    public CartPage addProductToCartByName(String productName) {
        By productBuyBtn = By.xpath("//p[text()='" + productName + "']/ancestor::div[contains(@class,'shelf-item')]//div[contains(@class,'shelf-item__buy-btn')]");
        WebElement btn = waitUtils.waitForClickable(productBuyBtn);
        btn.click();
        return new CartPage(driver);
    }

    public void filterByVendor(String vendorName) {
        By filterCheckbox = By.xpath("//span[text()='" + vendorName + "'] | //span[contains(@class,'checkmark') and following-sibling::span[text()='" + vendorName + "']]");
        WebElement filter = waitUtils.waitForClickable(filterCheckbox);
        filter.click();
    }

    public int getCartBadgeCount() {
        try {
            WebElement badge = waitUtils.waitForVisibility(cartQuantityBadge);
            String text = badge.getText().trim();
            return text.isEmpty() ? 0 : Integer.parseInt(text);
        } catch (Exception e) {
            return 0;
        }
    }

    public CartPage openCart() {
        WebElement bag = waitUtils.waitForClickable(cartBagIcon);
        bag.click();
        return new CartPage(driver);
    }

    public String getProductsFoundMessage() {
        try {
            return waitUtils.waitForVisibility(productsFoundText).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isUserLoggedIn() {
        try {
            return waitUtils.waitForVisibility(loggedInUsername).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoggedInUsername() {
        try {
            return waitUtils.waitForVisibility(loggedInUsername).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }
}
