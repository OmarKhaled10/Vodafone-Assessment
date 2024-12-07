package StepDefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


//run using cucumber
@RunWith(Cucumber.class)
// read the feature file written in BDD format and glue it to its opposite jave file in StepDefinitions package
@CucumberOptions(features = "src/test/resources/features" , glue = {"StepDefinitions"})
public class TestRunner {
}