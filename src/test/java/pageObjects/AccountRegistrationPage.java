package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPrivacyPolicy;
	
	@FindBy(xpath="//button[normalize-space()='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	
	// Actions on above locator
	
	
	public void setFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);
	}
	

	public void setLasttName(String lastName) {
		txtLastName.sendKeys(lastName);
	}
	

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	

	public void setTelephone(String telephone) {
		txtTelephone.sendKeys(telephone);
	}
	

	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	

	public void setConfirmPassword(String confirmPasswor) {
		txtConfirmPassword.sendKeys(confirmPasswor);
	}
	

	public void setPrivacyPolicy() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    // Scroll to the checkbox and pause
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", chkPrivacyPolicy);

	    // Add a pause to let animation/layout settle
	    try {
	        Thread.sleep(1000); // 1 second
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    // Ensure element is visible
	    wait.until(ExpectedConditions.visibilityOf(chkPrivacyPolicy));

	    // Try JS click
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chkPrivacyPolicy);
	}
	

	public void clickContinue() {
		btnContinue.click();
	}
	
	public String getConfirmMessage() {
		try {
			return msgConfirmation.getText();
		}catch(Exception e) {
			return (e.getMessage());
		}
		
	}

}
