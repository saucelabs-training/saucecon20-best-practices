# Optional Exercise: Cross-Browser Parallelization

## Part One: Configure `DataProvider`
2. In `BaseTest`, add the following `ThreadLocal` declarations:
    ```
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();
    ```
    
3. Add the following object array:
    ```
    @DataProvider(name = "hardCodedBrowsers", parallel = true)
        public static Object[][] sauceBrowserDataProvider(Method method) {
            return new Object[][]{
                    new Object[]{"MicrosoftEdge", "14.14393", "Windows 10"},
                    new Object[]{"firefox", "49.0", "Windows 10"},
                    new Object[]{"internet explorer", "11.0", "Windows 7"},
                    new Object[]{"safari", "10.0", "OS X 10.11"},
                    new Object[]{"chrome", "54.0", "OS X 10.10"},
                    new Object[]{"firefox", "latest-1", "Windows 7"},
            };
        }
    ```
4. Create a **`WebDriver`** public method to return the current WebDriver in the thread:
    ```
     public WebDriver getWebDriver() { return webDriver.get();}
    ```
5. Create a **`String`** public method to return the current SauceLabs session ID for the current thread:
    ```
    public String getSessionId() { return sessionId.get();}
    ```
6. Create a new public method that constructs a `RemoteWebDriver` instance that uses the capabilities defined by the browser, version and os parameters defined in the current thread.
    ```
     protected void createDriver(String browser, String version, String os, String methodName)
                throws MalformedURLException {
    ```
    * `String browser` Represents the browser type.
    * `String version` Represents the browser version.
    * `String os` Represents the operating system.
    * `methodName` Represents the name of the test case that we can use to identify the test on Sauce Labs.
    * `MalformedURLException` throws if an error occurs parsing the url
    
7. Set the `MutableCapabilities caps = new MutableCapabilities();` as follows:
    ```
    caps.setCapability("sauce:options", sauceOpts);
    caps.setCapability(CapabilityType.BROWSER_NAME, browser);
    caps.setCapability(CapabilityType.VERSION, version);
    caps.setCapability(CapabilityType.PLATFORM, os);
    caps.setCapability("name", methodName);
    ```
    
8. Launch the remote web browser and set it as the current thread, then set the current session ID.
    ```
    webDriver.set(new RemoteWebDriver(new URL(
        "https://ondemand.saucelabs.com/wd/hub"),caps));
    ```
    
    ```
    String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    sessionId.set(id);
    ```
## Part Three: Enable Each `@Test` Method to Accept `DataProvider`
1. In both `LogInFeatureTest` and `CheckoutFeatureTest` refactor each `@Test` method with the following parameters outlined in the `BaseTest` **`@DataProvider`** annotation. For example:
    ```
    @Test(dataProvider = "hardCodedBrowsers")
    public void SHouldBeAbleToLogin(String browser, String version, String os, Method method)
        throws MalformedURLException, InvalidElementStateException, UnexpectedException {
    ```
2. In each test method add the following code to instantiate a webdriver session from the current driver in the thread:
    ```
    this.createDriver(browser, version, os, method.getName());
    WebDriver driver = this.getWebDriver();
    ```
3. In `BaseTest` change the `"build"` tag again log in a different build:
    * Before
    ```
     sauceOpts.setCapability("build", "saucecon19-best-practices-v0.0.2");
    ```
    * After
    ```
     sauceOpts.setCapability("build", "saucecon19-best-practices-v0.0.3");
    ```
3. Save all and re-run your tests:
    ```
    mvn test
    ```