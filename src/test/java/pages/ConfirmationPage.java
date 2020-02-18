package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
    //TODO duplication in the driver properties
    private final WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    //TODO duplication between this and LoginPage
    public void visit()
    {
        //TODO duplication in URL with LoginPage
        driver.navigate().to("https://www.saucedemo.com/checkout-step-two.html");
    }

    public void setPageState()
    {
        ((JavascriptExecutor)driver).executeScript("window.sessionStorage.setItem('standard-username', 'standard-user')");
        ((JavascriptExecutor)driver).executeScript("window.sessionStorage.setItem('cart-contents', '[4,1]')");
        driver.navigate().refresh();
    }
    public Boolean hasItems() {
        String cartBadge = "shopping_cart_badge";
        return Integer.parseInt(driver.findElement(By.className(cartBadge)).getText()) > 0;
    }
    public CheckoutCompletePage FinishCheckout()
    {
        String finished =".btn_action.cart_button";
        driver.findElement(By.cssSelector(finished)).click();
        return new CheckoutCompletePage(driver);
    }
}
