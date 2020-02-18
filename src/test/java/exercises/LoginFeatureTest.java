package exercises;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;


public class LoginFeatureTest extends BaseTest {

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
}
