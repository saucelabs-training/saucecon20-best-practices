package exercises;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.CheckoutCompletePage;
import pages.ConfirmationPage;

import java.util.concurrent.TimeUnit;


public class CheckoutFeatureTest extends BaseTest{

    @Test
    public void ShouldBeAbleToCheckoutWithItems() {

        // TODO deal with this in the future: wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        confirmationPage.visit();
        confirmationPage.setPageState();
        Assertions.assertTrue(confirmationPage.hasItems());

        CheckoutCompletePage completePage = confirmationPage.FinishCheckout();
        // assert that the test is finished by checking the last page's URL
        Assertions.assertTrue(completePage.IsLoaded());
    }

}
