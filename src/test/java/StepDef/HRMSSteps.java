package StepDef;
 
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert; // Standard TestNG Assertions engine
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import hooks.Hooks; 

public class HRMSSteps {

    private WebDriver driver;

    @Given("User is on HRMSLogin page")
    public void user_is_on_hrms_login_page() {
        System.out.println("Connecting to the browser instance opened by Hooks...");
        this.driver = Hooks.getDriver(); 
    }
 
    @When("User enters username as {string} and {string}")
    public void user_enters_username_as_and(String name, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        
        usernameField.sendKeys(name);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
 
    @Then("user should see the validation status as {string}")
    public void user_should_see_the_validation_status_as(String expectedResult) {
        String expectedURLComponent = "dashboard";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isLoginSuccessful = false;
        
        try {
            wait.until(ExpectedConditions.urlContains(expectedURLComponent));
            isLoginSuccessful = true;
            System.out.println("Actual System State: Successfully routed to Dashboard.");
        } catch (Exception e) {
            System.out.println("Actual System State: Redirection blocked by application.");
        }

        // Validates the actual outcome matches your expected result
        if (expectedResult.equalsIgnoreCase("Pass")) {
            Assert.assertTrue(isLoginSuccessful, "Validation Broken: Expected a successful login profile redirection, but the system stayed on the login screen.");
            System.out.println("Test Verification: SUCCESS (Expected Pass -> Got Pass)");
        } else {
            Assert.assertFalse(isLoginSuccessful, "Validation Broken: Expected the login to be blocked, but the application mistakenly redirected to the dashboard screen.");
            System.out.println("Test Verification: SUCCESS (Expected Fail -> Got Fail)");
        }
    }
}
