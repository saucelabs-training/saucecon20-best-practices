package pages;

import exercises.BaseTest;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    //TODO duplication in the driver properties
    private final WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }
}
