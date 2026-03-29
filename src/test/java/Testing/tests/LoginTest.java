package Testing.tests;

import Testing.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void loginWithEmail() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        // 🔥 Wait for app to load
        try {
            Thread.sleep(5000);
        } catch (Exception e) {}

        // 🔥 Step 1: Click LOGIN button (if present)
        try {
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("com.sportstechbrands.sportstechlive:id/login")
            ));
            loginBtn.click();
            System.out.println("Clicked LOGIN button");
        } catch (Exception e) {
            System.out.println("Already on login screen");
        }

        // 🔥 Step 2: Click EMAIL tab (VERY IMPORTANT)
        WebElement emailTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text='EMAIL']")
        ));
        emailTab.click();
        System.out.println("EMAIL tab selected");

        // ✅ Test Data
        String emailValue = "protest@gmail.com";
        String passwordValue = "Test@123";

        // 🔥 Step 3: Enter Email
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.sportstechbrands.sportstechlive:id/etEmail")
        ));
        email.click();
        email.clear();
        email.sendKeys(emailValue);

        // 🔥 Step 4: Enter Password
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.sportstechbrands.sportstechlive:id/etPassword")
        ));
        password.click();
        password.clear();
        password.sendKeys(passwordValue);

        // 🔥 Step 5: Click LOGIN
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("com.sportstechbrands.sportstechlive:id/btnLogin")
        ));
        loginButton.click();

        System.out.println("✅ Email login completed");
    }
}