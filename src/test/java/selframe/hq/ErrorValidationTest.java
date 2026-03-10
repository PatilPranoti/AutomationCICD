package selframe.hq;

import java.io.IOException;
import java.util.List;
import testcomponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import testcomponents.BaseTest;

public class ErrorValidationTest extends BaseTest{

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	
public void LoginErrorValidation() throws InterruptedException, IOException {
		
		
		
		String productName="ZARA COAT 3";
		landingPage.loginApplication("pjadhav@gmail.com", "@Panu123");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage() );

	}
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{

		String productName = "ZARA COAT 3";
		ProductCatalog ProductCatalog = landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
		List<WebElement> products = ProductCatalog.getProductList();
		ProductCatalog.addProductTocart(productName);
		CartPage cartPage = ProductCatalog.goToCart();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	

	}
}