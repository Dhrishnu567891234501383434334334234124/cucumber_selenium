package com.cucumber.framework.configreader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.cucumber.framework.configuration.browser.BrowserType;
import com.cucumber.framework.configuration.browser.ChromeBrowser;
import com.cucumber.framework.configuration.browser.FirefoxBrowser;
import com.cucumber.framework.configuration.browser.HtmlUnitBrowser;
import com.cucumber.framework.configuration.browser.IExploreBrowser;
import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.helper.TestBase.TestBase;

//**********************************************************************************************************
public class ConfigurationReader {
	private final Logger log = LoggerHelper.getLogger(TestBase.class);
    public static ConfigurationReader instance;
    public static final Object lock = new Object();
    public static String propertyFilePath = System.getProperty("user.dir")+
            "\\src\\main\\resources\\configfile\\config.properties";
    static Properties prop = new Properties();
    public static WebDriver driver; 
    


    //Create a Singleton instance. We need only one instance of Property Manager.
    public static ConfigurationReader getInstance () {
        if (instance == null) {
            synchronized (lock) {
                instance = new ConfigurationReader();
                instance.loadData();
            }
        }
        return instance;
    }

    public static void loadData() {
        //Declare a properties object
       // Properties prop = new Properties();

        //Read configuration.properties file
        try {
            prop.load(new FileInputStream(propertyFilePath));
        } catch (IOException e) {
            System.out.println("Configuration properties file cannot be found");
        }

        //Get properties from configuration.properties
   }
	public WebDriver getBrowserObject(BrowserType bType) throws Exception {
		try {
			log.info(bType);

			switch (bType) {

			case Chrome:
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				return chrome.getChromeDriver(chrome.getChromeCapabilities());

			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				return firefox.getFirefoxDriver(firefox.getFirefoxCapabilities());

			case HtmlUnitDriver:
				HtmlUnitBrowser htmlUnit = HtmlUnitBrowser.class.newInstance();
				return htmlUnit.getHtmlUnitDriver(htmlUnit.getHtmlUnitDriverCapabilities());

			case Iexplorer:
				IExploreBrowser iExplore = IExploreBrowser.class.newInstance();
				return iExplore.getIExplorerDriver(iExplore.getIExplorerCapabilities());
			default:
				throw new Exception(" Driver Not Found : " + new PropertyFileReader().getBrowser());
			}
		} catch (Exception e) {
			log.equals(e);
			throw e;
		}
	}
public void setUpDriver(BrowserType bType) throws Exception {
		
		driver = getBrowserObject(bType);
		log.debug("InitializeWebDrive : " + driver.hashCode());
		driver.manage().timeouts().pageLoadTimeout(ConfigurationReader.getInstance().getPageLoadTimeOut(), TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(ConfigurationReader.getInstance().getImplicitWait(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public void before() throws Exception {
//		ObjectRepo.reader = new PropertyFileReader();
		setUpDriver(ConfigurationReader.getInstance().getBrowser());
		log.info(ConfigurationReader.getInstance().getBrowser());
	}

    public String getUserName() {
		return prop.getProperty("Username");
	}

	public String getPassword() {
		return prop.getProperty("Password");
	}

	public String getWebsite() {
		return prop.getProperty("Website");
	}

	public int getPageLoadTimeOut() {
		return Integer.parseInt(prop.getProperty("PageLoadTimeOut"));
	}

	public int getImplicitWait() {
		return Integer.parseInt(prop.getProperty("ImplcitWait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(prop.getProperty("ExplicitWait"));
	}

	public String getDbType() {
		return prop.getProperty("DataBase.Type");
	}

	public String getDbConnStr() {
		return prop.getProperty("DtaBase.ConnectionStr");
	}

	public BrowserType getBrowser() {
		return BrowserType.valueOf(prop.getProperty("Browser"));
	}
	public String getLocation() {
		return prop.getProperty("Location");
	}
}