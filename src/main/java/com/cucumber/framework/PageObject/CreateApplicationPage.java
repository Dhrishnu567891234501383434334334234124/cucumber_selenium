package com.cucumber.framework.PageObject;

import java.util.List;

import org.apache.bcel.generic.Select;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cucumber.framework.configreader.ObjectRepo;
import com.cucumber.framework.helper.DropDown.DropDownHelper;
import com.cucumber.framework.helper.Javascript.JavaScriptHelper;
import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.helper.Wait.WaitHelper;
import com.cucumber.framework.helper.assertionHelper.VerificationHelper;

public class CreateApplicationPage {

	WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(CreateApplicationPage.class);
	WaitHelper waitHelper;
	DropDownHelper dropDown;
	
	public String Random = RandomStringUtils.randomAlphanumeric(10); 
	
	@FindBy(xpath="//div[@id='root']//div[contains(@class,'createBtn')]")
	WebElement startNewApp;
	
	
	@FindBy(xpath="//div[@id='createAppDialog']//input[contains(@name,'ApplicationId')]")
	WebElement enterApplicationID ;
	
	@FindBy(xpath="//div[@id='createAppDialog']//select[contains(@name,'LocationID')]")
	WebElement selectLocation;
	
	@FindBy(xpath="//*[@id='addBorrower']//following::input[@type=\"text\" and contains(@placeholder,'Enter First Name')]")
	WebElement enterFirstName;
	
	@FindBy(xpath="//*[@id='addBorrower']//following::input[@type=\"text\" and contains(@placeholder,'Enter Last Name')]")
	WebElement enterLastName;
	
	
	public CreateApplicationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		

	}
	public void startNewApp(){
		log.info("Creating New Application....");
		this.startNewApp.click();
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(driver, enterApplicationID,ObjectRepo.reader.getExplicitWait());
	}
	public void createNewAppDetails(){
		log.info("Creating New Application by providing Mandatory Fields....");
		this.enterApplicationID.sendKeys(Random);
		dropDown.SelectUsingVisibleValue(selectLocation, ObjectRepo.reader.getLocation());
		this.enterFirstName.sendKeys()
	}
	
	public void mouseOverOnProduct(int number){
		String fPart = "//*[@id='center_column']/ul/li[";
		String sPart = "]/div/div[2]/h5/a";
		Actions action = new Actions(driver);
		log.info("doing mouse over on: "+number+"..product");
		action.moveToElement(driver.findElement(By.xpath(fPart+number+sPart))).build().perform();
	}
	
	public void clickOnAddToCart(){
		log.info("clickin on add to cart");
		addToCart.click();
	}
	
	public boolean verifyPoductAddedSuccesfully(){
		return VerificationHelper.verifyElementPresent(productAddedSucessfully);
	}
	
	public void clickOnProceedTocheckOut(){
		log.info("clickin on :"+proceedToCheckOut.getText());
		proceedToCheckOut.click();
	}
	
	public void selectColor(String data){
		new JavaScriptHelper(driver).scrollIntoView(driver.findElement(By.xpath("//a[contains(text(),'"+data+"')]/parent::*/preceding-sibling::input[1]")));
		driver.findElement(By.xpath("//a[contains(text(),'"+data+"')]/parent::*/preceding-sibling::input[1]")).click();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void selectSmallSize() {
		log.info("selecting small size..");
		driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_1']")).click();
	}

	public void selectMediumSize() {
		log.info("selecting Medium size..");
		try {
			boolean selected = driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_2']']")).isSelected();
			if (!selected) {
				driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_2']']")).click();
				log.info("checkbox is checked..");
			}
		} catch (Exception e) {
			log.info("checkbox was already checked..");
		}
	}

	public void selectLSize() {
		log.info("selecting Large size..");
		try {
			boolean selected = driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_3']")).isSelected();
			if (!selected) {
				driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_3']")).click();
				log.info("checkbox is checked..");
			}
		} catch (Exception e) {
			log.info("checkbox was already checked..");
		}
	}
	
	public void selectFirstProduct() {
		Actions obj = new Actions(driver);
		log.info("performning mouse over on first product of page..");
		obj.moveToElement(driver.findElements(By.xpath(".//*[@id='center_column']/ul/li")).get(0)).build().perform();
		log.info("clicking on add to basket..");
		driver.findElement(By.xpath(".//*[@id='center_column']/ul/li[1]/div/div[2]/div[2]/a[1]/span")).click();
	}
	
	public int getTotalProducts(){
		return totalProducts.size();
	}
}
