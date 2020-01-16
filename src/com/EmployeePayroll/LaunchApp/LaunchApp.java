package com.EmployeePayroll.LaunchApp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class LaunchApp {
	protected static WebDriver driver;
	static WebElement element;
	ExtentReports extent;
	ExtentTest logger;

	@BeforeTest
	public void launchBrowser() {
		
		System.setProperty("webdriver.chrome.driver", "Browser_Files/chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("incognito");
	    options.addArguments("--start-maximized");
	    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	    driver = new ChromeDriver(capabilities);		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("<===[ Google Chrome Browser open sucessfully ]===>");
		extent = new ExtentReports (System.getProperty("user.dir") +"/TestRunReport/PayrollReport.html", true);
	}
	
	@Test
	public void executeScript() {
		
		logger = extent.startTest("Execute Script");
		String listofpickup[] = {"Toronto","Brampton","Mississauga"};
		String listofdrop[] = {"Niagara Falls","Barrie","North york"};
		
		driver.get("https://www.orbitz.com/");
		logger.log(LogStatus.INFO, "URL loaded successfully");
		
		for (int o=0; o<listofpickup.length ; o++)  {	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		element = driver.findElement(By.xpath(Xpaths.homeCarBtn));
		try {
			element.isEnabled();
			System.out.println("Car button is enabled");
			logger.log(LogStatus.INFO, "Car button is enabled");
			element.click();
			logger.log(LogStatus.INFO, "Clicked on CAR button");
			System.out.println("<===[ Clicked on car button ]===>");
		}catch(Exception e) {
			System.out.println("Car button is disabled");
			logger.log(LogStatus.INFO, "Car button is disabled");
			driver.quit();
		}
		
			WebElement pickup = driver.findElement(By.xpath(Xpaths.homePickUpTxt));
			pickup.click();
			pickup.sendKeys(listofpickup[o]);
			logger.log(LogStatus.INFO, "Entered Pick Up Destination: "+listofpickup[o]);
			
			WebElement dropoff = driver.findElement(By.xpath(Xpaths.homeDropOffTxt));
			dropoff.click();
			dropoff.sendKeys(listofdrop[o]);	
			logger.log(LogStatus.INFO, "Entered Drop off Destination: "+listofdrop[o]);

			driver.findElement(By.xpath(Xpaths.homePickUpDateTxt)).clear();
			driver.findElement(By.xpath(Xpaths.homePickUpDateTxt)).sendKeys("1/20/2020");
			logger.log(LogStatus.INFO, "Entered Pick up Date");
			driver.findElement(By.xpath(Xpaths.homeDropOffDateTxt)).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.xpath(Xpaths.homeSearchBtn)).click();
			logger.log(LogStatus.INFO, "Entered Drop Off Date");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//a[@id = 'header-logo']")).click();
			logger.log(LogStatus.INFO, "Clicked on Search button");
		}	
	}
	
	@Test
	public void testCase2() {
		logger = extent.startTest("Test Case 2");
		logger.log(LogStatus.FAIL, "Test Execution Failed.");
	}
	
	@Test
	public void testCase3() {
		logger = extent.startTest("Test Case 3");
		logger.log(LogStatus.SKIP, "Test Case Skipped.");
	}
	
	@Test
	public void testCase4() {
		logger = extent.startTest("Test Case 4");
		logger.log(LogStatus.ERROR, "Test Execution fail due to error.");
	}
	
	@AfterTest
	public void quitMobileDriver() {
		
		extent.endTest(logger);
		extent.flush();
		
		driver.get("file://"+System.getProperty("user.dir") +"/TestRunReport/PayrollReport.html") ;
		driver.findElement(By.xpath("//nav/ul/li[@class='theme-selector']")).click();
	}

}
