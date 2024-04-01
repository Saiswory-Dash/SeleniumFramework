package Demo.SeleniumFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	By visibleElement= By.cssSelector(".ta-results");
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountrySpinner;
	
	@FindBy(css = ".action__submit")
	WebElement submit;
	
	
	

	public void selectCountryName(String countryName) throws InterruptedException {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		elementToAppear(visibleElement);
		selectCountrySpinner.click();
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  // Scroll vertically down by 500 pixels
	        js.executeScript("window.scrollBy(0, 500)");
	        Thread.sleep(3000);
		
	}
	public ConfirmationPage submitOrder() throws InterruptedException {
		submit.click();
		ConfirmationPage confirmpage=new ConfirmationPage(driver);
		return confirmpage;
		
	}
	
}



