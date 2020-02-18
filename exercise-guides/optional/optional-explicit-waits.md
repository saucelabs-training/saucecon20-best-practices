# Exercise 6: Implement Explicit Waits
## Part One: Identify Implicit Waits
1. Checkout branch `06_explicit_waits `.
2. Open **`LogInPage`** and navigate to the **`logIn`** method.
3. As it stands, our wait strategy is inefficient because of: **`implicitlyWait`**. This means our session hangs for a broad 5 seconds after the preceding command and adds unecessary overhead to our test execution. A better strategy is to wait until a specific element renders on the page, then proceed to the next command. In other words, our wait strategy should transform as follows:
    * Before
    ```
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    ```
    
    * After
    ```
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(ExpectedConditions
            .presenceOfElementLocated(locator));
    
    ```
    However this still adds a bit of duplication. The best strategy is to add the **`WebDriverWait`** command to the **`BasePage`** class to avoid further duplication.
4. In the **`BasePage`** object, create a method that waits for specific elements to render based on a relevant `locator`. Below is an example of an explicit wait method:
    ```
    private void waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.
        presenceOfElementLocated(locator));
    }
    ```
    > Note: the `ExpectedConditions` method must be imported using: `import org.openqa.selenium.support.ui.ExpectedConditions`
    
2. In **`BasePage`**, check each method and refactor the following wherever it exists:
    * Before
    ```
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    ```
    * After
    ```
    waitForElement(locator);
    ``` 

3. Ensure each command replacement has a relevant `locator`
4. Save and run `mvn test`
5. Checkout branch `05_configure_atomic_tests` to see the answers.

<br />