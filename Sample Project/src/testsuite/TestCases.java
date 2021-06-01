package testsuite;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import businessfunctionslib.LoginPage;
import genericfunctionslib.Browser;
import genericfunctionslib.Excel;
import genericfunctionslib.Utility;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.Reporter;

public class TestCases
	{
	WebDriver driver;

	@BeforeMethod
	public void initializeDriver() throws Exception{
		// Call to Browser method to open the browser and URL
		driver= Browser.startBrowser("chrome", "https://www.saucedemo.com");	
		Reporter.log("Browser launched and URL is opened");
		
	}
	
	@AfterMethod
	public void reportAndCloseDriver(ITestResult result) {
		if(ITestResult.FAILURE==result.getStatus()) {
			Utility.CaptureScreenshots(driver,result.getName());
		}
		driver.close();		
	}


	@DataProvider(name = "credentialsProvider")
	public static Object[][] getCredentials() throws IOException {
		List<Map<String, String>> dataElements = Excel.getTestData();
	    Object[][] credentials = new Object[dataElements.size()][3];
	    for (int i = 0; i < dataElements.size(); i++) {
	    	Map<String, String> dataElement = dataElements.get(i);
	    	credentials[i][0] = dataElement.get("Username");
	    	credentials[i][1] = dataElement.get("Password");
	    	credentials[i][2] = dataElement.get("ExpectedBehavior");
	    }
	    return credentials;
	}
	
	@Test(dataProvider = "credentialsProvider")
	public void testLoginSwaglabs(String username, String password, 
			String expectedBehavior) throws Exception {
		loginAndVerify(username, password, expectedBehavior);
	}
	
	private void loginAndVerify(String username, String password, String expectedBehavior) throws Exception {
		//create a page object using page factory		
		LoginPage login_page=PageFactory.initElements(driver, LoginPage.class);
		login_page.loginSwaglabs(username, password);

		switch(expectedBehavior) {
		case "VALID_CREDENTIALS":
			login_page.verifyHomePage();
			Reporter.log("Login with Valid Credentials is successful");
			break;
		case "INCORRECT_CREDENTIALS":
			login_page.verifyIncorrectCredentials();
			Reporter.log("Login with Invalid Credentials is working as expected");
			break;
		case "USERNAME_REQUIRED":
			login_page.verifyUsernameRequired();
			Reporter.log("Login with username as blank and valid password is working as expected");
			break;
		case "PASSWORD_REQUIRED":
			login_page.verifyPasswordRequired();
			Reporter.log("Login with password as blank and valid username is working as expected");
			break;
		case "USERNAME_AND_PASSWORD_REQUIRED":
			login_page.verifyUsernameAndPasswordRequired();
			Reporter.log("Login with blank username and password is working as expected");
			break;
		}

	}
	
}
	
