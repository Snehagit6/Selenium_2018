package Gmail_Test;

import java.io.IOException;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Gmail_Pages.login;
import utilities.functions;

public class Reading_emails {

	public static WebDriver driver;
	login log=PageFactory.initElements(driver,login.class);
	
	@BeforeTest
	@Parameters({"browser","path","url"})
	public void launchingWebsite(String browser,String download_path,String url) throws Exception {
//		 ExtentHtmlReporter reporter=new ExtentHtmlReporter("./test-output/report.html");
////		ExtentReports reports=new ExtentReports();
////		reports.attachReporter(reporter);
//		ExtentTest logger=reports.createTest("Launchwebsite");
//	    logger.log(Status.INFO,"Launch website");
	    driver=functions.load_driver(browser, download_path);
	    driver.get(url);
	    
//	    logger.log(Status.PASS,"Website has been launched");
	    
	}
	
	@Test(priority=1)
	public void  Login() throws InterruptedException{
	log.login();


		
	}
		
	@AfterMethod
	public void take_screenshot_failure(ITestResult result) throws IOException{
		if(ITestResult.FAILURE==result.getStatus()){
			functions.Capturescreenshot(driver,"Login_failure");
			driver.close();
			
		}
		
	}
	
	@AfterTest
	public void teardown(){
		driver.close();
	}

}
