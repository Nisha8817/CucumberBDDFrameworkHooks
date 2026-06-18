package Runner;
 
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
 
@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/feature/Tester.feature",
		glue="StepDef", 
		monochrome=true,
		// Correctly maps tag logical operations parameters
		tags = "@regression or @smoke"
		)
public class TesterRunner {
}
