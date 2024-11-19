package utilities;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import testBase.BaseClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{

		public ExtentSparkReporter sparkReporter; // UI of the report
		public ExtentReports extent; // populate common info on the report
		public ExtentTest test; // creating test case entries in to the report and update the status of the method
		
		String repName;
		
		public void onStart(ITestContext testContext)
		{
			
			String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		    repName="Myreports-"+timeStamp+".html";
		    
		    sparkReporter=new ExtentSparkReporter(".\\Reports\\"+repName); // specify location of the report
		   // sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/Myreports.html");
		    
		    sparkReporter.config().setDocumentTitle("TestNg");//Title of report
		    sparkReporter.config().setReportName("TestNG test");// name of the report
		    sparkReporter.config().setTheme(Theme.DARK);
		    
		    extent=new ExtentReports();
		    extent.attachReporter(sparkReporter);
		    
		    extent.setSystemInfo("Application", "TestNg Listener");
		    extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		    extent.setSystemInfo("User Name", System.getProperty("user.name"));
		    extent.setSystemInfo("Environment", "QA");
		    extent.setSystemInfo("Tester Name", "Gowthami");
	        
		    String os=testContext.getCurrentXmlTest().getParameter("os");//get from XML file
		    extent.setSystemInfo("Operating System", os);
		    
		    String browser=testContext.getCurrentXmlTest().getParameter("browser"); // get from XML file
		    extent.setSystemInfo("Browser",browser);
		    
		    List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		    if(!includedGroups.isEmpty()) {
		    	extent.setSystemInfo("Groups", includedGroups.toString());
		    }
		}
		
		public void onTestSuccess(ITestResult result)
		{
			test=extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups()); // to display griups in report
			test.createNode(result.getName());
			test.log(Status.PASS,result.getName()+"Test succesfully executed");
			
		}
		public void onTestFailure(ITestResult result)
		{
			test=extent.createTest(result.getTestClass().getName());
			//test.createNode(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.FAIL,result.getName()+ "Got Failed");
			test.log(Status.INFO,result.getThrowable().getMessage());
		
			try {
				String imgpath=new BaseClass().captureScreen(result.getName());
				test.addScreenCaptureFromPath(imgpath);
			}catch(IOException el) {
				el.printStackTrace();
			}
		
		}
		
		public void onTestSkipped(ITestResult result) 
		{
			test=extent.createTest(result.getTestClass().getName());
			//test.createNode(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.SKIP, "got Skipped");
			test.log(Status.INFO, result.getThrowable().getMessage());
				
		}
		public void onFinish(ITestContext testContext)
		{
			extent.flush(); // except this report cannot be generated
			
			// Open the report automatically after completed the execution
		
			String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
			File extentReport=new File(pathOfExtentReport);
			
			try {
				Desktop.getDesktop().browse(extentReport.toURI());
				}catch(IOException e)
			{
					e.printStackTrace();
			}
			
			/*
			try {
				URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
				
				// create the email message
				ImageHtmlEmail email=new ImageHtmlEmail();
				*/
			}
			
		}
	


