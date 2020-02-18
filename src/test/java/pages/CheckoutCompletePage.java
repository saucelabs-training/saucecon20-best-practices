package pages;

import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage{

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean IsLoaded()
    {
        return driver.getCurrentUrl().contains("https://www.saucedemo.com/checkout-complete.html");
    }
}
