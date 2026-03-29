package Testing.pages;

import org.openqa.selenium.By;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage {

    AndroidDriver driver;

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void login(String email, String password) {

        driver.findElement(By.id("com.sportstechbrands.sportstechlive:id/etEmail"))
                .sendKeys(email);

        driver.findElement(By.id("com.sportstechbrands.sportstechlive:id/etPassword"))
                .sendKeys(password);

        driver.findElement(By.xpath("//android.widget.Button"))
                .click();
    }
}