package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage {
    private final WebDriver driver;
    //TODO notice the duplication in all of page our classes
    public CheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
    }

    public ConfirmationPage fillOutInformation(String first, String last, String zip) {
        String firstNameField = "[data-test='firstName']";
        String lastNameField = "[data-test='lastName']";
        String postalField= "[data-test='postalCode']";
        String continueLink = "[value='CONTINUE']";
        // proceed to shipping info (checkout step 1)
        driver.findElement(By.cssSelector(firstNameField)).sendKeys(first);
        driver.findElement(By.cssSelector(lastNameField)).sendKeys(last);
        driver.findElement(By.cssSelector(postalField)).sendKeys(zip);
        driver.findElement(By.cssSelector(continueLink)).click();
        return new ConfirmationPage(driver);
    }
}
