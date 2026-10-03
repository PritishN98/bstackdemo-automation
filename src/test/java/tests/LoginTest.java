package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;
import utils.ConfigReader;

/**
 * Test suite covering User Authentication scenarios (Valid, Invalid, and Empty inputs).
 */
public class LoginTest extends BaseTest {

    @Test(priority = 1, groups = {"login", "smoke"}, description = "TC_001: Login with valid credentials")
    public void TC_001_loginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToSignInPage();

        String validUser = ConfigReader.getValidUsername();
        String validPass = ConfigReader.getValidPassword();

        ProductPage productPage = loginPage.login(validUser, validPass);

        Assert.assertTrue(productPage.isUserLoggedIn(), 
                "User was not logged in successfully. Username element not found.");
        Assert.assertEquals(productPage.getLoggedInUsername(), validUser, 
                "Logged in username does not match expected username.");
    }

    @Test(priority = 2, groups = {"login", "negative"}, description = "TC_002: Login with invalid credentials")
    public void TC_002_loginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToSignInPage();

        String validUser = ConfigReader.getValidUsername();
        String invalidPass = ConfigReader.getInvalidPassword();

        loginPage.login(validUser, invalidPass);

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                "Error message was not displayed for invalid credentials.");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.toLowerCase().contains("invalid password"), 
                "Expected error message to contain 'Invalid Password'. Actual: " + errorMsg);
    }

    @Test(priority = 3, groups = {"login", "negative"}, description = "TC_003: Login with empty username/password")
    public void TC_003_loginWithEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToSignInPage();

        // Attempt login without entering username or password
        loginPage.clickLogin();

        // Validate that user is not logged in and remains on signin page or error is shown
        Assert.assertFalse(loginPage.isLoginSuccessful(), 
                "User should not be logged in with empty credentials.");
        Assert.assertTrue(driver.getCurrentUrl().contains("signin") || loginPage.isErrorMessageDisplayed(), 
                "Application should prevent login with empty credentials.");
    }
}
