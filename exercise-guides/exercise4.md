# Exercise 4: Configure Atomic Tests

## Part One: Create `CheckoutCompletePage`
1. Checkout the branch `04_configure_atomic_tests`.
2. In `src > test > java > pages`, create a new class called `CheckoutCompletePage`
3. Add the following:
    ```
    public class CheckoutCompletePage {
        private final WebDriver driver;

        public CheckoutCompletePage(WebDriver driver) {
            this.driver = driver;
        }
    }
    ```
4. Add a new class method called `IsLoaded()` to confirm the correct checkout page is loaded:
    ```
    public boolean IsLoaded()
    {
        return driver.getCurrentUrl().contains("https://www.saucedemo.com/checkout-complete.html");
    }
    ```
    
<br />

## Part Two: Modify `ConfirmationPage`
1. Open `ConfirmationPage` in `src > test > java > pages`.
2. Add the following class methods:
    ```
    public void visit() {
        driver.get("https://www.saucedemo.com/checkout-step-two.html");
    }
    ```
    ```
    public Boolean hasItems() {
        String cartBadge = "shopping_cart_badge";
        return Integer.parseInt(driver.findElement(By.className(cartBadge)).getText()) > 0;
    }
    ```
    ```
   public CheckoutCompletePage FinishCheckout()
    {
        String finished =".btn_action.cart_button";
        driver.findElement(By.cssSelector(finished)).click();
        return new CheckoutCompletePage(driver);
    }
    ```
3. Open **`CheckoutFeatureTest`** located in `src > test > java > exercises`.
4. You'll notice that the **`ShouldBeAbleToCheckout()`** class method steps through many pages to get to the checkout function. The existing test flow works like this:
    * User logs in
    * Adds some items to the cart
    * Clicks the cart icon to proceed to checkout

This approach is under-optimized because our tests shouldn't rely on the assertions of other tests and it's unecessary to travel through each individual page to reach that feature. Therefore if we're only testing features on a specific page, we can modify the page state using the **`JavaScriptExecutor`**.
    
<br />
    
## Part Three: Implement the `JavascriptExecutor` to Bypass Pages
1. Go back to **`ConfirmationPage`** and add the following class method:
    ``` 
    public void setPageState() {
        driver.navigate().refresh();
    }
    ```
2. In **`setPageState()`** add the following **`JavaScriptExecutor`** command to bypass logging in through the **`LoginPage`** object:
    ```
    ((JavascriptExecutor)driver).executeScript("window.sessionStorage.setItem('standard-username', 'standard-user')");
    ```
3. Then add another **`JavaScriptExecutor`** command to bypass adding items to the cart through the **`InventoryPage`** object:
    ```
    ((JavascriptExecutor)driver).executeScript("window.sessionStorage.setItem('cart-contents', '[4,1]')");
    ```
4. In **`CheckoutFeatureTest`**, delete the existing commands and add the following:
    ```
    @Test
    public void ShouldBeAbleToCheckoutWithItems() {
        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        confirmationPage.visit();
        confirmationPage.setPageState();
        Assert.assertTrue(confirmationPage.hasItems());

        CheckoutCompletePage completePage = confirmationPage.FinishCheckout();
        // assert that the test is finished by checking the last page's URL
        Assert.assertTrue(completePage.IsLoaded());
    }
    ```
5. Save all and run the following command to ensure the build passes:
    ```
    mvn test
    ```
6. Use `git stash` or `git commit` to discard or save your changes. Checkout the next branch to proceed to the next exercise
    ```
    git checkout 05_create_base_page
    ```