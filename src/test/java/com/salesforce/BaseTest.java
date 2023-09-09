package com.salesforce;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.salesforce.constants.FileConstants;
import com.salesforce.pages.LoginPage;
import com.salesforce.utils.ConfigUtil;
import com.salesforce.utils.FileUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;


//	Requirements
//	Cross browser support
//	Parallel support -
//	Proper reporting - Accurate Assertion, Screenshots
//	Support of Logs in the framework

@Listeners({TestListener.class})
public class BaseTest {
    protected Logger logger = LogManager.getLogger(getClass().getName());

    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    private Properties testData;


    //Extent Report

    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = null;
    public ExtentTest test = null;


    public WebDriver openLoginPage() {
        WebDriver driver = getDriver();
        driver.get(getProperty("salesforce.url"));
        return driver;
    }

    @BeforeTest
    public void setDriver() {
        spark = new ExtentSparkReporter(new File(FileConstants.REPORT_PATH));
        extent.attachReporter(spark);
        WebDriver driver = BaseTest.getBrowserType("chrome", false);
        threadLocalDriver.set(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        this.testData = FileUtils.readAllProperties(FileConstants.LOGIN_TESTDATA_FILE_PATH2);

    }

    @BeforeMethod
    public void setup(Method name) {
        test = extent.createTest(name.getName());
    }

    public String getProperty(String key) {
        return this.testData.getProperty(key);
    }

    public WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    @AfterTest
    public void removeDriver() {
        getDriver().close();
        threadLocalDriver.remove();
        extent.flush();
    }

    public static WebDriver getBrowserType(String browserName, boolean headless) {
        WebDriver driver;


        String browserType = browserName.toLowerCase();

        switch (browserType) {
            case "chrome":
                if (headless) {
                    ChromeOptions co = new ChromeOptions();
                    co.addArguments("--headless");
                    driver = new ChromeDriver(co);
                } else {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");

                    DesiredCapabilities dc = new DesiredCapabilities();
                    dc.setCapability(ChromeOptions.CAPABILITY, options);
                    options.merge(dc);

                    HashMap<String, Object> chromePref = new HashMap<>();
                    String downloadPath = ConfigUtil.getProperty("download.path");
                    chromePref.put("download.default_directory", downloadPath);
                    options.setExperimentalOption("prefs", chromePref);

                    driver = new ChromeDriver(options);
                }
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "safari":
                driver = new SafariDriver();
                break;
            case "edge":
                ;
                driver = new EdgeDriver();
                break;

            default:
                driver = null;
                break;
        }
        return driver;
    }


    public LoginPage login(WebDriver driver, boolean rememberMe) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUsername(getProperty("username"));
        loginPage.setPassword(getProperty("password"));
        if (rememberMe) {
            loginPage.enableRememberMe();
        } else {
            loginPage.disableRememberMe();
        }

        loginPage.submit();
        return loginPage;

    }

    public LoginPage login(WebDriver driver) {

        return login(driver, false);
    }

}