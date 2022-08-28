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
import AutomationTask.AmazonHomePage;
import AutomationTask.AmazonItemPage;
import AutomationTask.AmazonSmartWagonPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AddItemFromTodayDeal {
	
	WebDriver browser;
	Dimension requiredDimension = new Dimension(1024, 768);
	AmazonHomePage homePage;
	AmazonItemPage itemPage;
	AmazonSmartWagonPage wagon;
	
	// Elements
	By itemToSelect = By.xpath("//div[3]//div[1]/div/div/a[3]/div");
	
	// Validation Messages
	String addedToCartMsg = "Added to Cart";
	
	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		browser = new ChromeDriver();
		browser.manage().window().setSize(requiredDimension);
		homePage = new AmazonHomePage(browser);
		itemPage = new AmazonItemPage(browser);
		wagon = new AmazonSmartWagonPage(browser);
	}

	@BeforeMethod
	public void beforeMethod() {
		homePage.navigate();
	}

	@Test
	public void select_item_from_Page4() {
		homePage.goToPageInTodayDeal();
		browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		browser.findElement(itemToSelect).click();
		itemPage.addItemToCart();
		browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		assertEquals(wagon.getAddedToCartMsg(), addedToCartMsg);
	}
	
	@AfterClass
	public void afterClass() {
		browser.quit();
	}

}
