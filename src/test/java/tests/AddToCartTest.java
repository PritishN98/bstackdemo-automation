package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.ProductPage;

/**
 * Test suite covering Cart interactions (Add single item, Add multiple items, and Remove item).
 */
public class AddToCartTest extends BaseTest {

    @Test(priority = 4, groups = {"cart", "smoke"}, description = "TC_004: Add single item to cart")
    public void TC_004_addSingleItemToCart() {
        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.getProductCount() > 0, "No products found on homepage.");

        // Add the first product to cart
        CartPage cartPage = productPage.addProductToCartByIndex(0);

        // Verify cart opens and count is 1
        Assert.assertTrue(cartPage.isCartOpen(), "Cart drawer did not open after adding product.");
        int cartCount = cartPage.getCartBadgeCount();
        Assert.assertTrue(cartCount >= 1, "Expected cart badge count to be at least 1, but found: " + cartCount);
    }

    @Test(priority = 5, groups = {"cart", "functional"}, description = "TC_005: Add multiple items to cart and verify cart count")
    public void TC_005_addMultipleItemsToCartAndVerifyCount() {
        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.getProductCount() >= 3, "At least 3 products required for multiple add test.");

        // 1. Add first product
        CartPage cartPage = productPage.addProductToCartByIndex(0);
        cartPage.closeCart();

        // 2. Add second product
        productPage.addProductToCartByIndex(1);
        cartPage.closeCart();

        // 3. Add third product
        productPage.addProductToCartByIndex(2);

        // 4. Verify total cart count equals 3
        int totalCartCount = cartPage.getCartBadgeCount();
        Assert.assertEquals(totalCartCount, 3, 
                "Cart badge count did not match expected count of 3 items.");
        Assert.assertEquals(cartPage.getCartItemCount(), 3, 
                "Cart items list count did not match 3 items.");
    }

    @Test(priority = 6, groups = {"cart", "functional"}, description = "TC_006: Remove item from cart")
    public void TC_006_removeItemFromCart() {
        ProductPage productPage = new ProductPage(driver);

        // 1. Add an item to cart
        CartPage cartPage = productPage.addProductToCartByIndex(0);
        Assert.assertTrue(cartPage.isCartOpen(), "Cart drawer should be open.");

        int initialCount = cartPage.getCartBadgeCount();
        Assert.assertTrue(initialCount > 0, "Initial cart count should be greater than 0.");

        // 2. Remove the item
        cartPage.removeItemByIndex(0);

        // 3. Verify item is removed (count decreases or cart becomes empty)
        int updatedCount = cartPage.getCartBadgeCount();
        Assert.assertTrue(updatedCount < initialCount || cartPage.isCartEmpty(), 
                "Item was not removed from cart. Initial count: " + initialCount + ", Updated count: " + updatedCount);
    }
}
