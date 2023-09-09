package com.salesforce.account;

import com.beust.ah.A;
import com.salesforce.BaseTest;
import com.salesforce.pages.AccountPage;
import com.salesforce.utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Instant;


public class CreateAccountTest extends BaseTest {

    @Test
    public void TC10_createAccount() throws InterruptedException {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickAccountLink();
        logger.info("Account page displayed");
        Assert.assertEquals(accountPage.getRecentAccountsTitle(),"Recent Accounts");

        accountPage.clickNewBtn();
        accountPage.sendAccName("Priya ***");

        accountPage.selectAccType("Technology Partner");
        logger.info("Account Type selected as technology partner");

        accountPage.selectCustomerPriority("High");
        logger.info("priority selected high");

        accountPage.clickSaveBtnCreateAcc();
        Assert.assertEquals(accountPage.getAccNameTitle(),"Priya ***");
        logger.info("new account created as "+ accountPage.getAccNameTitle());

        accountPage.clickDeleteAcc();
        Assert.assertEquals(accountPage.getRecentAccountsTitle(),"Recent Accounts");
        logger.info(accountPage.getAccNameTitle()+" is deleted");

    }

    @Test
    public void TC_11CreateNewView() {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickAccountLink();
        logger.info("Account page displayed");
        Assert.assertEquals(accountPage.getRecentAccountsTitle(),"Recent Accounts");

        accountPage.clickCreateNewView();
        logger.info("create view account pge opened");
        accountPage.sendValueToViewName("priya !!!");
        logger.info("view name entered");
        accountPage.sendValueToUniqueName("automation123");
        logger.info("unique view name entered");
        accountPage.ClickSaveBtnCreateView();
        logger.info("Newly added View is displayed in the account view list");
        // TODO: 9/8/23 Assert
        //Assert.assertEquals(accountPage.getCreatedView(),"priya !!!");
    }

    @Test
    public void TC_12editView() throws InterruptedException {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickAccountLink();
        logger.info("Account page displayed");
        Assert.assertEquals(accountPage.getRecentAccountsTitle(),"Recent Accounts");
        accountPage.chooseDropDownList();
        Thread.sleep(2000);

    }

    @Test
    public void TC_13mergeAccounts() throws InterruptedException {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickAccountLink();
        logger.info("Account page displayed");
        Assert.assertEquals(accountPage.getRecentAccountsTitle(),"Recent Accounts");

        accountPage.clickMergeLink();
        Thread.sleep(2000);
        accountPage.sendValueToFindAccTextBox("Priya");
        Thread.sleep(2000);
        accountPage.clickFindAccBtn();
        Thread.sleep(2000);
        accountPage.clickOptOne();
        accountPage.clickOptTwo();
        accountPage.clickNext();
        accountPage.clickMergeAccBtn();

    }
}