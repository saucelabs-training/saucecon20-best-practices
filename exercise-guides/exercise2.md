# Exercise 2: Create Page Objects

## Part One: Create a  `LoginPage`
1. Checkout branch `02_create_page_objects`. 
3. In the **`pages`** package, navigate to the class called **`LoginPage`**
4. Create the constructor for the page object:
    ```
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    ```
5. Create a `visit` method in the `LoginPage` object:
    ```
    public LoginPage visit() {
        driver.get("https://www.saucedemo.com");
        return this;
    }
    ```
6. Open **`LoginFeatureTest`** and import the **`LoginPage`** changes to replace **`driver.navigate().to("https://www.saucedemo.com")`** For Example:
    
    * Before
    ```
    driver.navigate().to("https://www.saucedemo.com");
    ```
    * After
    ```
    LoginPage LoginPage = new LoginPage(driver);
    LoginPage.visit();
    ```

7. Run a `mvn test` command to see if the test executes successfully:
    ```
    mvn test -Dtest=LoginFeatureTest
    ```   
    <br />
    
## Part Two: Create `login()` Method
1. Open **`LoginPage`** and create a new class method called `login()`. This method will return a new page object that represents the next page in the journey (i.e. `InventoryPage`)
2. Add the **`LoginPage.visit()`** action in place of **`driver.navigate().to("https://www.saucedemo.com")`** The method will also expect some `String` data for the credentials (`username` and `password`). For Example:
    ```
    public InventoryPage login(String username, String password)
    {
        String userField = "[data-test='username']";
        String passField = "[data-test='password']";
        String loginBtn = "[value='LOGIN']";
    }
    ```
3. Add the following Selenium commands in order to send the keystrokes:
    ```
    // send username keystrokes
    driver.findElement(By.cssSelector(userField)).sendKeys(username);

    // send password keystrokes
    driver.findElement(By.cssSelector(passField)).sendKeys(password);

    // click login button to submit keystrokes
    driver.findElement(By.cssSelector(loginBtn)).click();
    return new InventoryPage(driver);
    ```
    
4. Open `LoginFeatureTest`, and add the following to the `ShouldBeAbleToLogin` method:
    ```
    import pages.LoginPage;
    ...
        @Test
        public void ShouldBeAbleToLogin() {

            // wait 5 seconds
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
            //navigate to the url of the Sauce Labs Sample app
            LoginPage loginPage = new LoginPage(driver);
            loginPage.visit();

            // Ignore the following selectors
            String username = "standard_user";
            String password = "secret_sauce";
            loginPage.login(username, password);
            Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        }
    ```
5. Save and run `mvn test` to ensure the test still executes:
    ```
    mvn test -Dtest=LoginFeatureTest
    ```

<br />

## Part Three: Create Remaining Page Objects
1. Complete the `InventoryPage` object with the following class methods:
    ```
    private final WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addBackpackToCart() {
        String backpack = "div:nth-child(1) > div.pricebar > button";
        driver.findElement(By.cssSelector(backpack)).click();
    }

    public ShoppingCartPage goToShoppingCart() {
        String cart = "#shopping_cart_container > a > svg";
        driver.findElement(By.cssSelector(cart)).click();
        return new ShoppingCartPage(driver);
    }
    ```
2. Create the remainder page objects:
    * `ShoppingCartPage`
    * `ConfirmationPage`
    * `CheckoutStepTwoPage`
    * `CheckoutCompletePage`
3. Add the following methods to each respective page object:
    * **`ShoppingCartPage`**
        ```
        public class ShoppingCartPage {
            private final WebDriver driver;
            public ShoppingCartPage(WebDriver driver) {
                this.driver = driver;
            }
            public CheckoutStepTwoPage checkout() {
                String checkoutLink = "div.cart_footer > a.btn_action.checkout_button";
                driver.findElement(By.cssSelector(checkoutLink)).click();
            return new CheckoutStepTwoPage(driver);
            }
        }
        ```
    * **`ConfirmationPage`**
        ```
        public class ConfirmationPage {
            private final WebDriver driver;
            public ConfirmationPage(WebDriver driver) {
                this.driver = driver;
            }

            public CheckoutCompletePage finish() {
                String finished =".btn_action.cart_button";
                driver.findElement(By.cssSelector(finished)).click();
                return new CheckoutCompletePage(driver);
            }
        }
        ```
    * **`CheckoutStepTwoPage`**
        ```
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
        ```
    * **`CheckoutCompletePage`**
        ```
        public class CheckoutCompletePage {
            private final WebDriver driver;
            //TODO more duplication
            public CheckoutCompletePage(WebDriver driver) {
                this.driver = driver;
            }

            public boolean isLoaded() {
                return driver.getCurrentUrl().contains("https://www.saucedemo.com/checkout-complete.html");
            }
        }
        ```
4. Create a new test class called **`CheckoutFeatureTest`** with the following;
    ```
    public class CheckoutFeatureTest {
        protected WebDriver driver;

        @BeforeMethod
        public void setUp(Method method) throws MalformedURLException
        {
            // Input your SauceLabs Credentials
            String sauceUsername = System.getenv("SAUCE_USERNAME");
            String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");

            MutableCapabilities capabilities = new MutableCapabilities();

            //sets browser to Firefox
            capabilities.setCapability("browserName", "firefox");

            //sets operating system to macOS version 10.13
            capabilities.setCapability("platform", "macOS 10.13");

            //sets the browser version to 11.1
            capabilities.setCapability("version", "58.0");

            //sets your test case name so that it shows up in Sauce Labs
            capabilities.setCapability("name", method.getName());
            capabilities.setCapability("username", sauceUsername);
            capabilities.setCapability("accessKey", sauceAccessKey);

            //instantiates a remote WebDriver object with your desired capabilities
            driver = new RemoteWebDriver(new URL("https://ondemand.saucelabs.com/wd/hub"), capabilities);
        }
        @Test
        public void ShouldBeAbleToCheckoutWithItems() {
            // wait 5 seconds
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
            //navigate to the url of the Sauce Labs Sample app
            LoginPage loginPage = new LoginPage(driver);
            loginPage.visit();

            // Ignore the following selectors
            String username = "standard_user";
            String password = "secret_sauce";
            InventoryPage inventoryPage = loginPage.login(username, password);

            // Assert that the url is on the inventory page
            //TODO fix this assertion later
            Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
            inventoryPage.addBackpackToCart();
            ShoppingCartPage cart = inventoryPage.goToShoppingCart();
            CheckoutStepTwoPage stepTwoPage = cart.checkout();
            ConfirmationPage confirmationPage = stepTwoPage.fillOutInformation("first", "last", "zip");
            CheckoutCompletePage finalConfirmationPage = confirmationPage.finish();
            Assert.assertTrue(finalConfirmationPage.isLoaded());
        }

        @AfterMethod
        public void teardown(ITestResult result) {
            ((JavascriptExecutor)driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
            driver.quit();
        }
    }
    ```
5. Run `mvn test` to ensure the following tests run correctly:
    * **`CheckoutFeatureTest`**
    * **`FullJourneyTest`**
    * **`LoginFeatureTest`**
6. Use `git stash` or `git commit` to discard or save your changes. Checkout the next branch to proceed to the next exercise
    ```
    git checkout 03_remove_duplication
    ```