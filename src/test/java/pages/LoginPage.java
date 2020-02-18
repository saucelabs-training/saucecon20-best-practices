package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage visit() {
        driver.get("https://www.saucedemo.com");
        return this;
    }

    public InventoryPage login(String username, String password)
    {
        String userField = "[data-test='username']";
        String passField = "[data-test='password']";
        String loginBtn = "[value='LOGIN']";

        // send username keystrokes
        driver.findElement(By.cssSelector(userField)).sendKeys(username);

        // send password keystrokes
        driver.findElement(By.cssSelector(passField)).sendKeys(password);

        // click login button to submit keystrokes
        driver.findElement(By.cssSelector(loginBtn)).click();
        return new InventoryPage(driver);
    }
}
