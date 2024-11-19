package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
		
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("******** Starting TC001_AccountRegistrationTest *****");
		
		try
		{
		HomePage hp=new HomePage(driver); 
		//it will try to invoke the constructor so we need to pass the driver
		
		hp.clickMyAccount();
		logger.info("Clicked on My accountlink ");
		
		hp.clickRegistration();
		logger.info("Clicked on Registration Link");
		
		// Account Registration page from PageObjcet with static data
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		logger.info("Providing Customer details.....");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLasttName(randomString().toUpperCase());
		//regpage.setEmail("abc.peter@gmail.com");
		regpage.setEmail(randomString()+"@gmail.com");// randomly generated the email
		regpage.setTelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
						
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected message");
		String confmsg=regpage.getConfirmationMsg();
		
		Assert.assertEquals(confmsg,"Your Account Has Been Created!");
		}catch(Exception e)
		{
			logger.error("Test failed");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		
		logger.info("finished");
	}
	
}
