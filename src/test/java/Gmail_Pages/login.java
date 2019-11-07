package Gmail_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.functions;

public class login { 
	WebDriver driver;
	public login(WebDriver driver){
		this.driver=driver;
	}
	@FindBy(xpath="//*[@id='identifierId']")
	WebElement username;
	@FindBy(xpath="//span[contains(text(),'Next')]")
	WebElement usernext;
	@FindBy(xpath="//input[@type='password']")
	WebElement password;
	@FindBy(xpath="//*[@id='passwordNex']/content/span")
	WebElement passwordnext;
	public void login() throws InterruptedException{
		functions.typeValues(username,30,"snehatech611@gmail.com");
		functions.Click(usernext,30);
		functions.typeValues(password, 30,"XXXXX");
		functions.Click(passwordnext,30);
		Thread.sleep(5000);

		
	
	}

}
