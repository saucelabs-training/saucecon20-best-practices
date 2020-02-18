package pages;

import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    private final WebDriver driver;
    //TODO more duplication
    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("https://www.saucedemo.com/checkout-complete.html");
    }
}
