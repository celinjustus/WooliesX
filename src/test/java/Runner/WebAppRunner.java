package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "stepdefinitions",
        features = "src/test/resources/features/",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@SmokeTest"
)
public class WebAppRunner {

}
