package maincomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selframe.hq.CartPage;
import selframe.hq.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	
		public AbstractComponents(WebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		public void waitForElementToAppear(By findBy)
		{
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		}
		public void waitForWebElementToAppear(WebElement findBy)
		{
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(findBy));
		}
		public void waitForElementToDisAppear(WebElement ele)
		{
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
			wait.until(ExpectedConditions.invisibilityOf(ele));
		}
		
		
		@FindBy(css="[routerlink*='cart']")
		WebElement cartHeader;
		
		@FindBy(css="[routerlink*='myorders']")
		WebElement orderHeader;
		
		public CartPage goToCart()
		{
			cartHeader.click();
			CartPage CartPage=new CartPage(driver);
			return CartPage;
		}
		public OrderPage goToOrder()
		{
			orderHeader.click();
			OrderPage OrderPage=new OrderPage(driver);
			return OrderPage;
		}
		
		
}
