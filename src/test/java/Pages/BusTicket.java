package Pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import AutomationTask.BusTicketWebsite;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BusTicket {
	
	WebDriver browser;
	Dimension requiredDimension = new Dimension(1024, 768);
	BusTicketWebsite homepage;
	
	// messages
	String alertMsgToSelectRoute = "Please select start place.";
	
	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		browser = new ChromeDriver();
		browser.manage().window().setSize(requiredDimension);
		homepage = new BusTicketWebsite(browser);
	}

	@BeforeMethod
	public void beforeMethod() {
		homepage.navigate();
	}
	
	@Test
	public void book_bus_ticket_successfully() {
		homepage.selectPopularRoute();
		homepage.selectTomorrowDate();
		homepage.goToBookingForm();
		homepage.selectBoardingPoint();
		homepage.selectAvailSeat();
		homepage.fillPassengerData();
	}

	@Test
	public void user_must_select_route() {
		homepage.selectDatePicker();
		homepage.selectTomorrowDate();
		homepage.goToBookingForm();
		assertEquals(browser.switchTo().alert().getText(), alertMsgToSelectRoute);
	}
	
	@Test
	public void user_must_date() {
		homepage.selectPopularRoute();
		homepage.goToBookingForm();
		assertTrue(homepage.DatePickerIsDisplayed());
	}

	@AfterClass
	public void afterClass() {
		browser.quit();
	}
	
}
