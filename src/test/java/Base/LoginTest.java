package Base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;

class LoginTest {

    public AndroidDriver driver;
    public WebDriverWait wait;

    @BeforeClass
    public void setup() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:deviceName", "Android");
        caps.setCapability("appium:appPackage", "com.sportstechbrands.sportstechlive");
        caps.setCapability("appium:appActivity", "com.sportstechbrands.sportstechlive.activities.newUiDesignRewamp.Welcome");
        caps.setCapability("appium:appWaitActivity", "com.sportstechbrands.sportstechlive.*");
        caps.setCapability("appium:noReset", true);
        caps.setCapability("appium:ignoreHiddenApiPolicyError", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void loginTest() throws InterruptedException {
        System.out.println("--- TEST STARTED ---");

        // 1. Navigate to Email Login Screen
        wait.until(ExpectedConditions.elementToBeClickable(By.id("com.sportstechbrands.sportstechlive:id/login"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("com.sportstechbrands.sportstechlive:id/ll_email_signin"))).click();

        // 2. Enter Credentials (with focus clicks to trigger app validation)
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.sportstechbrands.sportstechlive:id/etEmail")));
        emailField.click();
        emailField.sendKeys("protest@gmail.com");

        WebElement passwordField = driver.findElement(By.id("com.sportstechbrands.sportstechlive:id/etPassword"));
        passwordField.click();
        passwordField.sendKeys("Test@123");

        // 3. Close Keyboard and wait for Button to "Enable"
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
            System.out.println("Keyboard note: already hidden.");
        }
        Thread.sleep(2000); // Small pause for UI sync

        // 4. Click the PINK Login Button using the verified XPath
        WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.widget.Button[@text='LOGIN']")
        ));
        loginBtn.click();
        System.out.println("LOGIN CLICKED");


// 5. VERIFICATION: Successfully reached the Dashboard
        try {
            System.out.println("Verifying Home Page landing...");

            // We check for the App Logo or the Welcome Greeting "Hey Prot!"
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.id("com.sportstechbrands.sportstechlive:id/ivSportsTech")),
                    ExpectedConditions.visibilityOfElementLocated(By.id("com.sportstechbrands.sportstechlive:id/tv_user_name"))
            ));

            String welcomeText = driver.findElement(By.id("com.sportstechbrands.sportstechlive:id/tv_user_name")).getText();
            System.out.println("VERIFIED: Successfully landed on Home Page! Greeting: " + welcomeText);
        } catch (Exception e) {
            System.out.println("Verification Failed: Home screen elements not found.");
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}