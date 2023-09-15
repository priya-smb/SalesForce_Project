package com.salesforce.login;

import com.salesforce.BaseTest;
import com.salesforce.pages.ForgotPwdPage;
import com.salesforce.pages.HomePage;
import com.salesforce.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {

    protected static Logger logger = LogManager.getLogger(LoginTest.class);

    @Test(description = "Login with empty password")
    public void loginWithEmptyPwd_TC1() {
        WebDriver driver = getDriver();
        driver.get(getProperty("salesforce.url"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUsername(getProperty("username"));
        loginPage.setPassword("");
        loginPage.submit();
        Assert.assertEquals(loginPage.getErrorMessage(), "Please enter your password.");
    }


    @Test()
    public void validLogin_TC2() {
        WebDriver driver = getDriver();
        driver.get(getProperty("salesforce.url"));

        login(driver);

        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.userMenuDDBtn.getText(), "Priyanka Selenium");

    }

    @Test()
    public void checkRememberMe_TC3() throws InterruptedException {
        logger.info("checkRememberMe_TC3 started");
        WebDriver driver = BaseTest.getDriver();
        driver.get(getProperty("salesforce.url"));

        LoginPage loginPage = login(driver, true);
        HomePage homePage = new HomePage(driver);
        homePage.showUserMenu();
        homePage.logout();
        Thread.sleep(1000);
        Assert.assertTrue(loginPage.isRememberMeSelected());

        loginPage.clearRememberMe();
        Assert.assertEquals(loginPage.getUsername(), "");
    }


    @Test()
    public void forgotPwd_TC4A() {
        WebDriver driver = getDriver();
        driver.get(getProperty("salesforce.url"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPwd();

        ForgotPwdPage forgotPwdPage = new ForgotPwdPage(driver);
        forgotPwdPage.setUsername(getProperty("username"));
        forgotPwdPage.submit();

        Assert.assertEquals(forgotPwdPage.checkEmailMsg.getText(), "Check Your Email");
    }

    @Test()
    public void invalidCredentials_TC4B() {
        WebDriver driver = getDriver();
        driver.get(getProperty("salesforce.url"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUsername("123");
        loginPage.setPassword("22131");
        loginPage.submit();

        Assert.assertEquals(loginPage.getErrorMessage(), "Please check your username and password. If you still can't log in, contact your Salesforce administrator.");
    }

    @Test
    public void testSample() {
        WebDriver driver = getDriver();

    }
}
