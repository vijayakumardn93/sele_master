import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.WebElement;

public class SeleniumTesting {
	static ExtentTest test;
	static ExtentReports report;

	private static String Base_Url = "https://www.facebook.com";
	private WebDriver driver;

	@Before
	public void setUp() {
		driver = new ChromeDriver();
		driver.get(Base_Url);
		report = new ExtentReports("ExtentReportResults.html",true);
		test = report.startTest("ExtentDemo");
	}

	@After
	public void after() {
		driver.quit();
		report.endTest(test);
		report.flush();
	}

	@Test
	public void testCasePassed() {
		// driver.get("https://www.google.co.in");
		if (driver.getTitle().equals("Google")) {
			test.log(LogStatus.PASS, "Navigated to the specified URL");
		} else {
			test.log(LogStatus.FAIL, "Test Failed");
		}
	}

	@Test
	public void testCaseFailed() {
		// Assert.assertTrue(driver.findElement(By.xpath("//form[@id='failed
		// case']")).isDisplayed());

		driver.get("https://www.linkedin.com/login");

		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement login = driver.findElement(By.xpath("//button[text()='Sign in']"));

		username.sendKeys("example@gmail.com");
		password.sendKeys("password");
		login.click();

		String actualUrl = "https://www.linkedin.com/feed/";
		String expectedUrl = driver.getCurrentUrl();

		Assert.assertEquals(expectedUrl, actualUrl);
		test.log(LogStatus.PASS, "Navigated to the specified URL");

	}
	/*
	 * @Ignore
	 * 
	 * @Test public void testCaseIgnored() {
	 * Assert.assertTrue(driver.findElement(By.xpath("//form[@id='ignored case']")).
	 * isDisplayed()); }
	 */
}