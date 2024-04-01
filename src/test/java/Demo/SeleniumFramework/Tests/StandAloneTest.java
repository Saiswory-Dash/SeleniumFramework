package Demo.SeleniumFramework.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.bouncycastle.asn1.dvcs.Data;
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

import Demo.SeleniumFramework.TestComponents.BaseTest;
import Demo.SeleniumFramework.pageobjects.CartPage;
import Demo.SeleniumFramework.pageobjects.CheckOutPage;
import Demo.SeleniumFramework.pageobjects.ConfirmationPage;
import Demo.SeleniumFramework.pageobjects.LandingPage;
import Demo.SeleniumFramework.pageobjects.OrderPage;
import Demo.SeleniumFramework.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest extends BaseTest {

	String productName = "ZARA COAT 3";
		// TODO Auto-generated method stub
	
		@Test(dataProvider="getData",groups= {"purchase"})
		
		/*one way of declaration dataprovider using string
		 * public void submitOrder(String email, String password,String productname)
		 * throws IOException, InterruptedException{ ProductCatalogue productcatalogue=
		 * landingpage.loginApplication(email, password);
		 */
		public void submitOrder(HashMap<String,String>input) throws IOException, InterruptedException{
		ProductCatalogue productcatalogue=	landingpage.loginApplication(input.get("email"), input.get("password"));
	    List<WebElement>products=productcatalogue.getProductList();
		productcatalogue.addToCart(input.get("product"));
		CartPage cartpage=productcatalogue.gotoCartPage();
		boolean match=cartpage.verifyProductDisplay("ZARA COAT 3");
		Assert.assertTrue(match);
		CheckOutPage checkoutpage=cartpage.goToCheckoutPage();
		
	    checkoutpage.selectCountryName("India");
		ConfirmationPage confirmpage=checkoutpage.submitOrder();
		String msg=confirmpage.confirmMessagevalidation();
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	

	@Test(dependsOnMethods={"submitOrder"})
	public void orderHistoryTest() {
		ProductCatalogue productcatalogue=	landingpage.loginApplication("sai@gmai.com", "Sai@123456");
		OrderPage orderpage=productcatalogue.gotoOrderPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
		
	}


	
	@DataProvider
	public Object[][] getData() throws IOException{
		List<HashMap<String,String>>map=getJsonDataToMap(System.getProperty
				("user.dir")+"//src//test//java//Demo//SeleniumFramework//data//PurchaseOrder.json");
		return new Object[][] {{map.get(0)},{map.get(1)}};
	}

	
	
	
			/*
			 * HashMap<String,String>map=new HashMap<String,String>();
			 * map.put("email","sai@gmai.com"); map.put("password","Sai@123456");
			 * map.put("product","ZARA COAT 3");
			 * 
			 * HashMap<String,String>map2=new HashMap<String,String>();
			 * map2.put("email","saii@gmai.com"); map2.put("password","Sai@123456");
			 * map2.put("product","ZARA COAT 3"); return new Object[][] {{map},{map2}};
			 */
	
	/*
	 * @DataProvider public Object[][] getData(){ return new Object[][]
	 * {{"sai@gmai.com", "Sai@123456","ZARA COAT 3"}, {"sai@gmai.com",
	 * "Sai@123456","ZARA COAT 3"}}; }
	 */
}
