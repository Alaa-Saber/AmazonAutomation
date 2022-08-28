package AutomationTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonItemPage {
	
	WebDriver browser;
	
	// Elements
	By addToCartBtn = By.id("add-to-cart-button");
	By itemTitle = By.id("productTitle");
	By addToCartBtnDeals = By.id("add-to-cart-button-ubb");
	
	
	//constructor
	public AmazonItemPage(WebDriver browser) {
		this.browser = browser;
	}
	
	//actions	
	public void addItemToCart() {
		if (browser.findElement(addToCartBtn).isDisplayed()) {
			browser.findElement(addToCartBtn).click();			
		}else {
			browser.findElement(addToCartBtnDeals).click();			
		}
	}
	
	public String getItemTitle() {
		return (browser.findElement(itemTitle).getText());
	}
	
}
