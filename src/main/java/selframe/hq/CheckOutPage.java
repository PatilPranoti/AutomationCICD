package selframe.hq;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import maincomponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{
	
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder= 'Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement placeorder;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	

	
	public void selectCountry(String countryName)
	{
		Actions a= new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		//driver.manage().window().setSize(new Dimension(1440,900));

		driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
		placeorder.click();
		ConfirmationPage ConfirmationPage=new ConfirmationPage(driver);
		return ConfirmationPage;
	}
	
	
}
