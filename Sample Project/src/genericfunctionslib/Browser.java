package genericfunctionslib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {
	   
	
	
	public static WebDriver startBrowser(String browsername, String url) throws Exception {
		WebDriver driver = null;
		if (browsername.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\eclipse\\eclipse\\Sample Project\\resources\\geckodriver.exe");
			driver= new FirefoxDriver();
		}
		else if (browsername.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./resources/chromedriver.exe");
			driver= new ChromeDriver();
		} else {
			throw new Exception("Unsupported browser");
		}
		driver.manage().window().maximize();
		driver.get(url);			
		return driver;
	}

}
