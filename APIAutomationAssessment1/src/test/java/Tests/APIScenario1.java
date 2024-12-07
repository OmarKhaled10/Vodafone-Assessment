package Tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class APIScenario1 {

    private static ExtentReports extent;// create an object of ExtentReports called extent
    private static ExtentTest test;// create an object of ExtentTest called test

    static {
        // Initialize the ExtentReports and reporter objects
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extent-report-scenario1.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test
    public void Sceanrio1() {
        // Start the test in ExtentReports
        test = extent.createTest("Sceanrio1", "API testing for authentication");

        // create a request body using json
        String requestBody = "{ \"username\" : \"admin\", \"password\" : \"password123\" }";

        // Log the request body in the report
        test.info("Request Body: " + requestBody);


        Response response = given()
                .header("Content-Type", "application/json")// set content type as json
                .body(requestBody)// set requestBody as our body
                .when()
                .post("https://restful-booker.herokuapp.com/auth");

        // check if the response status is 200
        if (response.statusCode() == 200) {
            test.pass("Received successful response with status code 200");
        } else {
            test.fail("Failed to receive response with status code 200. Actual code: " + response.statusCode());
        }

        // check if the token is generated
        String token = response.jsonPath().getString("token");

        // assert if the token isn't generated
        if (token != null) {
            test.pass("Token generated successfully: " + token);
        } else {
            test.fail("Token was not generated");
        }

        // Assert the status code and token presence
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(token, "Token is not generated");
    }

    // Ensure to flush the report after tests are done
    @org.testng.annotations.AfterClass
    public static void tearDown() {
        extent.flush();  // Write everything to the report
    }
}
