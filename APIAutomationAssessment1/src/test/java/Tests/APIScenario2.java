package Tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class APIScenario2 {

    private static ExtentReports extent; // Create an object of ExtentReports called extent
    private static ExtentTest test; // Create an object of ExtentTest called test

    static {
        // Initialize the ExtentReports and reporter objects
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extent-report_Scenario2.html"); // Set the location and name for the report
        extent = new ExtentReports(); // Initialize the ExtentReports object
        extent.attachReporter(htmlReporter); // Attach the reporter to the ExtentReports object
    }

    @Test
    public void Scenario2() {
        // Start the test in ExtentReports
        test = extent.createTest("Scenario2", "API testing using RestAssured"); // Create a new test case for Scenario2

        String requestBody = "{ \"firstname\": \"Jim\", \"lastname\": \"Brown\", " +
                "\"totalprice\": 111, \"depositpaid\": true, " +
                "\"bookingdates\": { \"checkin\": \"2018-01-01\", \"checkout\": \"2019-01-01\" }, " +
                "\"additionalneeds\": \"Breakfast\" }"; // Create the request body as a JSON string

        Response response = given() // Start the RestAssured request
                .header("Content-Type", "application/json") // Set content type as JSON
                .body(requestBody) // Set the request body
                .when()
                .post("https://restful-booker.herokuapp.com/booking"); // Send a POST request to the specified URL

        // Log the response and test result
        if (response.statusCode() == 200) {
            test.pass("Successfully received response with status code 200"); // Log a success message if status code is 200
        } else {
            test.fail("Failed to receive response with status code 200. Actual code: " + response.statusCode()); // Log a failure message if status code is not 200
        }

        // Validating the Booking ID
        int bookingId = response.jsonPath().getInt("bookingid"); // Extract the booking ID from the response
        if (bookingId > 0) {
            test.pass("Booking ID is valid: " + bookingId); // Log a success message if booking ID is valid
        } else {
            test.fail("Booking ID is not valid"); // Log a failure message if booking ID is not valid
        }

        // Assert the status and booking ID
        Assert.assertEquals(response.statusCode(), 200); // Assert that the status code is 200
        Assert.assertTrue(bookingId > 0, "Booking ID is not valid"); // Assert that the booking ID is greater than 0
    }

    // Ensure to flush the report after tests are done
    @org.testng.annotations.AfterClass
    public static void tearDown() {
        extent.flush();  // Write everything to the report after test completion
    }
}
