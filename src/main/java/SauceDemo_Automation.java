import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceDemo_Automation {
WebDriver driver;
@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");

		
	}
	
	@Test
	public void validLogin() {
	driver.findElement(By.id("user-name")).sendKeys("standard_user");
			driver.findElement(By.id("password")).sendKeys("secret_sauce");
			driver.findElement(By.id("login-button")).click();
			
			String actualTitle = driver.findElement(By.cssSelector("[data-test='title']")).getText();

		    Assert.assertEquals(actualTitle, "Products",
		            "Login failed: Products page was not displayed");
	}
	
	@Test
	public void invalidUsername() {
		driver.findElement(By.id("user-name")).sendKeys("wrong_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		String actualError = driver.findElement(By.cssSelector("[data-test='error']")).getText();
		Assert.assertEquals(actualError, "Epic sadface: Username and password do not match any user in this service",
	            "Invalid username error was not displayed");
	}
	
	@Test
	public void invalidPassword() {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauceee");
		driver.findElement(By.id("login-button")).click();
		String actualError2 = driver.findElement(By.cssSelector("[data-test='error']")).getText();
		Assert.assertEquals(actualError2, "Epic sadface: Username and password do not match any user in this service", 
				"Invalid password error was not displayed");
	}
	
	@Test
	public void emptyFieldLogin() {
		driver.findElement(By.id("user-name")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.id("login-button")).click();
		String actualError3 = driver.findElement(By.cssSelector("[data-test='error']")).getText();
		Assert.assertEquals(actualError3, "Epic sadface: Username is required", "Required field validation was not displayed");
	}
	
	@AfterMethod
	public void tearDown() {
		 if (driver != null) {
			 driver.quit();	 
		 }
		
	}
	}
		
