package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"sanity","Master"})
	public void verify_login()
	{
		logger.info("*** Starting TC002_LoginTest *** ");
		
		try
		{
		//Home page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//My Account Page
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExits();
		
		//Assert.assertEquals(targetPage,true,"Login failed");
		Assert.assertTrue(targetPage);
		
		logger.info("*** Finished TC002_LoginTest *** ");
	}
		
	catch(Exception e)
		{
		  Assert.fail();
		}
	}
}
