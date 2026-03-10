package selframe.hq;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import maincomponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) //constructor invokes 1st
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//WebElement uEmail =driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement uEmail;
	
	@FindBy(id="userPassword")
	WebElement upassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalog loginApplication(String email,String password)
	{
		uEmail.sendKeys(email);
		upassword.sendKeys(password);
		submit.click();
		
		ProductCatalog ProductCatalog=new ProductCatalog(driver);
		return ProductCatalog;

	}
	public void  goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");

	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
