package Demo.SeleniumFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import SeleniumFramework.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By waitProducts=By.cssSelector(".mb-3");
	By addtocart=By.cssSelector(".card-body button:last-of-type");
	By waitToast=By.cssSelector("#toast-container");
	
	public  List<WebElement> getProductList() throws InterruptedException {
		elementToAppear(waitProducts);
		return products;
		
	}
	public WebElement getProductByName(String productName) {
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);
		return prod;
	}
	public void addToCart(String productName) throws InterruptedException {
		WebElement prod=getProductByName(productName);
		prod.findElement(addtocart).click();
		elementToAppear(waitToast);
		elementToDisappear(spinner);
		
	}
	
	
	
	
	
}
