package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

/**
 * Page Object representing the Cart drawer / cart view on bstackdemo.com.
 */
public class CartPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    // Locators
    private final By cartContainer = By.className("float-cart");
    private final By openCartContainer = By.className("float-cart--open");
    private final By cartItems = By.cssSelector(".float-cart .shelf-item");
    private final By cartItemTitles = By.cssSelector(".float-cart .shelf-item .title");
    private final By deleteButtons = By.className("shelf-item__del");
    private final By subtotalPrice = By.cssSelector(".sub-price__val");
    private final By checkoutButton = By.className("buy-btn");
    private final By closeCartButton = By.className("float-cart__close-btn");
    private final By emptyCartMessage = By.className("shelf-empty");
    private final By cartBadgeQuantity = By.className("bag__quantity");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public boolean isCartOpen() {
        try {
            return waitUtils.waitForVisibility(openCartContainer).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getCartItemCount() {
        try {
            return driver.findElements(cartItems).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public int getCartBadgeCount() {
        try {
            WebElement badge = waitUtils.waitForVisibility(cartBadgeQuantity);
            String text = badge.getText().trim();
            return text.isEmpty() ? 0 : Integer.parseInt(text);
        } catch (Exception e) {
            return 0;
        }
    }

    public List<String> getCartItemTitles() {
        List<WebElement> elements = driver.findElements(cartItemTitles);
        List<String> titles = new ArrayList<>();
        for (WebElement el : elements) {
            titles.add(el.getText().trim());
        }
        return titles;
    }

    public boolean isItemPresentInCart(String expectedTitle) {
        List<String> titles = getCartItemTitles();
        for (String t : titles) {
            if (t.equalsIgnoreCase(expectedTitle)) {
                return true;
            }
        }
        return false;
    }

    public void removeItemByIndex(int index) {
        List<WebElement> delButtons = waitUtils.waitForVisibility(driver.findElement(deleteButtons)) != null
                ? driver.findElements(deleteButtons) : new ArrayList<>();
        if (index >= 0 && index < delButtons.size()) {
            delButtons.get(index).click();
        }
    }

    public String getSubtotal() {
        try {
            return waitUtils.waitForVisibility(subtotalPrice).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isCartEmpty() {
        try {
            return driver.findElements(emptyCartMessage).size() > 0 
                || driver.findElements(cartItems).isEmpty();
        } catch (Exception e) {
            return true;
        }
    }

    public CheckoutPage clickCheckout() {
        WebElement checkoutBtn = waitUtils.waitForClickable(checkoutButton);
        checkoutBtn.click();
        return new CheckoutPage(driver);
    }

    public boolean isCheckoutButtonPresent() {
        try {
            return driver.findElements(checkoutButton).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void closeCart() {
        try {
            WebElement closeBtn = waitUtils.waitForClickable(closeCartButton);
            closeBtn.click();
        } catch (Exception e) {
            // drawer already closed
        }
    }
}
