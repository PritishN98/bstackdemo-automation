package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utils.ConfigReader;
import utils.WebDriverFactory;

/**
 * BaseTest manages the setup and teardown of the WebDriver lifecycle.
 */
public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional("") String browser) {
        if (browser == null || browser.trim().isEmpty()) {
            browser = ConfigReader.getBrowser();
        }
        boolean headless = ConfigReader.isHeadless();

        driver = WebDriverFactory.initDriver(browser, headless);
        driver.get(ConfigReader.getUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverFactory.quitDriver();
    }

    public WebDriver getDriver() {
        return WebDriverFactory.getDriver();
    }
}
