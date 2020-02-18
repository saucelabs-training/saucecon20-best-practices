package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ConfirmationPage extends BasePage {
    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }
    public void visit()
    {
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
        String finished = ".btn_action.cart_button";
        WebElement finishButton = driver.findElement(By.cssSelector(finished));
        finishButton.click();
        return new CheckoutCompletePage(driver);
    }
}
