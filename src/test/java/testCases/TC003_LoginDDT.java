package testCases;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test( dataProvider = "LoginData" , dataProviderClass=DataProviders.class, groups="dataDriven") 
	public void verify_loginDDT(String email, String password, String res) {
		
		logger.info("**** Starting TC003_LoginDDT******");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();
		
		
		MyAccountPage myAcc=new MyAccountPage(driver);
		boolean targetPage=myAcc.isMyPageExist();
		
		if(res.equalsIgnoreCase("Valid")) {
			 if(targetPage==true) {
                 System.out.println("Login successful as expected for Valid credentials.");
				 myAcc.clickLogout();
				 Assert.assertTrue(true);
			 }
			 else {
                 System.out.println("Expected login to succeed, but failed.");
				 Assert.assertTrue(false);
			 }
		}
		else if(res.equalsIgnoreCase("Invalid")) {
			 if(targetPage==true) {
				 myAcc.clickLogout();
				 Assert.assertTrue(false);
			 }
			 else {
				 Assert.assertTrue(true);
			 }
		}
	}catch(Exception e) {
		Assert.fail();
	}
		logger.info("**** Finished TC003_LoginDDT******");

	}
}
