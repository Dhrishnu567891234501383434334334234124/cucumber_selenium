package com.cucumber.framework.stepdefinition.login;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.seleniumhq.jetty9.server.Authentication.User;
import org.testng.Assert;

import com.cucumber.framework.PageObject.LoginPage;
import com.cucumber.framework.configreader.ConfigurationReader;
import com.cucumber.framework.configreader.ObjectRepo;
import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.helper.TestBase.TestBase;
import com.gargoylesoftware.htmlunit.javascript.host.Map;

import cucumber.api.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginToAutomationPractices{
	private final Logger log = LoggerHelper.getLogger(LoginToAutomationPractices.class);
	LoginPage loginpage;
	@Given("^navigate to application$")
	public void navigate_to_application() throws Throwable {
	//TestBase.driver.get(ObjectRepo.reader.getWebsite());
		System.out.println("driver" +ConfigurationReader.driver);
		ConfigurationReader.driver.get(ConfigurationReader.getInstance().getWebsite());
	}

	@Given("I enter the user Login Credentials")
	public void i_enter_the_user_login_credentials() {
		//loginpage= new LoginPage(TestBase.driver);
		loginpage.setUserNamePassword();
	}

	@When("I Click on Login$")
	public void click_on_login() throws Throwable {
		loginpage.clickOnLoginButton();

	}

	@Given("I close the Browser")
	public void i_close_the_browser() {
		loginpage.closeBrowser();
	}


}
