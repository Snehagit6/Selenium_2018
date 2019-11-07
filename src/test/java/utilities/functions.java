package utilities;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.sql.DriverManager;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import org.openqa.selenium.support.ui.Select;
import property_file_operations.DatafromPropertyFile;

public class functions {
	

	public static WebDriver driver;
	public static DesiredCapabilities dc;
	public static WebElement element;
	public static WebDriverWait wait;
	//Database Username
	private static String username="root";
	//Database Password
	private static  String password="Securepass6!";	
	public functions(WebDriver driver){
		this.driver=driver;
		
	}
	
	@SuppressWarnings("deprecation")
	public  static WebDriver load_driver(String browser,String download_path) throws Exception {
		try{
		switch(browser){
		case "chrome":
		System.setProperty("webdriver.chrome.driver",
				DatafromPropertyFile.ReadfromPropertyfile("./constants_file/constants.properties","ChromeDriver"));
		ChromeOptions options=new ChromeOptions();
		HashMap<String,String> chromepref=new HashMap<String,String>();
		chromepref.put("download.default_directory",download_path);
		options.addArguments("--disable-notifications","--disable-extensions","start-maximized").setAcceptInsecureCerts(true).setExperimentalOption("prefs",chromepref);
//		// Add the WebDriver proxy capability.
//		Proxy proxy = new Proxy();
//		proxy.setHttpProxy("myhttpproxy:3337");
//		options.setCapability("proxy", proxy);
		driver=new ChromeDriver(options);
		
		
		break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver",
					DatafromPropertyFile.ReadfromPropertyfile("./constants_file/constants.properties","FirefoxDriver"));
		dc = DesiredCapabilities.firefox();
		dc.setCapability("marionette", true);
		dc.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
		driver=new FirefoxDriver(dc);
		break;
		case "ie":
			System.setProperty("webdriver.ie.driver",
					DatafromPropertyFile.ReadfromPropertyfile("./constants_file/constants.properties","IEDriver"));
			dc = DesiredCapabilities.firefox();
			
			dc.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
			driver=new InternetExplorerDriver(dc);
			break;
		case "safari":
			DesiredCapabilities dc=DesiredCapabilities.safari();
			dc.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			driver=new SafariDriver(dc);
			break;
		default:
			break;
			}}catch(Exception e){
			throw new  Exception("Invalid browser");
		}
			
return driver;
}
	public static void teardown() throws Exception{

		driver.close();
	}
	/**********************************************************************
	 * PageLoadTimeOut 
	 */
	public static void PageLoadTimeOut(int time){
		
		driver.manage().timeouts().pageLoadTimeout(time,TimeUnit.SECONDS);
	}
	
	
	/*********************************************************************
	 * Method:waitUntilVisible:
	 *  imposes"Explicit Wait" on the web element until its visibility
	 * @param locator
	 * @param element
	 * @return element
	 */
	public static WebElement waitUntilVisible(WebElement element,int time){
		wait=new WebDriverWait(driver,time);
		try{
			wait.until(ExpectedConditions.visibilityOf(element));
		try{
		wait.until(ExpectedConditions.elementToBeClickable(element));
		}catch(Exception e){
			System.out.println("Exception "+e.toString());
		}
		

		}catch(Exception e){
			System.out.println("Exception "+e.toString());
		}
		return element;
	}
	
	/*********************************************************************
	 * Method:VerifyTitle:
	 *  Verifies title of webpage* */
	 public  static void VerifyTitle(String Expectedtitle,int time){
		 PageLoadTimeOut(time);
		 System.out.println("Title of current page : "+driver.getTitle());
		 if(driver.getTitle().trim().equals(Expectedtitle.trim()))
		 System.out.println("Landed to the correct webpage");
		 else
			 System.out.println("Landed to the incorrect webpage"); 
	 }
	 
	 
	 /***********************************************************
	  * Method:typeValues enters values in inputfields
	  * @param locator
	  * @param value
	  */
	 public static  void typeValues(WebElement element,int time,String value){
		 WebElement object=waitUntilVisible(element,time);
		 element.click();
		 element.clear();
		 element.sendKeys(value);
	 }
	 
	 
	 /******************************************
	  * Method:Click
	  * @param locator
	  */
public static void Click(WebElement element,int time){
	element =waitUntilVisible(element,time);
	 element.click();
}


public boolean  VerifyAlert_Actiontaken(int time,String value,String Expectedtitle){
	wait=new WebDriverWait(driver,time);
	
	//if(wait.until(ExpectedConditions.alertIsPresent())!=null){
	try{
	Alert a=driver.switchTo().alert();
	System.out.println("Alert message :: "+a.getText());
	if(a.getText().equals("User or Password is not valid")){
	a.dismiss();
	System.out.println("Wrong Login attempt");
	}
	else{
		
		a.sendKeys(value);
		a.accept();
	}
	return true;
	//System.exit(0);
		}
catch(NoAlertPresentException ae){
	System.out.println("No alert is present");
	VerifyTitle(Expectedtitle,time);
	return false;
}
}

public boolean IsElementPresent(int time,By locator){
	try{
	wait=new WebDriverWait(driver,time);
	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	return true;
	}
	catch(NoSuchElementException e){
		return false;
	}
		
	}


public boolean VerifyAlert(int time){
	wait=new WebDriverWait(driver,time);
	if(wait.until(ExpectedConditions.alertIsPresent())!=null){
		System.out.println("Alert message :: ");
		Alert a=driver.switchTo().alert();
		a.accept();
		
	}
	else
		
		System.out.println("Alert message not present");
	return false;
}



/*************************************
 * Capturescreenshot
 * @param driver
 * @param name
 * @throws IOException
 */
public static  void Capturescreenshot(WebDriver driver,String name) throws IOException{
	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	name=name+"_"+Date_Time();
	FileUtils.copyFile(src, new File("./Screenshot/"+name+".png"));
	
}
public static String Date_Time(){
	/*Date date = Calendar.getInstance().getTime();  
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd_hh_mm_ss");  
    String strDate = dateFormat.format(date);  */
	LocalDateTime now=LocalDateTime.now();
	DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy_HH_mm_ss");
	String Datetime=now.format(format);
	return Datetime;
	}

public 	void mouseHover(int time,By locator){
	wait=new WebDriverWait(driver, time);
	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	Actions a=new Actions(driver);
	a.moveToElement(element).build().perform();
}
//Windows -based authentication
/*******************************
 * 
 * @param username
 * @param password
 */
public void get_url(String username,String password,String url){
	
	driver.get("https://"+username+":"+password+"@"+url);
	
}
/******************************************************************

*method name : Is Element displayed

*Method Description : to verify if any element present and return boolean value

*input parameters : by locator

******************************************************************/


public static boolean Iselementdisplayed(WebElement element){
	boolean result= false;

	
	try{
	if(waitUntilVisible(element,10).isDisplayed()){
		result=true;
	}}catch(Exception e){
		result=false;
	}
	return result;
}

/*************************
*method name : Is Element Enabled

*Method Description : to verify if any element enabled and return boolean value
* @param locator
* @return
*******************************/

public static boolean IselementEnabled(By locator){
	boolean result= false;
	try{WebElement element = driver.findElement(locator);
	if(element.isEnabled())
		result=true;
	}catch(Exception e){
		System.out.println("Element "+ element.getAttribute("innerHTML")+" is not enabled as \n"+e.getMessage());

	}
	return result;
}
/****************************
* 	
* @param locator
* @return
*/
public boolean IselementSelected(WebElement element){
	boolean result= false;
	try{
	if(element.isSelected()){
		result=true;
	}}catch(Exception e){
		result=false;
	}
	return result;
}

public void Select_element(WebElement element,String text){
	Select s=new Select(element);
	s.selectByIndex(0);
	List<WebElement>lst=s.getOptions();
	for(WebElement we:lst){
		if(we.getText().equals(text))
			s.selectByVisibleText(text);
	}	
	s.deselectByVisibleText(text);
//	s.selectByValue("option_value");
//	s.selectByVisibleText("text");

}
/***********************************
 * 
 * @param element
 * @param dropdown_root_elem
 * @param text
 */
public void Bs_select_element(WebElement element,List<WebElement>dropdown_root_elem,String text){
element = waitUntilVisible(element, 10);
if (element!=null){
	element.click();
	List <WebElement> web=dropdown_root_elem;
	for(WebElement w:web){
		if(w.getAttribute("innerHTML").equals(text)){
			w.click();
		break;
		}
}
	}
}
public void Fileupload(String file,WebElement button) throws AWTException{
	Robot r=new Robot();
	StringSelection sel=new StringSelection(file);
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel,null);
	element=waitUntilVisible(button, 10);
	
//		
//	JavascriptExecutor js=(JavascriptExecutor)driver.scrollIntoView("arguments[0]",button);
	r.keyPress(KeyEvent.VK_ENTER);
	r.keyRelease(KeyEvent.VK_ENTER);
	r.keyPress(KeyEvent.VK_CONTROL);
	r.keyPress(KeyEvent.VK_V);
	r.keyRelease(KeyEvent.VK_CONTROL);
	r.keyRelease(KeyEvent.VK_V);
}
	
//	s.selectByValue("option_value");
//	s.selectByVisibleText("text");
/**************************************************
 * 
 * @throws HeadlessException
 * @throws AWTException
 * @throws IOException 
 */
public void window_screenshot(String name) throws HeadlessException, AWTException, IOException{
	BufferedImage bi=new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

	ImageIO.write(bi,"png",new File("./Screenshots/"+name+".png"));
	

}
public void activities(WebElement drag,WebElement drop,String action){
	Actions as=new Actions(driver);
	if(action=="mousehover"){
	Action a =as.moveToElement(element).build();
	a.perform();
	}
	if(action=="dranganddrop")
//	a.dragAndDrop(drag,drop).build().perform();
	as.dragAndDropBy(drag, drag.getLocation().getX(),drag.getLocation().getY());
	//click and hold
	as.clickAndHold(drag).pause(2000).moveToElement(drop).release().build().perform();
	if(action=="contextClick")
	as.contextClick(element).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
	

}
public void handle_multilewindows(){
	String parent=driver.getWindowHandle();
	Set<String>s1=driver.getWindowHandles();
	for(String s:s1)
		if(!parent.equals(s)){
			driver.switchTo().window(s);
			System.out.println(driver.switchTo().window(s).getTitle());
			
	driver.close();
}
	driver.switchTo().defaultContent();
}
public void frames(WebElement element){
	WebDriverWait wait=new WebDriverWait(driver,10);
	try{
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	driver.switchTo().frame(element);
}catch(NoSuchFrameException e){
	System.out.println(e.getMessage());
}
	driver.switchTo().defaultContent();
}
public  void javascript_actions(WebElement element){
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("arguments[0].scrollIntoView();",element);
	//Element not clickable at x /y.
	js.executeScript("window.scrollTo(0,"+element.getLocation().getX()+")");
}

public void verify_broken_link(String url) throws IOException{
	URL u=new URL(url);

	HttpURLConnection huc=(HttpURLConnection)u.openConnection();
	huc.setConnectTimeout(5000);
	huc.connect();
	
	try{
	if(huc.getResponseCode()==200)
		System.out.printf("Response code %s and response message %s",huc.getResponseCode(),huc.getResponseMessage());
	if(huc.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)
		System.out.printf("Response code %s and response message %s",huc.getResponseCode(),huc.getResponseMessage());
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
}
public void brokenlinks() throws IOException{
	List<WebElement>links=driver.findElements(By.tagName("a"));
	System.out.println("total links"+links.size());
	for(int i=0;i<links.size();i++)
	{
		WebElement ele=links.get(i);
		String url=ele.getAttribute("href");
		verify_broken_link(url);
	}
}

public void database_connection(String query) throws SQLException{
	
	try{
		//Step1. Load and Register the JDBC Driver:
	Class.forName(DatafromPropertyFile.ReadfromPropertyfile("./constants_file/constants.properties","MySQL_driver"));
	}catch(Exception e){
		e.printStackTrace();
	}
	//Step2:Establishing Connection:
	Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/data",username,password);
	//Step3:Creating Statement Object:
	
	Statement st= (Statement) con.createStatement();
	ResultSet rs=st.executeQuery(query);
	while(rs.next()){
		System.out.println(rs.getInt(1));
		System.out.println(rs.getString(2));
		System.out.println(rs.getInt(3));
	}
}
public void handle_webtable(By locator){
	
	
	int num_rows=driver.findElements(By.xpath("/table/tbody/tr")).size();
	for(int i=0;i<num_rows;i++){
		int num_cols=driver.findElements(By.xpath("/table/tbody/tr["+i+"]")).size();
		for(int j=0;j<num_cols;j++){
			System.out.println(driver.findElement(By.xpath("/table/tbody/tr["+i+"]/td["+j+"]")).getText());
		}
	}


}
}




