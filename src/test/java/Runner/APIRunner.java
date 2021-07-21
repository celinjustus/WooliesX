package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "stepdefinitions",
        features = "src/test/resources/features/",
        plugin = { "html:target/cucumber-reports.html"},
        tags="@APITest"
)
public class APIRunner {
}
