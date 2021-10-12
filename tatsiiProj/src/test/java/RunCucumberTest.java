import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features = "src/test/resources",
        features = "src/test/resourcesPrivateBank",
        plugin = {"pretty"},
        //glue = "StepDefinitions"
        glue = "StepDefinitionsPrivateBank"
)
public class RunCucumberTest {
}
