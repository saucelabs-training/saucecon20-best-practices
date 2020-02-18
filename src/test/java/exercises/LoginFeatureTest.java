package exercises;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginFeatureTest extends BaseTest {

    @Test
    public void ShouldBeAbleToLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        Assertions.assertTrue(loginPage.isLoaded());

        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        Assertions.assertTrue(inventoryPage.isLoaded());
    }
}
