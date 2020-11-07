package generics;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GenericScreenShots
{
	public static String photo(WebDriver driver , String tcname) throws IOException
	{
		String photo="./screenshots/";
		
		Date d=new Date();
		
		String d1=d.toString();
		
		String date=d1.replaceAll(":", "-");
		
	TakesScreenshot ts=(TakesScreenshot) driver;
		
		File srcfile=ts.getScreenshotAs(OutputType.FILE);
		File dst=new File(photo+date+tcname+".jpeg");
		String path = dst.getAbsolutePath();	//--> path=exact location of Screenshots;
		FileUtils.copyFile(srcfile, dst);
		
		return path;	
	}

}
