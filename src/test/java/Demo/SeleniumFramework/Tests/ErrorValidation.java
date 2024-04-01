package Demo.SeleniumFramework.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Demo.SeleniumFramework.TestComponents.BaseTest;
import Demo.SeleniumFramework.TestComponents.RetryListener;
import Demo.SeleniumFramework.pageobjects.CartPage;
import Demo.SeleniumFramework.pageobjects.CheckOutPage;
import Demo.SeleniumFramework.pageobjects.ConfirmationPage;
import Demo.SeleniumFramework.pageobjects.ProductCatalogue;

public class ErrorValidation extends BaseTest {
	ProductCatalogue productcatalogue;

	/* @Test(groups={"ErrorHandling"},retryAnalyzer=RetryListener.class) */
	@Test(groups={"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		productcatalogue = landingpage.loginApplication("sai@gmai.com", "Sai@123456");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMsg());
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		productcatalogue = landingpage.loginApplication("sai@gmai.com", "Sai@123456");
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addToCart("ZARA COAT 3");
		CartPage cartpage = productcatalogue.gotoCartPage();
		boolean match = cartpage.verifyProductDisplay("ZARA COAT 3");
		Assert.assertTrue(match);
		
	}

}
