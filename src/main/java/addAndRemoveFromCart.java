import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class addAndRemoveFromCart {
WebDriver driver;
String username = "standard_user";
String password = "secret_sauce";

@BeforeMethod
public void openingBrowser() {
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.saucedemo.com/");
}
//Adding products to cart
@Test
public void addToCart() {
	driver.findElement(By.id("user-name")).sendKeys(username);
	driver.findElement(By.id("password")).sendKeys(password);
	driver.findElement(By.id("login-button")).click();
	
	
	// Wait for 5seconds
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
		
	
	String[] selectedProducts = {
			"sauce-labs-backpack", 
			"sauce-labs-bike-light", 
			"sauce-labs-fleece-jacket", 
			"sauce-labs-bolt-t-shirt"
	};
	
	
	
	
	//Loop through selected products
	for (String products : selectedProducts) {
		
		w.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-" + products)
            )).click();
		System.out.println("Added to cart: " + products);
		
		

	}
	String productNumber = driver.findElement(By.className("shopping_cart_link")).getText();
	System.out.println("Total number of products in cart: " + productNumber);
	
	driver.findElement(By.className("shopping_cart_link")).click();
	
	//Wait for cart page
	w.until(ExpectedConditions.elementToBeClickable(By.id("remove-sauce-labs-bike-light"))).click();
	driver.findElement(By.id("remove-sauce-labs-fleece-jacket")).click();
	System.out.println("Bike light & Fleece jacket were removed from cart");
	
	
	//Verify remaining cart count
	String updatedCart = driver.findElement(By.className("shopping_cart_link")).getText();
	System.out.println("Total number of products in cart: " + updatedCart);
	
	//Assertion
	Assert.assertEquals(updatedCart, "2", "Cart should be 2 after removing 2 products");
	
//Checkout
	driver.findElement(By.id("checkout")).click();
	w.until(ExpectedConditions.elementToBeClickable(By.id("checkout_info_container")));
	driver.findElement(By.id("first-name")).sendKeys("Esther");
	driver.findElement(By.id("last-name")).sendKeys("Emmanuel");
	driver.findElement(By.id("postal-code")).sendKeys("10045");
	driver.findElement(By.id("continue")).click();
	
	
	w.until(ExpectedConditions.elementToBeClickable(By.className("summary_info")));
	driver.findElement(By.id("finish")).click();
	
	// Get the actual success message
String successMessage = driver.findElement(By.className("complete-header")).getText();

// Print the success message on the console
System.out.println("Checkout message: " + successMessage);
	
//Assertion
	Assert.assertEquals(successMessage, "Thank you for your order!");
	

	
	}
@AfterMethod
public void tearDown() {
		driver.quit();
	}
	
}



