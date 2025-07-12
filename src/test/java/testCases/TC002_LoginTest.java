package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass {

	
	@Test(groups={"Sanity", "Master"})
	public void verify_login() {
		logger.info("*******Starting TC002_LoginTest***************");
		
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			Thread.sleep(1000);
			LoginPage lp = new LoginPage(driver);
			logger.info("Mail enter");

			lp.setEmail(p.getProperty("email"));
			logger.info("Password enter");

			lp.setPassword(p.getProperty("password"));
			logger.info("Click Login");
			Thread.sleep(1000);

			lp.clickLogin();
			Thread.sleep(1000);

			MyAccountPage ap=new MyAccountPage(driver);
			boolean page=ap.isMyPageExist();
			Assert.assertEquals(page, true, "Login Fail");
		}
		catch(Exception e) {
			e.printStackTrace();
		    logger.error("Login Test Failed due to: " + e.getMessage());
		    Assert.fail("Exception occurred: " + e.getMessage());
			Assert.fail();
		}
		
		logger.info("TC002_LoginTest Completed");
	}
}


