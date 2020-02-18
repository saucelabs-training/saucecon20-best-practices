package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
    private final WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutCompletePage finish() {
        String finished =".btn_action.cart_button";
        driver.findElement(By.cssSelector(finished)).click();
        return new CheckoutCompletePage(driver);
    }
}
