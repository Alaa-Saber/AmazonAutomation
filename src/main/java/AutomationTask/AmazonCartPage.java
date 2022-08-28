package AutomationTask;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonCartPage {

	WebDriver browser;
	String cartURL = "https://www.amazon.com/gp/cart/view.html?ref_=nav_cart";

	// Elements
	By cartItemTitle = By.xpath("//span/a//span/span[2]");
	
	//constructor
	public AmazonCartPage(WebDriver browser) {
		this.browser = browser;
	}
	
	//actions
	public void navigate() {
		browser.navigate().to(cartURL);
		browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	
	public String getCartItemTitle() {
		return (browser.findElement(cartItemTitle).getText());
	}

}
