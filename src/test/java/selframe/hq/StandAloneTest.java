package selframe.hq;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver =new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		
		
		String productname="ZARA COAT 3";
		driver.findElement(By.id("userEmail")).sendKeys("pjadhav@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("@Panu123");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		WebElement selectedProduct=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		
		selectedProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();//fist-of-type also used to view button
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		
		List<WebElement> cartproducts =driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match=cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(match);
		
		
	/*	JavascriptExecutor js=(JavascriptExecutor)driver; //javaScriptexcutor object  casting javascript executor
		js.executeScript("window.scrollBy(0,500)");
		WebElement checkout=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));
		checkout.click();*/
		
		WebElement checkout = driver.findElement(By.cssSelector(".totalRow button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkout);
		Thread.sleep(500);
		checkout.click(); 
		
	//	driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a= new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder= 'Select Country']")),"india").build().perform();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		WebElement results = driver.findElement(By.cssSelector(".ta-results"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", results);
		Thread.sleep(500);
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		
	/*	WebElement placeorder = driver.findElement(By.cssSelector(".action__submit"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeorder);
		Thread.sleep(500);
		placeorder.click(); */
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		

	}

}
