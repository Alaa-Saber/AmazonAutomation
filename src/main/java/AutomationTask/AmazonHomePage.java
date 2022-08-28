package AutomationTask;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class AmazonHomePage {

	WebDriver browser;
	String WebsiteURL = "https://www.amazon.com/";
	JavascriptExecutor js;

	// Elements
	By searchBox = By.id("twotabsearchtextbox");
	By searchBtn = By.id("nav-search-submit-button");
	By todayDeals = By.xpath("//*[@id=\"nav-xshop\"]//*[contains(text(), \"Today's Deals\")]");
	By locationBtn = By.xpath("//span[1]/span/input");
	By groceryFilter = By.xpath("//ul//*[contains(text(), 'Grocery')]");
	By headphoneFilter = By.xpath("//ul//*[contains(text(), 'Headphones')]");
	By discount10Filter = By.xpath("//span[6]//li[2]//a");
	By page3 = By.xpath("//div[3]/div/ul/li[4]/a");
	By page4 = By.xpath("//div[3]/div/ul/li[5]/a");
	
	//constructor
	public AmazonHomePage(WebDriver browser) {
		this.browser = browser;
	}
	
	//actions
	public void navigate() {
		browser.navigate().to(WebsiteURL);
		if (browser.findElement(locationBtn).isDisplayed() == true) {
			browser.findElement(locationBtn).click();
		}
	}
	
	public void search(String word) {
		browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		browser.findElement(searchBox).sendKeys(word);
		browser.findElement(searchBtn).click();
	}
	
	public void goToTodayDeal() {
		browser.findElement(todayDeals).click();
		browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	
	public void applyFiltersInTodayDeal() {
		goToTodayDeal();
		js = (JavascriptExecutor) browser;
		js.executeScript("arguments[0].scrollIntoView();", browser.findElement(groceryFilter));
		browser.findElement(groceryFilter).click();
		browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		js.executeScript("arguments[0].scrollIntoView();", browser.findElement(headphoneFilter));
		browser.findElement(headphoneFilter).click();
		browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		js.executeScript("arguments[0].scrollIntoView();", browser.findElement(discount10Filter));
		browser.findElement(discount10Filter).click();
		browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	public void goToPageInTodayDeal() {
		applyFiltersInTodayDeal();
		js.executeScript("arguments[0].scrollIntoView();", browser.findElement(page3));
		browser.findElement(page3).click();
		browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		js.executeScript("arguments[0].scrollIntoView();", browser.findElement(page4));
		browser.findElement(page4).click();
		browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
}
