# Exercise 3: Remove Duplication

1. Checkout branch `03_remove_duplication`.
2. Open **`CheckoutFeatureTest`** in `src > test > java > exercises`. There is duplicate code that exists in **`LoginFeatureTest`**, specifically the `setup()` and `teardown()` methods. 
4. Create a `BaseTest` class that executes these prerequisite and postrequisite test actions.
5. Migrate the `setup()` and `teardown()` methods from `LoginFeatureTest` and `CheckoutFeatureTest` and place them into `BaseTest`
6. Back in `LoginFeatureTest` and `CheckoutFeatureTest`, extend `BaseTest` like so:
    ```
    public class LoginFeatureTest extends BaseTest {
    ...
    }
    ```
7. Also in `LoginFeatureTest` and `CheckoutFeatureTest` remove the following line:
    ```
    protected WebDriver driver
    ```
    as it's now redundant.
7. Delete `FullJourneyTest` and test the changes:
    ```
    mvn test
    ```
8. If both tests run fine, use `git commit` or `git stash`, then checkout the next branch to proceed to the next exercise:

## Part Two: Cross Browser Testing
Next we will update our `capabilities` in `BaseTest` to test using the latest version of Google Chrome, which means we have to modify our code a bit to comply with the new `W3C` protocol.
1. Add a second `MutableCapabilities` object in the `setup()` method with the following details:
    ```
    MutableCapabilities sauceOpts = new MutableCapabilities();
    sauceOpts.setCapability("username", sauceUsername);
    sauceOpts.setCapability("accessKey", sauceAccessKey);
    sauceOpts.setCapability("name", method.getName());
    sauceOpts.setCapability("seleniumVersion", "3.141.59");
    sauceOpts.setCapability("build", "saucecon19-best-practices");
    sauceOpts.setCapability("tags", "['best-practices', 'saucecon19']");
    ```
2. Next, modify the existing `MutableCapabilities` object called `capabilities with the following:
    ```
    MutableCapabilities capabilities = new MutableCapabilities();
    capabilities.setCapability(ChromeOptions.CAPABILITY,  chromeOpts);
    capabilities.setCapability("sauce:options", sauceOpts);
    capabilities.setCapability("browserName", "googlechrome");
    capabilities.setCapability("browserVersion", "71.0");
    capabilities.setCapability("platformName", "windows 10");
    ```
3. Run a test to ensure the build passes
    ```
    mvn test
    ```
4. Use `git stash` or `git commit` to discard or save your changes. Checkout the next branch to proceed to the next exercise
    ```
    git checkout 04_configure_atomic_tests
    ```