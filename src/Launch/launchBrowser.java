package Launch;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class launchBrowser {
	
	WebDriver driver;
	
	@Test
	public void chromeBrowser() {
		
		System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("incognito");
	    options.addArguments("--start-maximized");
	    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	    driver = new ChromeDriver(capabilities);		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.google.com/");
		
		System.out.println("<===[ Google Chrome Browser open sucessfully ]===>");
		
	}

}
