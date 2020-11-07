package generics;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest 
{
	public static WebDriver driver;
	
	public ExtentReports reports;
	public ExtentTest test;
	@BeforeMethod
	public void openApp()
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver=new ChromeDriver();
		//driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//driver.get("https://www.gmail.com");
		//driver.get("http://localhost:8888/");
		driver.get("https://www.facebook.com");
		reports= new ExtentReports("./reports/extentreports.html", false);
		test = reports.startTest("Regration Test Cases");
	}
	@AfterMethod
	public void closeApp(ITestResult res) throws IOException
	{
		int status = res.getStatus();
		String tcname = res.getName();		//---> print the test cases name;
		if(status==2)
		{
			String path = GenericScreenShots.photo(driver, tcname);
			test.log(LogStatus.FAIL, test.addScreenCapture(path));
			reports.endTest(test);
			reports.flush();
			driver.quit();
		}
	}
}


