package selframe.hq;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import maincomponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {
	
	
WebDriver driver;
	
	public ProductCatalog(WebDriver driver) //constructor invokes 1st
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	By productBy=By.cssSelector(".mb-3"); //this is not webelement this is bylocator if driver.find then webelement
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement selectedProduct=getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return selectedProduct;
	}
	
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastmsg=By.cssSelector("#toast-container");
	@FindBy(css=".ng-animating")
	WebElement dismiss;
	
	public void addProductTocart(String productName)
	{
		WebElement selectedProduct=getProductByName(productName);
		selectedProduct.findElement(addToCart).click();
		waitForElementToAppear(toastmsg);
		waitForElementToDisAppear(dismiss);
		
	}
	
	
	

}
