package StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebScenario2 {
    private WebDriver driver; // initializing a WebDriver named driver
    private static ExtentReports extent; // creating an ExtentReports object for reporting
    private static ExtentTest test; // initializing an ExtentTest object for test cases
    String username = "Test User36"; // initiallizing a string for the username to be logged in with
    String password = "12345"; // initiallizing a string for the password to be logged in with

    // Initialize Extent Reports
    static {
        ExtentSparkReporter spark = new ExtentSparkReporter("extentReport_WebScenario2.html"); // setting the extent report file
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Your Name"); // adding system info
        extent.setSystemInfo("Environment", "Demo Testing");
        extent.setSystemInfo("Browser", "Chrome");
    }

    @Before
    public void setUp() {
        if (driver == null) {
            driver = new ChromeDriver(); // initializing the ChromeDriver before each test
        }
    }

    @After
    public void tearDown() {
        driver.quit(); // closing the browser after each test
        extent.flush(); // saving the extent report
    }

    @Given("the user is on demoblaze homepage")
    public void the_user_is_on_demoblaze_homepage() {
        test = extent.createTest("Navigate to Demoblaze Homepage");
        try {
            driver.get("https://www.demoblaze.com"); // navigating to the homepage
            Thread.sleep(3000); // waiting for the page to load
            test.pass("User successfully navigated to the Demoblaze homepage."); // pass case
        } catch (Exception e) {
            test.fail("Failed to navigate to the homepage: " + e.getMessage()); // fail case
        }
    }

    @And("the user presses log in from header")
    public void the_user_presses_log_in_from_header() {
        test = extent.createTest("Press Log In From Header");
        try {
            driver.findElement(By.id("login2")).click(); // clicking the log-in button by its ID
            Thread.sleep(3000); // waiting for the action
            test.pass("Log in button clicked successfully."); // pass case
        } catch (Exception e) {
            test.fail("Failed to click log in button: " + e.getMessage()); // fail case
        }
    }

    @When("the user enters an existing username and password")
    public void the_user_enters_an_existing_username_and_password() {
        test = extent.createTest("Enter Existing Credentials");
        try {
            driver.findElement(By.id("loginusername")).sendKeys(username); // entering the username
            driver.findElement(By.id("loginpassword")).sendKeys(password); // entering the password
            Thread.sleep(3000); // waiting for the action
            test.pass("Credentials entered successfully."); // pass case
        } catch (Exception e) {
            test.fail("Failed to enter credentials: " + e.getMessage()); // fail case
        }
    }

    @And("the user clicks on the login button")
    public void the_user_clicks_on_the_login_button() {
        test = extent.createTest("Click Login Button");
        try {
            driver.findElement(By.xpath("//button[text()='Log in']")).click(); // clicking the log-in button using XPath
            Thread.sleep(3000); // waiting for the action
            test.pass("Log in button clicked successfully."); // pass case
        } catch (Exception e) {
            test.fail("Failed to click login button: " + e.getMessage()); // fail case
        }
    }

    @Then("the user is redirected to the home page")
    public void the_user_is_redirected_to_the_home_page() {
        test = extent.createTest("Redirect to Home Page");
        try {
            if (driver.getPageSource().contains("Welcome")) { // checking if the page contains the text "Welcome"
                test.pass("User successfully redirected to the homepage."); // pass case
            } else {
                test.fail("Redirection to homepage failed."); // fail case
            }
            Thread.sleep(3000); // waiting for the action
        } catch (Exception e) {
            test.fail("An exception occurred: " + e.getMessage()); // fail case
        }
    }

    @When("the user choose laptops from categories")
    public void the_user_choose_laptops_from_categories() {
        test = extent.createTest("Choose Laptops Category");
        try {
            driver.findElement(By.xpath("//a[text()='Laptops']")).click(); // clicking the laptops category
            Thread.sleep(3000); // waiting for the action
            test.pass("Laptops category selected successfully."); // pass case
        } catch (Exception e) {
            test.fail("Failed to select laptops category: " + e.getMessage()); // fail case
        }
    }
    @And("the user chooses a product")
    public void the_user_chooses_a_product() {
        // Creating a test case for choosing a product
        test = extent.createTest("Choose a Product");
        try {
            // Clicking on the "Sony vaio i5" product using its XPath
            driver.findElement(By.xpath("//a[text()='Sony vaio i5']")).click();
            Thread.sleep(3000); // Waiting for 3 seconds to ensure product is selected
            test.pass("Product 'Sony vaio i5' selected successfully."); // If successful, log pass
        } catch (Exception e) {
            test.fail("Failed to select product: " + e.getMessage()); // Log failure if an error occurs
        }
    }

    @And("the user presses add to cart")
    public void the_user_presses_add_to_cart() {
        // Creating a test case for adding the product to the cart
        test = extent.createTest("Add Product to Cart");
        try {
            // Clicking on the "Add to cart" button for the selected product
            driver.findElement(By.xpath("//a[text()='Add to cart']")).click();
            Thread.sleep(3000); // Waiting for 3 seconds to ensure the product is added to the cart
            test.pass("Product added to cart successfully."); // Log pass if successful
        } catch (Exception e) {
            test.fail("Failed to add product to cart: " + e.getMessage()); // Log failure if an error occurs
        }
    }

    @Then("product added pop up appears")
    public void product_added_pop_up_appears() {
        // Creating a test case for verifying the "Add to Cart" pop-up
        test = extent.createTest("Verify Add to Cart Pop-up");
        try {
            // Switching to the alert and checking the pop-up message
            Alert alert = driver.switchTo().alert();
            String alertMessage = alert.getText();
            if (alertMessage.contains("Product added.")) {
                test.pass("Add to cart pop-up appeared with correct message."); // Log pass if message is correct
            } else {
                test.fail("Add to cart pop-up message did not match."); // Log failure if message is incorrect
            }
            alert.accept(); // Accepting the alert to close the pop-up
            Thread.sleep(3000); // Waiting for 3 seconds to ensure pop-up is handled
        } catch (Exception e) {
            test.fail("An exception occurred while verifying pop-up: " + e.getMessage()); // Log failure if an error occurs
        }
    }

    @When("the user is on demoblaze Home")
    public void the_user_is_on_demoblaze_home() {
        // Creating a test case for navigating to the Demoblaze home page
        test = extent.createTest("Navigate to Demoblaze Home");
        try {
            // Navigating to the Demoblaze homepage
            driver.navigate().to("https://www.demoblaze.com");
            Thread.sleep(3000); // Waiting for 3 seconds to ensure the page loads
            test.pass("User successfully navigated to Demoblaze Home."); // Log pass if successful
        } catch (Exception e) {
            test.fail("Failed to navigate to Demoblaze Home: " + e.getMessage()); // Log failure if an error occurs
        }
    }

    @And("the user chooses an item")
    public void the_user_chooses_an_item() {
        // Creating a test case for choosing an item
        test = extent.createTest("Choose an Item");
        try {
            // Clicking on the "Samsung galaxy s6" item using its XPath
            driver.findElement(By.xpath("//a[text()='Samsung galaxy s6']")).click();
            Thread.sleep(3000); // Waiting for 3 seconds to ensure item is selected
            test.pass("Item 'Samsung galaxy s6' selected successfully."); // Log pass if successful
        } catch (Exception e) {
            test.fail("Failed to select item: " + e.getMessage()); // Log failure if an error occurs
        }
    }

    @And("presses add to cart")
    public void presses_add_to_cart() {
        // Creating a test case for adding the item to the cart
        test = extent.createTest("Press Add to Cart");
        try {
            // Clicking on the "Add to cart" button for the selected item
            driver.findElement(By.xpath("//a[text()='Add to cart']")).click();
            Thread.sleep(3000); // Waiting for 3 seconds to ensure the item is added to the cart
            test.pass("Item added to cart successfully."); // Log pass if successful
        } catch (Exception e) {
            test.fail("Failed to add item to cart: " + e.getMessage()); // Log failure if an error occurs
        }
    }

    @Then("product added pop up page appears")
    public void product_added_pop_up_page_appears() {
        // Creating a test case for verifying the "Add to Cart" pop-up for the item
        test = extent.createTest("Verify Add to Cart Pop-up for Item");
        try {
            // Switching to the alert and checking the pop-up message
            Alert alert = driver.switchTo().alert();
            String alertMessage = alert.getText();
            if (alertMessage.contains("Product added.")) {
                test.pass("Pop-up appeared with correct message."); // Log pass if message is correct
            } else {
                test.fail("Pop-up message did not match."); // Log failure if message is incorrect
            }
            alert.accept(); // Accepting the alert to close the pop-up
            Thread.sleep(3000); // Waiting for 3 seconds to ensure pop-up is handled
        } catch (Exception e) {
            test.fail("An exception occurred: " + e.getMessage()); // Log failure if an error occurs
        }
    }

    @When("the user presses on cart")
    public void the_user_presses_on_cart() {
        // Creating a test case for pressing on the cart
        test = extent.createTest("Press on Cart");
        try {
            // Clicking on the "Cart" link by its ID
            driver.findElement(By.id("cartur")).click();
            Thread.sleep(3000); // Waiting for 3 seconds to navigate to the cart
            test.pass("Navigated to cart successfully."); // Log pass if successful
        } catch (Exception e) {
            test.fail("Failed to navigate to cart: " + e.getMessage()); // Log failure if an error occurs
        }
    }

    @Then("The items are added correctly to cart")
    public void the_items_are_added_correctly_to_cart() {
        // Creating a test case for verifying the items in the cart
        test = extent.createTest("Verify Items in Cart");
        try {
            // Checking if both items are present in the cart by verifying their names
            if (driver.getPageSource().contains("Sony vaio i5") && driver.getPageSource().contains("Samsung galaxy s6")) {
                test.pass("Both items are present in the cart."); // Log pass if both items are found
            } else {
                test.fail("One or more items are missing from the cart."); // Log failure if any item is missing
            }
            Thread.sleep(3000); // Waiting for 3 seconds to ensure cart details are loaded
        } catch (Exception e) {
            test.fail("An exception occurred: " + e.getMessage()); // Log failure if an error occurs
        }
    }

    @And("Total price is true")
    public void total_price_is_true() {
        // Creating a test case for verifying the total price in the cart
        test = extent.createTest("Verify Total Price");
        try {
            // Finding and parsing the price of "Sony vaio i5" from the cart
            WebElement priceElement = driver.findElement(By.xpath("//td[text()='Sony vaio i5']/following-sibling::td[1]"));
            int price = Integer.parseInt(priceElement.getText());

            // Finding and parsing the price of "Samsung galaxy s6" from the cart
            WebElement priceElement1 = driver.findElement(By.xpath("//td[text()='Samsung galaxy s6']/following-sibling::td[1]"));
            int price1 = Integer.parseInt(priceElement1.getText());

            // Finding the total price element and parsing its value
            WebElement totalPriceElement = driver.findElement(By.xpath("//h3[@id='totalp']"));
            int totalPrice = Integer.parseInt(totalPriceElement.getText());

            // Verifying if the total price is the sum of both individual prices
            if (totalPrice == (price + price1)) {
                test.pass("Total price is calculated correctly: " + totalPrice); // Log pass if total price is correct
            } else {
                test.fail("Total price mismatch. Expected: " + (price + price1) + ", Found: " + totalPrice); // Log failure if mismatch
            }
        } catch (Exception e) {
            test.fail("An exception occurred while verifying total price: " + e.getMessage()); // Log failure if an error occurs
        }
    }

    @When("the user presses place order")
    public void the_user_presses_place_order() {
        test = extent.createTest("Press Place Order");
        try {
            driver.findElement(By.xpath("//button[text()='Place Order']")).click(); // Clicking the "Place Order" button
            Thread.sleep(3000); // Waiting for 3 seconds
            test.pass("Place Order button clicked successfully."); // Pass case for this step
        } catch (Exception e) {
            test.fail("Failed to click Place Order: " + e.getMessage()); // Fail case for this step
        }
    }

    @And("fills the needed info")
    public void fills_the_needed_info() {
        test = extent.createTest("Fill Order Information");
        try {
            driver.findElement(By.id("name")).sendKeys("xyz"); // Filling in the name field
            driver.findElement(By.id("country")).sendKeys("Egypt"); // Filling in the country field
            driver.findElement(By.id("city")).sendKeys("Giza"); // Filling in the city field
            driver.findElement(By.id("card")).sendKeys("1234"); // Filling in the credit card field
            driver.findElement(By.id("month")).sendKeys("11"); // Filling in the expiration month
            driver.findElement(By.id("year")).sendKeys("25"); // Filling in the expiration year
            Thread.sleep(3000); // Waiting for 3 seconds
            test.pass("Order information filled successfully."); // Pass case for this step
        } catch (Exception e) {
            test.fail("Failed to fill order information: " + e.getMessage()); // Fail case for this step
        }
    }

    @And("presses Purchase")
    public void presses_purchase() {
        test = extent.createTest("Press Purchase");
        try {
            driver.findElement(By.xpath("//button[text()='Purchase']")).click(); // Clicking the "Purchase" button
            Thread.sleep(3000); // Waiting for 3 seconds
            test.pass("Purchase button clicked successfully."); // Pass case for this step
        } catch (Exception e) {
            test.fail("Failed to click Purchase: " + e.getMessage()); // Fail case for this step
        }
    }

    @Then("Thank you for purchase pop up appears")
    public void thank_you_for_purchase_pop_up_appears() {
        test = extent.createTest("Verify Purchase Confirmation");
        try {
            if (driver.getPageSource().contains("Thank you for your purchase!")) { // Checking if the confirmation message is displayed
                test.pass("Purchase confirmation pop-up appeared successfully."); // Pass case for this step
            } else {
                test.fail("Purchase confirmation pop-up did not appear."); // Fail case for this step
            }
            Thread.sleep(3000); // Waiting for 3 seconds
        } catch (Exception e) {
            test.fail("An exception occurred: " + e.getMessage()); // Fail case for exception handling
        }
    }
}





/*package StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebScenario2 {
    private WebDriver driver;//create a webdriver called driver
    private static ExtentReports extent;
    private static ExtentTest test;
    String totalPriceText;

    // Initialize Extent Reports
    static {
        ExtentSparkReporter spark = new ExtentSparkReporter("extentReport_WebScenario2.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Your Name");
        extent.setSystemInfo("Environment", "Demo Testing");
        extent.setSystemInfo("Browser", "Chrome");
    }

    @Before
    public void setUp() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
    }

    @After
    public void tearDown() {
        driver.quit();
        extent.flush(); // Save the report after the test suite
    }

    @Given("the user is on demoblaze homepage")
    public void the_user_is_on_demoblaze_homepage() {
        test = extent.createTest("Navigate to Demoblaze Homepage");
        try {
            driver.get("https://www.demoblaze.com");
            Thread.sleep(3000);
            test.pass("User successfully navigated to the Demoblaze homepage.");
        } catch (Exception e) {
            test.fail("Failed to navigate to the homepage: " + e.getMessage());
        }
    }

    @And("the user presses log in from header")
    public void the_user_presses_log_in_from_header() {
        test = extent.createTest("Press Log In From Header");
        try {
            driver.findElement(By.id("login2")).click();
            Thread.sleep(3000);
            test.pass("Log in button clicked successfully.");
        } catch (Exception e) {
            test.fail("Failed to click log in button: " + e.getMessage());
        }
    }

    @When("the user enters an existing username and password")
    public void the_user_enters_an_existing_username_and_password() {
        test = extent.createTest("Enter Existing Credentials");
        try {
            driver.findElement(By.id("loginusername")).sendKeys("Test User33");
            driver.findElement(By.id("loginpassword")).sendKeys("12345");
            Thread.sleep(3000);
            test.pass("Credentials entered successfully.");
        } catch (Exception e) {
            test.fail("Failed to enter credentials: " + e.getMessage());
        }
    }

    @And("the user clicks on the login button")
    public void the_user_clicks_on_the_login_button() {
        test = extent.createTest("Click Login Button");
        try {
            driver.findElement(By.xpath("//button[text()='Log in']")).click();
            Thread.sleep(3000);
            test.pass("Log in button clicked successfully.");
        } catch (Exception e) {
            test.fail("Failed to click login button: " + e.getMessage());
        }
    }

    @Then("the user is redirected to the home page")
    public void the_user_is_redirected_to_the_home_page() {
        test = extent.createTest("Redirect to Home Page");
        try {
            if (driver.getPageSource().contains("Welcome")) {
                test.pass("User successfully redirected to the homepage.");
            } else {
                test.fail("Redirection to homepage failed.");
            }
            Thread.sleep(3000);
        } catch (Exception e) {
            test.fail("An exception occurred: " + e.getMessage());
        }
    }

    @When("the user choose laptops from categories")
    public void the_user_choose_laptops_from_categories() {
        test = extent.createTest("Choose Laptops Category");
        try {
            driver.findElement(By.xpath("//a[text()='Laptops']")).click();
            Thread.sleep(3000);
            test.pass("Laptops category selected successfully.");
        } catch (Exception e) {
            test.fail("Failed to select laptops category: " + e.getMessage());
        }
    }

    @And("the user chooses a product")
    public void the_user_chooses_a_product() {
        test = extent.createTest("Choose a Product");
        try {
            driver.findElement(By.xpath("//a[text()='Sony vaio i5']")).click();
            Thread.sleep(3000);
            test.pass("Product 'Sony vaio i5' selected successfully.");
        } catch (Exception e) {
            test.fail("Failed to select product: " + e.getMessage());
        }
    }

    @And("the user presses add to cart")
    public void the_user_presses_add_to_cart() {
        test = extent.createTest("Add Product to Cart");
        try {
            driver.findElement(By.xpath("//a[text()='Add to cart']")).click();
            Thread.sleep(3000);
            test.pass("Product added to cart successfully.");
        } catch (Exception e) {
            test.fail("Failed to add product to cart: " + e.getMessage());
        }
    }

    @Then("product added pop up appears")
    public void product_added_pop_up_appears() {
        test = extent.createTest("Verify Add to Cart Pop-up");
        try {
            Alert alert = driver.switchTo().alert();
            String alertMessage = alert.getText();
            if (alertMessage.contains("Product added.")) {
                test.pass("Add to cart pop-up appeared with correct message.");
            } else {
                test.fail("Add to cart pop-up message did not match.");
            }
            alert.accept();
            Thread.sleep(3000);
        } catch (Exception e) {
            test.fail("An exception occurred while verifying pop-up: " + e.getMessage());
        }
    }
    @When("the user is on demoblaze Home")
    public void the_user_is_on_demoblaze_home() {
        test = extent.createTest("Navigate to Demoblaze Home");
        try {
            driver.navigate().to("https://www.demoblaze.com");
            Thread.sleep(3000);
            test.pass("User successfully navigated to Demoblaze Home.");
        } catch (Exception e) {
            test.fail("Failed to navigate to Demoblaze Home: " + e.getMessage());
        }
    }

    @And("the user chooses an item")
    public void the_user_chooses_an_item() {
        test = extent.createTest("Choose an Item");
        try {
            driver.findElement(By.xpath("//a[text()='Samsung galaxy s6']")).click();
            Thread.sleep(3000);
            test.pass("Item 'Samsung galaxy s6' selected successfully.");
        } catch (Exception e) {
            test.fail("Failed to select item: " + e.getMessage());
        }
    }

    @And("presses add to cart")
    public void presses_add_to_cart() {
        test = extent.createTest("Press Add to Cart");
        try {
            driver.findElement(By.xpath("//a[text()='Add to cart']")).click();
            Thread.sleep(3000);
            test.pass("Item added to cart successfully.");
        } catch (Exception e) {
            test.fail("Failed to add item to cart: " + e.getMessage());
        }
    }

    @Then("product added pop up page appears")
    public void product_added_pop_up_page_appears() {
        test = extent.createTest("Verify Add to Cart Pop-up for Item");
        try {
            Alert alert = driver.switchTo().alert();
            String alertMessage = alert.getText();
            if (alertMessage.contains("Product added.")) {
                test.pass("Pop-up appeared with correct message.");
            } else {
                test.fail("Pop-up message did not match.");
            }
            alert.accept();
            Thread.sleep(3000);
        } catch (Exception e) {
            test.fail("An exception occurred: " + e.getMessage());
        }
    }

    @When("the user presses on cart")
    public void the_user_presses_on_cart() {
        test = extent.createTest("Press on Cart");
        try {
            driver.findElement(By.id("cartur")).click();
            Thread.sleep(3000);
            test.pass("Navigated to cart successfully.");
        } catch (Exception e) {
            test.fail("Failed to navigate to cart: " + e.getMessage());
        }
    }

    @Then("The items are added correctly to cart")
    public void the_items_are_added_correctly_to_cart() {
        test = extent.createTest("Verify Items in Cart");
        try {
            if (driver.getPageSource().contains("Sony vaio i5") && driver.getPageSource().contains("Samsung galaxy s6")) {
                test.pass("Both items are present in the cart.");
            } else {
                test.fail("One or more items are missing from the cart.");
            }
            Thread.sleep(3000);
        } catch (Exception e) {
            test.fail("An exception occurred: " + e.getMessage());
        }
    }

    @And("Total price is true")
    public void total_price_is_true() {
        test = extent.createTest("Verify Total Price");
        try {
            WebElement priceElement = driver.findElement(By.xpath("//td[text()='Sony vaio i5']/following-sibling::td[1]"));
            int price = Integer.parseInt(priceElement.getText());

            WebElement priceElement1 = driver.findElement(By.xpath("//td[text()='Samsung galaxy s6']/following-sibling::td[1]"));
            int price1 = Integer.parseInt(priceElement1.getText());

            WebElement totalPriceElement = driver.findElement(By.xpath("//h3[@id='totalp']"));
            int totalPrice = Integer.parseInt(totalPriceElement.getText());

            if (totalPrice == (price + price1)) {
                test.pass("Total price is calculated correctly: " + totalPrice);
            } else {
                test.fail("Total price mismatch. Expected: " + (price + price1) + ", Found: " + totalPrice);
            }
        } catch (Exception e) {
            test.fail("An exception occurred while verifying total price: " + e.getMessage());
        }
    }

    @When("the user presses place order")
    public void the_user_presses_place_order() {
        test = extent.createTest("Press Place Order");
        try {
            driver.findElement(By.xpath("//button[text()='Place Order']")).click();
            Thread.sleep(3000);
            test.pass("Place Order button clicked successfully.");
        } catch (Exception e) {
            test.fail("Failed to click Place Order: " + e.getMessage());
        }
    }

    @And("fills the needed info")
    public void fills_the_needed_info() {
        test = extent.createTest("Fill Order Information");
        try {
            driver.findElement(By.id("name")).sendKeys("xyz");
            driver.findElement(By.id("country")).sendKeys("Egypt");
            driver.findElement(By.id("city")).sendKeys("Giza");
            driver.findElement(By.id("card")).sendKeys("1234");
            driver.findElement(By.id("month")).sendKeys("11");
            driver.findElement(By.id("year")).sendKeys("25");
            Thread.sleep(3000);
            test.pass("Order information filled successfully.");
        } catch (Exception e) {
            test.fail("Failed to fill order information: " + e.getMessage());
        }
    }

    @And("presses Purchase")
    public void presses_purchase() {
        test = extent.createTest("Press Purchase");
        try {
            driver.findElement(By.xpath("//button[text()='Purchase']")).click();
            Thread.sleep(3000);
            test.pass("Purchase button clicked successfully.");
        } catch (Exception e) {
            test.fail("Failed to click Purchase: " + e.getMessage());
        }
    }

    @Then("Thank you for purchase pop up appears")
    public void thank_you_for_purchase_pop_up_appears() {
        test = extent.createTest("Verify Purchase Confirmation");
        try {
            if (driver.getPageSource().contains("Thank you for your purchase!")) {
                test.pass("Purchase confirmation pop-up appeared successfully.");
            } else {
                test.fail("Purchase confirmation pop-up did not appear.");
            }
            Thread.sleep(3000);
        } catch (Exception e) {
            test.fail("An exception occurred: " + e.getMessage());
        }
    }
}
*/
