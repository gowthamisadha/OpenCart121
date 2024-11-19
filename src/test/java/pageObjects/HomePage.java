package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	// crate constructor, invoke parent class constructor
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(linkText="Login")
	WebElement lnkLogin;
	
	public void clickMyAccount()
	{
		lnkMyaccount.click();
	}
	public void clickRegistration()
	{ 
		lnkRegister.click();
	}
	public void clickLogin()
	{ 
		lnkLogin.click();
	}
}
