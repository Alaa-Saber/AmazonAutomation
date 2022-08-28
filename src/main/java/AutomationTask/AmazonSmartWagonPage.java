package AutomationTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonSmartWagonPage {

	WebDriver browser;
	
	// Elements
	By goToCartBtn = By.id("sw-gtc");
	By addedToCartMsg1 = By.xpath("//*[@id=\"sw-atc-details-single-container\"]//span");
	By addedToCartMsg2 = By.id("attach-added-to-cart-alert-and-image-area");
	
	
	//constructor
	public AmazonSmartWagonPage(WebDriver browser) {
		this.browser = browser;
	}
	
	//actions
	public void goToCart() {
		browser.findElement(goToCartBtn).click();
	}

	public String getAddedToCartMsg() {
		if (browser.findElement(addedToCartMsg1).isDisplayed()) {
			return browser.findElement(addedToCartMsg1).getText();
		}else {
			return browser.findElement(addedToCartMsg2).getText();			
		}
	}
	
}
