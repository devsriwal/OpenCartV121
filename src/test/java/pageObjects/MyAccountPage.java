package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//h1[normalize-space()='My Account']")
	WebElement myAccount;
	
	@FindBy(xpath = "(//a[text()='Logout'])[2]")
	WebElement lnkLogout;
	
	
	public boolean isMyPageExist() {
		
		try {
//			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		     WebElement myAcc = wait.until(ExpectedConditions.visibilityOfElementLocated((By) myAccount));
			 return (myAccount.isDisplayed());

		}
		catch(Exception e) {
			return false;
		}
	}
	
	
	public void clickLogout() {
		lnkLogout.click();
	}
}
