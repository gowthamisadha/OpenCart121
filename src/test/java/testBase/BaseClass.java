package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger; // Log4j
	public Properties p;
	
	//execute only once setup and tearDown
	@BeforeClass(groups= {"sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
		// Loading config.properties file
		FileReader file=new FileReader(".//src//test//resources//config.properties");
				
		// load the properties file
		p=new Properties();
		p.load(file);
		
		
		logger=LogManager.getLogger(this.getClass());
		
		switch(br.toLowerCase())
		{
		case "chrome": driver=new ChromeDriver();break;
		case "edge":driver=new EdgeDriver();break;
		case "firefox":driver=new FirefoxDriver();break;
		default : System.out.println("Invalid browser name..");
		return;
		}
				
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
		driver.get(p.getProperty("appURL1")); // Reading url from properties file
		driver.manage().window().maximize();
	
	}
	
	@AfterClass(groups= {"sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	// creating Dynamic data using Java Method
		public String randomString()
		{
			String generatedstring= RandomStringUtils.randomAlphabetic(5);
			return generatedstring;
		}
		public String randomNumber()
		{
			String generatednumber= RandomStringUtils.randomNumeric(10);// it is return string format only
			return generatednumber;
		}
		public String randomAlphaNumeric()
		{
			String generatedstring= RandomStringUtils.randomAlphabetic(5);
			String generatednumber= RandomStringUtils.randomNumeric(10);
			return (generatedstring+"*"+generatednumber);
		}
		public String captureScreen(String tname) throws IOException {
			
			String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
		    TakesScreenshot ts=(TakesScreenshot)driver;
		    File srcfile=ts.getScreenshotAs(OutputType.FILE);
		    
		    String trgFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timestamp;
		    File targetFile=new File(trgFilePath);
		    
		    srcfile.renameTo(targetFile);
		    
		    return trgFilePath;
		    
		}
}
