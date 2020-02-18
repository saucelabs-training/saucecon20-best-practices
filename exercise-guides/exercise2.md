# Exercise 2: Create Page Objects

## Part One: Create a  `LoginPage`
1. Checkout branch `02_create_page_objects`. 
3. In the **`pages`** package, navigate to the class called **`LoginPage`**
4. Create a `visit` method in the `LoginPage` object:
    ```
    public LoginPage visit() {
        driver.get("https://www.saucedemo.com");
        return this;
    }
    ```
5. Create the constructor for the page object:
    ```
    public LoginPage(WebDriver driver) {
        this.driver = driver;
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
    
## Part Two: Create `login()` Class Method
1. Open **`LoginPage`** and create a new class method called `login()`. This method will return a new page object that represents the next page in the journey (i.e. `InventoryPage`)
2. Add the **`LoginPage.visit()`** action in place of **`driver.navigate().to("https://www.saucedemo.com")`** The method will also expect some String data for the credentials (`username` and `password`) input. For Example:
    ```
    public InventoryPage login(String username, String password)
    {
    
    }
    ```
3. In `LoginFeatureTest` copy line 55-73 and paste it into `LoginPage`. The `login()` method should now look like the following:
    ```
    public InventoryPage login(String username, String password)
    {
        String userField = "[data-test='username']";
        String passField = "[data-test='password']";
        String loginBtn = "[value='LOGIN']";

        // send username keystrokes
        driver.findElement(By.cssSelector(userField))
        .sendKeys(username);

        // send password keystrokes
        driver.findElement(By.cssSelector(passField))
        .sendKeys(password);

        // click login button to submit keystrokes
        driver.findElement(By.cssSelector(loginBtn)).click();
        return new InventoryPage(driver);
    }
    ```
    
4. Add the following to the `LoginFeatureTest`, add the following to the `ShouldBeAbleToLogin` method:
    ```
    String username = "standard_user";
    String password = "secret_sauce";
    loginPage.login(username, password);
    ```
5. After the change, save and run `mvn test` to ensure the test still runs.
    ```
    mvn test -Dtest=LoginFeatureTest
    ```
6. Use `git stash` or `git commit` to discard or save your changes. Checkout the next branch to proceed to the next exercise
    ```
    git checkout 03_remove_duplication
    ```