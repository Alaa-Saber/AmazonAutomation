package AutomationTask;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BusTicketWebsite {
	
	WebDriver browser;
	String websiteURL = "https://ksrtc.in/oprs-web/guest/home.do?h=1";
	JavascriptExecutor js;
	WebDriverWait waitForElement;
	
	// Elements
	By popularRoutes = By.xpath("//section//div[1]/h2");
	By tripFromTo = By.xpath("//*[@id=\"routeSlider\"]//div[2]//li[1]");
	By datePicker = By.id("txtJourneyDate");
	By jumpToCalender = By.xpath("//div[1]/div/div[1]/div/h2");
	By calenderView = By.className("ui-datepicker-title");
	By bookBtn = By.cssSelector(".btn-booking");
	By selectSeatBtn = By.id("SrvcSelectBtnForward0");
	By ForwardboardingTab = By.id("Forwardboarding-tab");
	
	
	//constructor
	public BusTicketWebsite(WebDriver browser) {
		this.browser = browser;
	}
	
	//actions
	public void navigate() {
		browser.navigate().to(websiteURL);
	}
	
	public void selectPopularRoute() {
		js = (JavascriptExecutor) browser;
		js.executeScript("arguments[0].scrollIntoView();", browser.findElement(popularRoutes));
		waitForElement = new WebDriverWait(browser, Duration.ofSeconds(30));
		waitForElement.until(ExpectedConditions.visibilityOfElementLocated(tripFromTo));
		browser.findElement(tripFromTo).click();
	}

	public void selectDatePicker() {
		js = (JavascriptExecutor) browser;
		js.executeScript("arguments[0].scrollIntoView();", browser.findElement(datePicker));
		browser.findElement(datePicker).click();
	}
	

	public boolean DatePickerIsDisplayed() {
		js = (JavascriptExecutor) browser;
		js.executeScript("arguments[0].scrollIntoView();", browser.findElement(calenderView));
		if (browser.findElement(calenderView).isDisplayed()){
			return true;
		}else {
			return false;
		}
	}
	
	public void selectTomorrowDate() {
		js = (JavascriptExecutor) browser;
		js.executeScript("arguments[0].scrollIntoView();", browser.findElement(jumpToCalender));
		for (int i=1 ; i<6 ; i++) {
			String row = "//tbody/tr[" + i + "]";
			waitForElement = new WebDriverWait(browser, Duration.ofSeconds(30));
			waitForElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(row)));
			for (int j=1 ; j<8 ; j++) {
				String cell = row + "/td[" + j + "]";
				String content = browser.findElement(By.xpath(cell)).getAttribute("class");
				String activeDay = " ui-datepicker-week-end ";
				if (content.equals(activeDay) || content.equals(" ")) {
					browser.findElement(By.xpath(cell+"/a")).click();
					i = 6;
					break;
				}
			}
		}	
	}
	
	public void goToBookingForm() {
		js = (JavascriptExecutor) browser;
		waitForElement = new WebDriverWait(browser, Duration.ofSeconds(30));
		js.executeScript("arguments[0].click();", browser.findElement(bookBtn));
	}
	
	public void selectBoardingPoint() {
		js = (JavascriptExecutor) browser;
		waitForElement = new WebDriverWait(browser, Duration.ofSeconds(30));
		js.executeScript("arguments[0].click();", browser.findElement(selectSeatBtn));
		waitForElement.until(ExpectedConditions.visibilityOfElementLocated(ForwardboardingTab));
		js.executeScript("arguments[0].scrollIntoView();", browser.findElement(ForwardboardingTab));
		browser.findElement(By.xpath("//*[@id=\"Forwardboarding\"]//li[1]")).click();		
		browser.findElement(By.xpath("//*[@id=\"Forwarddroping\"]//li[1]")).click();
		browser.findElement(By.id("mobileNo")).sendKeys("6789125987)");
	
	}
	
	public void selectAvailSeat() {
		for (int i=1 ; i<7 ; i++) {
			String column = "//*[@id=\"seatlayout-Forward1\"]/ul[" + i + "]";
			waitForElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(column)));
			for (int j=1 ; j<12 ; j++) {
				String cell = column + "/li[" + j + "]";
				String availSeat = browser.findElement(By.xpath(cell)).getAttribute("class");
				if (availSeat.equals("availSeatClassS")) {
					browser.findElement(By.xpath(cell)).click();
					i = 7;
					break;
				}
			}
		}
	}
	
	public void fillPassengerData() {
		js = (JavascriptExecutor) browser;
		waitForElement = new WebDriverWait(browser, Duration.ofSeconds(30));
		js.executeScript("arguments[0].scrollIntoView();", browser.findElement(By.id("ForwardPxInfoDv")));
		browser.findElement(By.id("passengerNameForward0")).sendKeys("Name of Passenger");
		browser.findElement(By.id("genderCodeIdForward0")).click();
		Select dropDown = new Select(browser.findElement(By.id("genderCodeIdForward0")));
		dropDown.selectByVisibleText("MALE");
		browser.findElement(By.id("passengerAgeForward0")).sendKeys("33");
		browser.findElement(By.id("concessionIdsForward0")).click();
		dropDown = new Select(browser.findElement(By.id("concessionIdsForward0")));
		dropDown.selectByVisibleText("GENERAL PUBLIC");
		browser.findElement(By.id("passengerNameForward0")).click();
	}

}
