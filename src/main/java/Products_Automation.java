import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Products_Automation {
WebDriver driver;

@BeforeMethod
public void openingBrowser() {
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.saucedemo.com/");
}
@Test
public void productListing() {
	driver.findElement(By.id("user-name")).sendKeys("standard_user");
	driver.findElement(By.id("password")).sendKeys("secret_sauce");
	driver.findElement(By.id("login-button")).click();
	List <WebElement> products = driver.findElements(By.className("inventory_item"));
	System.out.println("Total products:" + products.size());
	
	
	
	for (WebElement product : products) {
		String productName = product.findElement(By.className("inventory_item_name")).getText();
		System.out.println("Product Name: " + productName);
		
		
	}
	
	//Price Sorting
	List <WebElement> products1 = driver.findElements(By.className("inventory_item"));
	List <Double> prices = new ArrayList<>();
	
	for (WebElement product : products1) {
		String productPrice = product.findElement(By.className("inventory_item_price")).getText();
		double priceNumber = Double.parseDouble(productPrice.replace("$", ""));
		prices.add(priceNumber);
	
	}
	Collections.sort(prices);
	System.out.println("Prices from lowest to highest: " + prices);
	
	Collections.reverse(prices);
	System.out.println("Prices from highest to lowest: " + prices);

}

@AfterMethod
public void tearDown() {
	driver.quit();
}
}
