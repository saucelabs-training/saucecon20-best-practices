package exercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.LoginPage;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class LoginFeatureTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp(Method method) throws MalformedURLException
    {
        // Input your SauceLabs Credentials
        String sauceUsername = System.getenv("SAUCE_USERNAME");
        String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");

        MutableCapabilities capabilities = new MutableCapabilities();

        //sets browser to Firefox
        capabilities.setCapability("browserName", "firefox");

        //sets operating system to macOS version 10.13
        capabilities.setCapability("platform", "macOS 10.13");

        //sets the browser version to 11.1
        capabilities.setCapability("version", "58.0");

        //sets your test case name so that it shows up in Sauce Labs
        capabilities.setCapability("name", method.getName());
        capabilities.setCapability("username", sauceUsername);
        capabilities.setCapability("accessKey", sauceAccessKey);

        //instantiates a remote WebDriver object with your desired capabilities
        driver = new RemoteWebDriver(new URL("https://ondemand.saucelabs.com/wd/hub"), capabilities);
    }
    @Test
    public void ShouldBeAbleToLogin() {

        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
        //navigate to the url of the Sauce Labs Sample app
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();

        // Ignore the following selectors
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.login(username, password);

        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }
    @AfterEach
    public void teardown(TestWatcher result) {
        ((JavascriptExecutor)driver).executeScript("sauce:job-result=" + (result.equals("testSuccessful") ? "passed" : "failed"));
        driver.quit();
    }
}
