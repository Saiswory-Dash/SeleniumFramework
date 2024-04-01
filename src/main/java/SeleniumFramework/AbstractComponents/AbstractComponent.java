package SeleniumFramework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Demo.SeleniumFramework.pageobjects.CartPage;
import Demo.SeleniumFramework.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartPage;
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderpage;
	
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void elementToAppear(By waitElement) throws InterruptedException {
				
		  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		  wait.until(ExpectedConditions.visibilityOfElementLocated(waitElement));
		 
	}
	public void elementToAppearWebElement(WebElement waitElementWebElement) throws InterruptedException {
		
		  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		  wait.until(ExpectedConditions.visibilityOf(waitElementWebElement));
		 
	}
	public void elementToDisappear(WebElement element) throws InterruptedException {
		Thread.sleep(1000);

		/*
		 * WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		 * wait.until(ExpectedConditions.invisibilityOf(element));
		 */
	}
	public CartPage gotoCartPage() {
		cartPage.click();
		CartPage cartpage=new CartPage(driver);
		return cartpage;
	}
	public OrderPage gotoOrderPage() {
		orderpage.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}
}
