package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = {"Sanity", "Regression", "Master"})
	@Parameters({"os", "browser"})
	void setup(String os, String browser) throws IOException {
		
		// Load config
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());

		String executionEnv = p.getProperty("execution_env");

		if (executionEnv.equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// Set platform
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("Invalid OS");
				return;
			}

			// Set browser
			switch (browser.toLowerCase()) {
				case "chrome": capabilities.setBrowserName("chrome"); break;
				case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
				case "firefox": capabilities.setBrowserName("firefox"); break;
				default: System.out.println("Invalid Browser"); return;
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		} 
		
		else if (executionEnv.equalsIgnoreCase("local")) {
			switch (browser.toLowerCase()) {
				case "chrome": driver = new ChromeDriver(); break;
				case "edge": driver = new EdgeDriver(); break;
				case "firefox": driver = new FirefoxDriver(); break;
				default: System.out.println("Invalid Browser"); return;
			}
		} 
		
		else {
			System.out.println("Invalid execution_env in config.properties");
			return;
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL1"));
		driver.manage().window().maximize();
	}

	@AfterClass(groups = {"Sanity", "Regression", "Master"})
	void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public String randomString() {
		return RandomStringUtils.randomAlphabetic(5);
	}

	public String randomNumber() {
		return RandomStringUtils.randomNumeric(10);
	}

	public String randomAlphaNumeric() {
		return RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomNumeric(10);
	}

	public static String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
