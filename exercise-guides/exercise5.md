# Exercise 5: Create a Base Page Object

1. Checkout the branch `05_create_base_page`.
2. In `src > test > java > pages`, create a new class called `BasePage`.
3. Remove the following duplication:
    ```
    //TODO notice the duplication
    private final WebDriver driver;
    ...
    //TODO notice the duplication
    this.driver = driver;
    ...
    driver.navigate().to("some-url");
    ...
    ```
In the following classes: 
    * `CheckoutCompletePage`
    * `ConfirmationPage`
    * `InventoryPage`
    * `LoginPage`
6. Save all and run the following command to ensure the build passes:
    ```
    mvn test
    ```
7. Use `git stash` or `git commit` to discard or save your changes. Checkout the next branch to proceed to the next exercise
    ```
    git checkout 06_test_parallelization
    ```