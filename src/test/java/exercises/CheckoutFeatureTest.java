package exercises;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.*;

import java.util.concurrent.TimeUnit;

public class CheckoutFeatureTest extends BaseTest {

    @Test
    public void ShouldBeAbleToCheckoutWithItems() {
        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
        //navigate to the url of the Sauce Labs Sample app
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();

        // Ignore the following selectors
        String username = "standard_user";
        String password = "secret_sauce";
        InventoryPage inventoryPage = loginPage.login(username, password);

        // Assert that the url is on the inventory page
        //TODO fix this assertion later
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        inventoryPage.addBackpackToCart();
        ShoppingCartPage cart = inventoryPage.goToShoppingCart();
        CheckoutStepTwoPage stepTwoPage = cart.checkout();
        ConfirmationPage confirmationPage = stepTwoPage.fillOutInformation("first", "last", "zip");
        CheckoutCompletePage finalConfirmationPage = confirmationPage.finish();
        Assertions.assertTrue(finalConfirmationPage.isLoaded());
    }
}
