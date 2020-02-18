package exercises;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.CheckoutCompletePage;
import pages.ConfirmationPage;


public class CheckoutFeatureTest extends BaseTest {

    @Test
    public void ShouldBeAbleToCheckoutWithItems() {
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        confirmationPage.visit();
        Assertions.assertTrue(confirmationPage.isLoaded());

        confirmationPage.setPageState();
        Assertions.assertTrue(confirmationPage.hasItems());
        CheckoutCompletePage completePage = confirmationPage.finishCheckout();
        Assertions.assertTrue(completePage.isLoaded());
    }

}
