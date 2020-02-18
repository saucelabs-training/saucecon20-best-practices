package pages;

import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    //TODO notice the duplication
    private final WebDriver driver;

    public CheckoutCompletePage(WebDriver driver) {
        //TODO notice the duplication
        this.driver = driver;
    }

    public boolean IsLoaded()
    {
        return driver.getCurrentUrl().contains("https://www.saucedemo.com/checkout-complete.html");
    }
}
