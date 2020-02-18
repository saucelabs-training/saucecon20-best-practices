package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
    public final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
