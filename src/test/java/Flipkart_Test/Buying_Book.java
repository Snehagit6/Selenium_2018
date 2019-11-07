package Flipkart_Test;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//import ScreenshotLibrary.TakingScreenshot;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.functions;
import Flipkart_Pages.Login_Page;
import property_file_operations.DatafromPropertyFile;


/**************************************
 * 
 * @author Sneha Mazumder
 *
 */
public class Buying_Book {
	WebDriver driver;
	Logger log;

	
	@BeforeTest(description="Initializing browser and launching website")
	@Parameters({"browser","path","url"})
	public void launchingWebsite(String browser,String download_path,String url) throws Exception {
			log=Logger.getLogger("Buying_Book");
			driver=functions.load_driver(browser,download_path);
			System.out.println(driver);
		    driver.get(url);
		    log.info("");
		    driver.manage().window().maximize();
		    functions.Capturescreenshot(driver,"Flipkart Page");
		    
		    
//		    logger.log(Status.PASS,"Website has been launched");
		    
		}
	

	@Test(priority=1)
	@Parameters({"username","password"})
	public void Login(String username,String password) throws IOException, InterruptedException {
//		functions.VerifyTitle(DatafromPropertyFile.
//				ReadfromPropertyfile("./constants_file/constants.properties", "Title"),10);
		Login_Page login=PageFactory.initElements(driver, Login_Page.class);
		login.login(username,password);
		 log.info("");
	
	}
	@AfterMethod
	public void take_screenshot_failedtestcase(ITestResult result){
		if(ITestResult.FAILURE==result.getStatus()){
			try{
				functions.Capturescreenshot(driver, result.getName());
				 log.info("");
			}
			catch(Exception e){
				System.out.println("Exception while taking screenshot"+e.getMessage());
			}
		}
	}
	@AfterTest
	
	public void teardown(){
		driver.close();
	}

//	@Test(priority = 2)
//	public void SelectBooks() throws IOException, InterruptedException {
//		Actions actions = new Actions(driver);
//		// WebElement
//		// Booksxpath=driver.findElement(By.xpath(".//*[@id='container']/div/header/div[2]/div/ul/li[7]/a/span"));
//		// WebElement Booksxpath=driver.findElement(By.linkText("Books &
//		// More"));
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		String x = Function("XpathBooknMore");
//		WebElement Booksxpath = driver.findElement(By.xpath(x));
//		actions.moveToElement(Booksxpath).build().perform();// mouseoverBooks.perform();
//		//actions.moveToElement(Booksxpath).dragAndDrop(Booksxpath,Book);
//		Thread.sleep(1000);
//		Date s=new Date();
////		TakingScreenshot.Capturescreenshot(driver, "Books");
//		Thread.sleep(3000);
//		String y = Function("XpathLitnFict");
//		driver.findElement(By.xpath(y)).click();
////		TakingScreenshot.Capturescreenshot(driver, "TypeOfBooks");
//		Thread.sleep(3000);
//		// ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)");
//		WebElement Book = driver.findElement(By.cssSelector("a[title^='PMR: I Too'"));
//		// WebElement
//		// Book=driver.findElement(By.xpath(".//*[@id='container']/div/div[1]/div/div[2]/div/div[2]/div/div[3]/div[1]/div[1]/div[3]/div/a[2]"));
//		String parent = driver.getWindowHandle();
//		// System.out.pri ntln("Parent window id:"+parent);
//		System.out.println("Before switching Title is : " + driver.getTitle());
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Book);
////		TakingScreenshot.Capturescreenshot(driver, "ScrolledTo");
//		Thread.sleep(3000);
//		Book.click();
////		TakingScreenshot.Capturescreenshot(driver, "ClickingBook");
//		Set<String> allWindows = driver.getWindowHandles();
//		// int count=allWindows.size();
//		Iterator<String> itr = allWindows.iterator();
//		while (itr.hasNext()) {
//			String childwindow = itr.next();
//			if (!parent.equalsIgnoreCase(childwindow)) {
//				driver.switchTo().window(childwindow);
//
//				WebElement bkcart = driver.findElement(By.cssSelector("img[alt^='PMR: I'][class^='sfescn']"));
//				actions.moveToElement(bkcart).build().perform();
//				Thread.sleep(1000);
////				TakingScreenshot.Capturescreenshot(driver, "ImageofBook");
//				Thread.sleep(3000);
//				driver.findElement(By.cssSelector("button._2AkmmA._2Npkh4._2MWPVK")).click();
////				TakingScreenshot.Capturescreenshot(driver, "AddedToCart");
//				Thread.sleep(3000);
//
//			}
//		}
//		System.out.println("After switching title :" + driver.getTitle());
//		driver.switchTo().window(parent);
//		Thread.sleep(3000);
//		driver.close();
//		/*
//		 * for(String child:allWindows){ if(parent!=(child)){
//		 * //driver.switchTo().window(child).getTitle();
//		 * System.out.println("Title of the webpage :"+driver.switchTo().window(
//		 * child).getTitle()); //WebElement bkcart=driver.findElement(By.
//		 * cssSelector("img[alt^='PMR: I'][class^='sfescn']"));
//		 * //actions.moveToElement(bkcart).build().perform();
//		 * Thread.sleep(5000); //driver.close(); } } //@Test(priority=2)
//		 * //public void AddToCart(){ /*driver.get(
//		 * "https://www.flipkart.com/pmr-too-had-love-story-r-j/p/itmemzgqcf6zfpgz?pid=9780143418764&srno=b_1_1&otracker=browse&lid=LSTBOK9780143418764MRFUZP&iid=14654996-13ac-473a-9bb4-28324c5fa401.9780143418764.SEARCH"
//		 * ); WebElement bkcart=driver.findElement(By.
//		 * cssSelector("img[alt^='PMR: I'][class^='sfescn']"));
//		 * actions.moveToElement(bkcart).build().perform();}
//		 */
//
//	}
}
