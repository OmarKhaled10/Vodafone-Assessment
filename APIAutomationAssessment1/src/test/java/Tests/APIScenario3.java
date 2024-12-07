package Tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class APIScenario3 {

    private static ExtentReports extent;
    private static ExtentTest test;

    static {
        // Initialize ExtentReports and attach an HTML reporter
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extent-report_Scenario3.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test
    public void Scenario3() {
        // Start the test in ExtentReports
        test = extent.createTest("Scenario3", "API testing using RestAssured for booking count");

        // Send GET request to retrieve booking information
        Response response = given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking");

        // Log the response status code in ExtentReports
        if (response.statusCode() == 200) {
            test.pass("Successfully received response with status code 200");
        } else {
            test.fail("Failed to receive response with status code 200. Actual code: " + response.statusCode());
        }

        // Validate that the booking count is greater than 0
        int bookingCount = response.jsonPath().getList("").size();
        if (bookingCount > 0) {
            test.pass("Successfully received booking list with " + bookingCount + " bookings");
        } else {
            test.fail("Booking list is empty");
        }

        // Assert the status code and booking count
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(bookingCount > 0, "Booking list is empty");
    }

    // Ensure to flush the report after tests are done
    @org.testng.annotations.AfterClass
    public static void tearDown() {
        extent.flush();  // Write everything to the report
    }
}
