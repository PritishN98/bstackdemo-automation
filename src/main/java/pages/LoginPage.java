package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

/**
 * Page Object representing the Sign-In page of bstackdemo.com.
 */
public class LoginPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    // Locators
    private final By signInNavLink = By.id("signin");
    private final By usernameDropdown = By.id("username");
    private final By usernameInput = By.cssSelector("#username input, #react-select-2-input");
    private final By passwordDropdown = By.id("password");
    private final By passwordInput = By.cssSelector("#password input, #react-select-3-input");
    private final By loginButton = By.id("login-btn");
    private final By errorMessage = By.className("api-error");
    private final By loggedInUser = By.className("username");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void navigateToSignInPage() {
        try {
            WebElement link = waitUtils.waitForClickable(signInNavLink);
            link.click();
        } catch (Exception e) {
            driver.get("https://bstackdemo.com/signin");
        }
    }

    public void enterUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return;
        }
        try {
            WebElement input = waitUtils.waitForClickable(usernameInput);
            input.sendKeys(username);
            input.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            WebElement dropdown = waitUtils.waitForClickable(usernameDropdown);
            dropdown.click();
            By option = By.xpath("//div[contains(@id,'react-select') and text()='" + username + "']");
            waitUtils.waitForClickable(option).click();
        }
    }

    public void enterPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return;
        }
        try {
            WebElement input = waitUtils.waitForClickable(passwordInput);
            input.sendKeys(password);
            input.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            WebElement dropdown = waitUtils.waitForClickable(passwordDropdown);
            dropdown.click();
            By option = By.xpath("//div[contains(@id,'react-select') and text()='" + password + "']");
            waitUtils.waitForClickable(option).click();
        }
    }

    public void clickLogin() {
        WebElement button = waitUtils.waitForClickable(loginButton);
        button.click();
    }

    public ProductPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new ProductPage(driver);
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return waitUtils.waitForVisibility(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            return waitUtils.waitForVisibility(errorMessage).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isLoginSuccessful() {
        try {
            return waitUtils.waitForVisibility(loggedInUser).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoggedInUsername() {
        try {
            return waitUtils.waitForVisibility(loggedInUser).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }
}
