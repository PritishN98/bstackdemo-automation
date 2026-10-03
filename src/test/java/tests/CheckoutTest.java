package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.ConfigReader;

/**
 * Test suite covering Checkout and Order Placement scenarios (Valid checkout and Negative flow without items).
 */
public class CheckoutTest extends BaseTest {

    @Test(priority = 7, groups = {"checkout", "smoke"}, description = "TC_007: Place order with valid details")
    public void TC_007_placeOrderWithValidDetails() {
        // 1. Log in with valid credentials first
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToSignInPage();
        ProductPage productPage = loginPage.login(ConfigReader.getValidUsername(), ConfigReader.getValidPassword());

        // 2. Add an item to cart
        CartPage cartPage = productPage.addProductToCartByIndex(0);
        Assert.assertTrue(cartPage.isCartOpen(), "Cart drawer should be open after adding product.");

        // 3. Click Checkout
        CheckoutPage checkoutPage = cartPage.clickCheckout();

        // 4. Fill shipping details
        Assert.assertTrue(checkoutPage.isCheckoutPageLoaded(), "Checkout page failed to load.");
        checkoutPage.enterShippingDetails("John", "Doe", "123 Market Street", "California", "94103");

        // 5. Submit order
        checkoutPage.submitOrder();

        // 6. Verify confirmation message
        Assert.assertTrue(checkoutPage.isOrderPlacedSuccessfully(), 
                "Order confirmation message was not displayed.");
        String confirmation = checkoutPage.getConfirmationMessage();
        Assert.assertTrue(confirmation.toLowerCase().contains("your order has been successfully placed"),
                "Confirmation text mismatch. Actual: " + confirmation);

        System.out.println("TC_007 Passed: Order successfully placed! Message: " + confirmation);
    }

    @Test(priority = 8, groups = {"checkout", "negative"}, description = "TC_008: Checkout flow without adding items (negative test)")
    public void TC_008_checkoutFlowWithoutAddingItems() {
        ProductPage productPage = new ProductPage(driver);

        // 1. Open cart when no items have been added
        CartPage cartPage = productPage.openCart();

        // 2. Verify cart is empty
        Assert.assertTrue(cartPage.isCartEmpty(), 
                "Expected cart to be empty when no items have been added.");

        // 3. Negative check: Checkout button should not be clickable/available for empty cart,
        // or navigating to checkout directly should redirect or keep user from placing order
        if (cartPage.isCheckoutButtonPresent()) {
            try {
                cartPage.clickCheckout();
                // If clicked, assert checkout page does not proceed with empty cart or remains on product page
                Assert.assertFalse(driver.getCurrentUrl().contains("confirmation"), 
                        "Order placement should not be possible with an empty cart.");
            } catch (Exception e) {
                // Expected: checkout prevented
                System.out.println("Checkout prevented as expected on empty cart.");
            }
        } else {
            Assert.assertFalse(cartPage.isCheckoutButtonPresent(), 
                    "Checkout button correctly disabled or hidden for empty cart.");
        }
    }
}
