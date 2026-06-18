package Runner;
 
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
 
@CucumberOptions(
		features = "src/test/resources/feature/HRMS.feature",
		
		// Ensures Cucumber links both your Java Steps and your Hook classes
		glue = {"StepDef", "hooks"},

		tags = "@smoke",
		
		// Ensure you are refreshing this exact target report file name on your disk
		plugin = {"pretty", "html:target/HRMS2.html"}
		)
public class HRMSRunner extends AbstractTestNGCucumberTests {
}
