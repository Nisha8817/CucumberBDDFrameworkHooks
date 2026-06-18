package hooks;
 
import java.time.Duration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
 
public class Hooks {
 
    private static WebDriver driver;
 
    @Before
    public void setup() {
        System.out.println("Launching browser context dynamically via Hooks...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    
    public static WebDriver getDriver() {
        return driver;
    }
    
    @After
    public void tearDown(Scenario scenario) throws InterruptedException {
        // Triggers dynamically ONLY for negative or intentionally failed test cases
        if (scenario.isFailed() && driver != null) {
            try {
                System.out.println("Test failure intercepted! Synchronizing viewport layout...");
                
                // FIX: Force a small 1-second pause to let Chrome render the error layout 
                // and gain active window focus before extracting screen pixels
                Thread.sleep(1000);
                
                // Capture the active browser viewport state as a byte array stream
                final byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                
                // Embed the screenshot directly inside the target HRMS2.html report file
                scenario.attach(screenshotBytes, "image/png", "Failure_State_Snapshot");
                System.out.println("Failure screenshot successfully embedded into the HTML report.");
                
            } catch (Exception e) {
                System.err.println("Failed to capture screenshot during negative scenario: " + e.getMessage());
            }
        }
        
        System.out.println("Closing the active browser session context safely...");
        Thread.sleep(3000); // Gives time to see the window close down cleanly
        if (driver != null) {
            driver.quit();
        }
    }

}
