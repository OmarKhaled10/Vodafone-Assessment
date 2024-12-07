package StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebScenario1 {
    WebDriver driver=new ChromeDriver(); // initiallizing a webdriver named driver

    static ExtentReports extent; // creating an extent report object called extent
    static ExtentTest test; // initiallizing an extenttest object called test

    String username = "Test User35"; // initiallizing a string for the username to be signned up with
    String password = "12345"; // initiallizing a string for the password to be signned up with

    static {
        ExtentSparkReporter spark = new ExtentSparkReporter("extentReport_WebScenario1.html");//setting the extent report elements
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Omar Khaled");
        extent.setSystemInfo("Environment", "Demo Testing");
        extent.setSystemInfo("Browser", "Chrome");
    }

    @Given("user is on demoblaze web home page")
    public void user_is_on_demoblaze_web_home_page() throws InterruptedException {
        test = extent.createTest("Navigate to Demoblaze Home Page");
        try {
            driver.get("https://www.demoblaze.com");// going to demoblaze homepage
            test.pass("User successfully navigated to the Demoblaze home page.");// pass case of this step
        } catch (Exception e) {
            test.fail("Failed to navigate to the Demoblaze home page: " + e.getMessage());// fail case of this step
        }
    }

    @And("the user presses sign up in homepage")
    public void the_user_presses_sign_up_in_homepage() throws InterruptedException {
        test = extent.createTest("Press Sign Up in Home Page");
        try {
            driver.findElement(By.id("signin2")).click();// find the sign up button using id
            Thread.sleep(3000);// waiting for 3 sec
            test.pass("Sign-up button clicked successfully.");// pass case of this step
        } catch (Exception e) {
            test.fail("Failed to click Sign-up button: " + e.getMessage());// fail case of this step
        }
    }

    @When("the user enters valid Credentials")
    public void the_user_enters_valid_credentials() throws InterruptedException {
        test = extent.createTest("Enter Valid Credentials");
        try {
            driver.findElement(By.id("sign-username")).sendKeys(username);//enter the username given in username variale
            driver.findElement(By.id("sign-password")).sendKeys(password);//enter the password given in password variale
            Thread.sleep(3000);// waiting for 3 sec
            test.pass("Credentials entered successfully.");// pass case of this step
        } catch (Exception e) {
            test.fail("Failed to enter credentials: " + e.getMessage());// fail case of this step
        }
    }

    @And("the user presses sign up from the pop up window")
    public void the_user_presses_sign_up_from_the_pop_up_window() throws InterruptedException {
        test = extent.createTest("Press Sign Up from Pop-up Window");
        try {
            driver.findElement(By.xpath("//button[text()='Sign up']")).click();// finding & pressing sign up button using xpath
            Thread.sleep(3000);// waiting for 3 sec
            test.pass("Sign-up button in pop-up clicked successfully.");// pass case of this step
        } catch (Exception e) {
            test.fail("Failed to click Sign-up button in pop-up: " + e.getMessage());// fail case of this step
        }
    }

    @Then("Sign Up successful appears")
    public void sign_up_successful_appears() throws InterruptedException {
        test = extent.createTest("Verify Sign-up Success Message");
        try {
            Alert alert = driver.switchTo().alert();// creating an Alert element called alert
            String alertMessage = alert.getText();//reading the message shown  in the alert
            if (alertMessage.contains("Sign up successful.")) {// if the message is the same as expected
                test.pass("Test Passed: " + alertMessage);// pass case of this step
            } else {
                test.fail("Test Failed: Alert message does not match. Message was: " + alertMessage);// fail case of this step
            }
            alert.accept();//close the alert
        } catch (Exception e) {
            test.fail("An exception occurred while verifying the alert: " + e.getMessage());// fail case of this step
        } finally {
            driver.quit();// close the browser
            extent.flush(); // Save the report
        }
    }
}