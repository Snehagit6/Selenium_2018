package Flipkart_Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import utilities.functions;

public class Login_Page {
WebDriver driver;
public Login_Page(WebDriver driver) {
	// TODO Auto-generated constructor stub
	this.driver=driver;
}

@FindBy(how=How.XPATH,using="//a[contains(text(),'Login & Signup')]")
@CacheLookup
WebElement login_link;

@FindBy(how=How.XPATH,using="/html/body/div[2]/div/div/button")
@CacheLookup
WebElement login_button;

@FindBy(how=How.XPATH,using="/html/body/div[2]/div/div/div/div/div[2]/div/form/div[1]/input")
@CacheLookup
WebElement email;

@FindBy(how=How.XPATH,using="/html/body/div[2]/div/div/div/div/div[2]/div/form/div[2]/input")
@CacheLookup
WebElement password;

@FindBy(how=How.XPATH,using="/html/body/div[2]/div/div")
@CacheLookup
List<WebElement> drp_root;
public void login(String username,String pass) throws IOException{
	
	
	if (functions.waitUntilVisible(login_link,10)!=null)
	functions.waitUntilVisible(login_link,10).click();
	functions.typeValues(email, 10,username);
	functions.Capturescreenshot(driver, "Email");
	functions.typeValues(password, 10,pass);
	functions.Capturescreenshot(driver, "Password");
	functions.Click(login_button, 10);
	}

	
	
}



