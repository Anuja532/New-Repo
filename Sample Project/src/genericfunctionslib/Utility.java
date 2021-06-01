package genericfunctionslib;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;


public class Utility {
	
	
	
	public static  void CaptureScreenshots(WebDriver driver, String Screenshotname)  {
		
		try {
			TakesScreenshot ts=(TakesScreenshot)driver ;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(source, new File("C://eclipse//eclipse//Sample Project//src//Screenshots//"+Screenshotname+"_"+System.currentTimeMillis()+".png"));
			System.out.println("Screenshot captured");
		
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot" +e.getMessage());
		}
	}

}
