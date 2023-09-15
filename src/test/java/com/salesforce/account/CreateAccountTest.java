package com.salesforce.account;

import com.beust.ah.A;
import com.salesforce.BaseTest;
import com.salesforce.pages.AccountPage;
import com.salesforce.pages.AccountReportPage;
import com.salesforce.pages.EditViewPage;
import com.salesforce.utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.Date;


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
        String accountTitle = accountPage.getAccNameTitle();
        logger.info("new account created as "+ accountTitle);

        accountPage.clickDeleteAcc();
        Assert.assertEquals(accountPage.getRecentAccountsTitle(),"Recent Accounts");
        logger.info(accountTitle + " is deleted");

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
        Assert.assertEquals(accountPage.getCreatedView(),"priya !!!");
    }

    @Test
    public void TC_12editView() {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");
        String newViewName = "TC12 View Name1";
        String updatedViewName = "TC12 View Name2";

        createView(newViewName, "TC12UniqueViewName2");
        logger.info("Created new view");

        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickAccountLink();
        logger.info("Account page displayed");
        Assert.assertEquals(accountPage.getRecentAccountsTitle(),"Recent Accounts");

        accountPage.chooseDropDownList("Recently Viewed Accounts");
        accountPage.chooseDropDownList(newViewName);
        accountPage.clickEditView();
        EditViewPage editViewPge = new EditViewPage(driver);
        editViewPge.giveNewViewName(updatedViewName);
        editViewPge.selectField("Account Name");
        editViewPge.selectOperator("contains");
        editViewPge.addValue("a");
        editViewPge.clickSave();
        deleteView();
    }

    @Test
    public void TC_13mergeAccounts() throws InterruptedException {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        String accountName1 = "TestMergeAccount 1";
        String accountName2 = "TestMergeAccount 2";
        createAccount(accountName1);
        createAccount(accountName2);

        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickAccountLink();
        logger.info("Account page displayed");
        Assert.assertEquals(accountPage.getRecentAccountsTitle(),"Recent Accounts");

        accountPage.clickMergeAcc();
        accountPage.sendValueAccTextBox("TestMergeAccount");
        accountPage.clickAccBtn();
        accountPage.clickOptOne();
        accountPage.clickOptTwo();
        accountPage.clickNext();
        accountPage.clickMergeAccBtn();
        Assert.assertTrue(CommonUtils.isAlertPresent(driver));
        accountPage.confirmMerge();
        Assert.assertEquals(accountPage.getFirstAccName(),accountName1);


        deleteAccount(accountName1);

    }

    private void createAccount(String accountName) {
        WebDriver driver = getDriver();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickAccountLink();
        accountPage.clickNewBtn();
        accountPage.sendAccName(accountName);
        accountPage.selectAccType("Technology Partner");
        accountPage.selectCustomerPriority("High");
        accountPage.clickSaveBtnCreateAcc();
        logger.info("priority selected high");
    }

    private void createView(String viewName, String uniqueViewName) {
        WebDriver driver = getDriver();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickAccountLink();
        accountPage.clickCreateNewView();

        EditViewPage viewPage = new EditViewPage(driver);
        viewPage.setViewName(viewName);
        viewPage.setUniqueViewName(uniqueViewName);
        viewPage.saveView();
    }

    private void deleteView() {
        WebDriver driver = getDriver();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.deleteView();
    }

    private void deleteAccount(String accountName) {

        WebDriver driver = getDriver();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickAccountLink();
        accountPage.openAccDetails(accountName);
        accountPage.clickDeleteAcc();
    }

    @Test
    public void TC_14CreateAccountReport() {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickAccountLink();
        logger.info("Account page displayed");
        accountPage.clickLast30DaysReport();
        logger.info("report page opened in accounts");
        AccountReportPage accountReportPage = new AccountReportPage(driver);
        accountReportPage.clickDateFieldDD();
        accountReportPage.selectCreatedDate();
        accountReportPage.clickFromDate();
        accountReportPage.clickFromToday();
        accountReportPage.clickToDate();
        accountReportPage.clickToToday();
        accountReportPage.clickSaveBtn();
        accountReportPage.switchToPopUp(driver);
        accountReportPage.giveReportName();
        accountReportPage.giveUniqReportName("report222");
        accountReportPage.clickSaveInPopUp();
        // TODO: 9/9/23
        //Assert.assertEquals(accountReportPage.getReportTitle(),"reportName");





    }
}
