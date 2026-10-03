package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

/**
 * Page Object representing the Checkout and Order Confirmation flow on bstackdemo.com.
 */
public class CheckoutPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    // Locators
    private final By firstNameInput = By.id("firstNameInput");
    private final By lastNameInput = By.id("lastNameInput");
    private final By addressInput = By.id("addressLine1Input");
    private final By provinceInput = By.id("provinceInput");
    private final By postCodeInput = By.id("postCodeInput");
    private final By submitButton = By.id("checkout-shipping-continue");
    private final By confirmationMessage = By.id("confirmation-message");
    private final By continueShoppingButton = By.xpath("//button[contains(text(),'Continue Shopping')]");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public boolean isCheckoutPageLoaded() {
        try {
            return waitUtils.waitForVisibility(firstNameInput).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterShippingDetails(String firstName, String lastName, String address, String province, String postCode) {
        WebElement fName = waitUtils.waitForVisibility(firstNameInput);
        fName.clear();
        fName.sendKeys(firstName);

        WebElement lName = driver.findElement(lastNameInput);
        lName.clear();
        lName.sendKeys(lastName);

        WebElement addr = driver.findElement(addressInput);
        addr.clear();
        addr.sendKeys(address);

        WebElement prov = driver.findElement(provinceInput);
        prov.clear();
        prov.sendKeys(province);

        WebElement zip = driver.findElement(postCodeInput);
        zip.clear();
        zip.sendKeys(postCode);
    }

    public void submitOrder() {
        WebElement submit = waitUtils.waitForClickable(submitButton);
        submit.click();
    }

    public boolean isOrderPlacedSuccessfully() {
        try {
            return waitUtils.waitForVisibility(confirmationMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getConfirmationMessage() {
        try {
            return waitUtils.waitForVisibility(confirmationMessage).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public ProductPage clickContinueShopping() {
        WebElement continueBtn = waitUtils.waitForClickable(continueShoppingButton);
        continueBtn.click();
        return new ProductPage(driver);
    }
}
