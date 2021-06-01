package businessfunctionslib;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;



public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
	this.driver=driver;
	}
	
	@FindBy(xpath=".//*[@name='user-name']")
	 WebElement Username;

	
	@FindBy(xpath=".//*[@name='password']")
	 WebElement Password;
	
	
	@FindBy(xpath=".//*[@name='login-button']")
	 WebElement LoginButton;
	
	private String xpathHomePageTitle = ".//*[text()='Products' and @class='title']";
	private String xpathIncorrectCredentials = ".//*[contains(text(),'Epic sadface: Username and password do not match any user in this service')]";
	private String xpathUsernameRequired = ".//*[contains(text(),'Epic sadface: Username is required')]";
	private String xpathPasswordRequired = ".//*[contains(text(),'Epic sadface: Password is required')]";
	private String xpathUsernameAndPasswordRequired = ".//*[contains(text(),'Epic sadface: Username is required')]";
	
	public void loginSwaglabs(String uname, String pass)  {     
			Username.sendKeys(uname);
			System.out.println("Username used is "+uname);
			Reporter.log("Username is "+uname);
	        Password.sendKeys(pass);
	        LoginButton.click();
	        Reporter.log("Clicked on Login button");
	}
	
	public void verifyHomePage() {
		List<WebElement> homepageTitles = driver.findElements(By.xpath(xpathHomePageTitle));
		Assert.assertFalse(homepageTitles.isEmpty(), "Login is unsuccessful");
		Assert.assertTrue(homepageTitles.get(0).getText().equalsIgnoreCase("PRODUCTS"), "Login is unsuccessful");
	}
	
	public void verifyIncorrectCredentials() {
		Assert.assertFalse(driver.findElements(By.xpath(xpathIncorrectCredentials)).isEmpty(), "No Error message is displayed");
	}

	public void verifyUsernameRequired() {
		Assert.assertFalse(driver.findElements(By.xpath(xpathUsernameRequired)).isEmpty(), "No Error message is displayed");
	}
	
	public void verifyPasswordRequired() {
		Assert.assertFalse(driver.findElements(By.xpath(xpathPasswordRequired)).isEmpty(), "No Error message is displayed");
	}
	
	public void verifyUsernameAndPasswordRequired() {
		if (!driver.findElement(By.xpath(xpathUsernameAndPasswordRequired)).getText().equalsIgnoreCase("username and password are required")) {
			Assert.fail("Incorrect error message is displayed");
		}
			}
	}


	

	