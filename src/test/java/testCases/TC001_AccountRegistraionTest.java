package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistraionTest extends BaseClass{

	 @Test(groups={"Regression","Master"})
	 void verify_account_registraion() 
	 {
		 logger.info("************ Starting TC001_AccountRegistraionTest***************");
		 
		 try {
		  HomePage homepage = new HomePage(driver);
		    homepage.clickMyAccount();
		    logger.info("Clicked on MyAccount Link");

		  homepage.clickRegister();
		  logger.info("Clicked on Register Link");
		  AccountRegistrationPage regPage=new AccountRegistrationPage(driver);
		  logger.info("Providing Custom Deatil.....");
		  regPage.setFirstName(randomString().toUpperCase());
		  regPage.setLasttName(randomString().toUpperCase());
		  logger.info("Providing Email.....");
		  regPage.setEmail(randomString() + "@gmail.com");
		//  regPage.setTelephone(randomNumber());
		  logger.info("Providing Password.....");

		  String password=randomAlphaNumeric();
		  regPage.setPassword(password);
		 // regPage.setConfirmPassword(password);
		  logger.info("Policy.....");
		  regPage.setPrivacyPolicy();
		  logger.info("Continue.....");

		  regPage.clickContinue();
		  
		  logger.info("Validateing Confirmation Message.....");
		  String conmsg=regPage.getConfirmMessage();
		  System.out.println(conmsg);
		  Assert.assertEquals(conmsg, "Your Account Has Been Created!");
		 }
		 catch(Exception e) {
			 logger.error("Test Failed....");
			 logger.error("Exception: " + e.getMessage());
			    e.printStackTrace();
			 logger.debug("Debug Logs.....");
			 Assert.fail();
		 }
		 
		 logger.info("Test Case Finished....");
	 }
	 

}
