package Pages;

import static org.testng.Assert.assertEquals;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import AutomationTask.AmazonCartPage;
import AutomationTask.AmazonHomePage;
import AutomationTask.AmazonItemPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AddItemFromHomePageTest {
	
	WebDriver browser;
	Dimension requiredDimension = new Dimension(1024, 768);
	AmazonHomePage homePage;
	String searchWord = "car accessories";
	AmazonItemPage firstItem;
	AmazonCartPage cartPage;

	// Elements
	By firstResult = By.xpath("//div[4]//h2/a");
		
	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		browser = new ChromeDriver();
		browser.manage().window().setSize(requiredDimension);
		homePage = new AmazonHomePage(browser);
		firstItem = new AmazonItemPage(browser);
		cartPage = new AmazonCartPage(browser);
	}

	@BeforeMethod
	public void beforeMethod() {
		homePage.navigate();
	}

	@Test
	public void select_first_item() {
		homePage.search(searchWord);
		browser.findElement(firstResult).click();
		browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String item_title = firstItem.getItemTitle();
		firstItem.addItemToCart();
		cartPage.navigate();
		String item_title_cart = cartPage.getCartItemTitle();
		assertEquals(item_title.substring(0, 60).toLowerCase(), item_title_cart.substring(0, 60).toLowerCase());
	}

	@AfterClass
	public void afterClass() {
		browser.quit();
	}

}
