package selframe.hq;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import maincomponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartproducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;

	public boolean verifyProductDisplay(String productName)
	{
		boolean match=cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
	return match;
	}
	
	public CheckOutPage goToCheckout()
	{
		driver.manage().window().setSize(new Dimension(1440,900));

		//driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
		checkout.click();
		CheckOutPage CheckOutpage=new CheckOutPage(driver);
		return CheckOutpage;
	}
}
