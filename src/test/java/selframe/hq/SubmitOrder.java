 


package selframe.hq;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import testcomponents.BaseTest;

public class SubmitOrder extends BaseTest{
	String productName="ZARA COAT 3";


	@Test(dataProvider="getData",groups= {"Purchase"})

	public void submitOrder(HashMap<String,String> input ) throws InterruptedException, IOException
	{
			
		ProductCatalog ProductCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products=ProductCatalog.getProductList();
		ProductCatalog.addProductTocart(input.get("productName"));
		CartPage CartPage=ProductCatalog.goToCart();
		
		
		boolean match=CartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		
		CheckOutPage CheckOutPage =CartPage.goToCheckout();
		CheckOutPage.selectCountry("india");
		ConfirmationPage ConfirmationPage=CheckOutPage.submitOrder();

	
		String confirmMessage= ConfirmationPage.getConfirmationMsg();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		

	}
	@Test(dependsOnMethods= {"submitOrder"})
	
	public void orderHistorytest()
	{
		ProductCatalog ProductCatalog=landingPage.loginApplication("pjadhav@gmail.com", "@Panu123");
		OrderPage OrderPage=ProductCatalog.goToOrder();
		Assert.assertTrue(OrderPage.verifyOrderDisplay(productName));
	}
	/*
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][]  {{"pjadhav@gmail.com","@Panu123","ZARA COAT 3"}, {"pj@gmail.com","@Panu123","ADIDAS ORIGINAL"}};
	}*/
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		/*HashMap<String,String> map=new HashMap<String,String>();
		map.put("email", "pjadhav@gmail.com");
		map.put("password", "@Panu123");
		map.put("productName", "ZARA COAT 3");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email", "pjadhav@gmail.com");
		map1.put("password", "@Panu123");
		map1.put("productName", "ADIDAS ORIGINAL");
		return new Object[][] {{map},{map1}};*/
		
		List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	//webhook jenkin ngrock
	

}
