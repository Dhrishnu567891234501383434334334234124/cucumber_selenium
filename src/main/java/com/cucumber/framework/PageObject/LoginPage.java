package com.cucumber.framework.PageObject;

import java.io.Reader;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cucumber.framework.configreader.ConfigurationReader;
import com.cucumber.framework.configreader.ObjectRepo;
import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.helper.TestBase.TestBase;
import com.cucumber.framework.helper.Wait.WaitHelper;
import com.cucumber.framework.helper.genericHelper.GenericHelper;


public class LoginPage{

	//WebDriver driver 
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	WaitHelper waitHelper;

	@FindBy(name="Email")
	@CacheLookup
	WebElement txtEmail;

	@FindBy(name="Password")
	@CacheLookup
	WebElement txtPassword;
	//button[text()='Login']

	@FindBy(xpath = "//button[text()='Login']")
	@CacheLookup
	WebElement buttonLogin;


	public LoginPage() {
		WebDriver driver = null;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(ConfigurationReader.driver);
		//waitHelper.waitForElement(driver, txtEmail,ObjectRepo.reader.getExplicitWait());
		waitHelper.waitForElement(ConfigurationReader.driver, txtEmail,ConfigurationReader.getInstance().getExplicitWait());
	}

	public void setUserNamePassword(){
		log.info("entering email address....");
		//this.txtEmail.sendKeys(ObjectRepo.reader.getUserName());
		this.txtEmail.sendKeys(ConfigurationReader.getInstance().getUserName());
		//txtEmail.sendKeys(validUsername);
		log.info("entering password....");
		this.txtPassword.clear();
		//this.txtPassword.sendKeys(ObjectRepo.reader.getPassword());
		this.txtPassword.sendKeys(ConfigurationReader.getInstance().getPassword());
		//txtPassword.sendKeys(validPwd);
	}
	public void clickOnLoginButton(){
		log.info("clicking on submit button...");
		this.buttonLogin.click();
	}
	public void closeBrowser() {
		ConfigurationReader.driver.close();
	}

}
