package stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selframe.hq.CartPage;
import selframe.hq.CheckOutPage;
import selframe.hq.ConfirmationPage;
import selframe.hq.LandingPage;
import selframe.hq.ProductCatalog;
import testcomponents.BaseTest;

public class StepdefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalog ProductCatalog;
	public ConfirmationPage ConfirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage=launchApplication();
	}
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_username_and_password(String username,String password)
	{
		ProductCatalog = landingPage.loginApplication(username,password);

	}
	@When("^I add product (.+) to cart$")
	public void add_product_to_cart(String productName)
	{
		List<WebElement> products=ProductCatalog.getProductList();
		ProductCatalog.addProductTocart(productName);
	}
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_submit_order(String productName )
	{
		CartPage CartPage=ProductCatalog.goToCart();		
		boolean match=CartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage CheckOutPage =CartPage.goToCheckout();
		CheckOutPage.selectCountry("india");
		ConfirmationPage=CheckOutPage.submitOrder();

	}
	@Then("{string} message is displayed on Confirmation page")
	public void message_displayed_confirmationpage(String string)
	{
		String confirmMessage= ConfirmationPage.getConfirmationMsg();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));

	}
	
	@Then("{string} error message is displayed")
	public void error_message_displayed(String string)
	{
		Assert.assertEquals(string,landingPage.getErrorMessage() );

	}

	
	
	

}
