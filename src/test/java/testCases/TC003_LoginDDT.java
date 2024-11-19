package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.Dataprovider;

public class TC003_LoginDDT extends BaseClass {
	@Test(dataProvider="LoginData",dataProviderClass=Dataprovider.class,groups="datadriven") // getting data provider from different class
	public void verify_loginDDT(String email,String pwd,String exp)
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
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//My Account Page
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExits();
		
		/*
		•	Valid credential ---login successful ----test passed - logout
		                      ---login unsuccessful --- test failed
		•	InValid credential ---login successful ---test failed ---logout
		                          ---login failed--testpassed
		*/
	//valid data
		if(exp.equalsIgnoreCase("valid")) // data is valid
		{
			if(targetPage==true) // target page is true
			{
				macc.clickLogout();
				Assert.assertTrue(true); // test is passed
				
			}else
			{
				Assert.assertTrue(false); // test is failed
			}
		}
		if(exp.equalsIgnoreCase("Invalid")) // data is invalid
		 {
			if(targetPage==true) // target page is true
		      {
			    macc.clickLogout();
			     Assert.assertTrue(false); // test is failed
			  }
		     else
		       {
			Assert.assertTrue(true); // test is passed
		       }
			
		}}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("**** finished TC_003_LoginDOT ****");
	}

}
