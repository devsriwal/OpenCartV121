package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


// This is the parent of all page object classes. Because constructor is same for all page classes.
public class BasePage {
	
	WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
}
