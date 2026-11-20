import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class SauceDemo {

    WebDriver driver;

    String usernames[] = {
        "standard_user",
        "locked_out_user",
        "problem_user",
        "performance_glitch_user",
        "error_user",
        "visual_user"
    };

    String password = "secret_sauce";

    @Test
    public void login() {

        driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(30)
        );

        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        for (int i = 0; i < usernames.length; i++) {

            System.out.println("Testing username: " + usernames[i]);

            // Enter username
            driver.findElement(By.id("user-name"))
                  .clear();

            driver.findElement(By.id("user-name"))
                  .sendKeys(usernames[i]);

            // Enter password
            driver.findElement(By.id("password"))
                  .clear();

            driver.findElement(By.id("password"))
                  .sendKeys(password);

            // Click login
            driver.findElement(By.id("login-button"))
                  .click();

            // Check whether login succeeded or failed
            wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("inventory"),
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-test='error']")
                )
            ));

            if (driver.getCurrentUrl().contains("inventory")) {

                System.out.println(
                    usernames[i] + " - Login Successful"
                );

                // Open menu
                wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("react-burger-menu-btn")
                )).click();

                // Logout
                wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("logout_sidebar_link")
                )).click();

            } else {

                WebElement errorMessage =
                    driver.findElement(By.cssSelector("[data-test='error']"));

                System.out.println(
                    usernames[i] + " - Login Failed: "
                    + errorMessage.getText()
                );

            }

            System.out.println("--------------------------");

        }

        driver.quit();
    }
}
