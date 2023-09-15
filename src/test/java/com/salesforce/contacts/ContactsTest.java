package com.salesforce.contacts;

import com.salesforce.BaseTest;
import com.salesforce.pages.ContactsPage;
import com.salesforce.pages.UserMenuPage;
import com.salesforce.utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Date;

public class ContactsTest extends BaseTest {

    @Test
    public void TC_25createNewContact() {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.clickContacts();
        logger.info("contacts page displayed");

        ContactsPage contactsPage = new ContactsPage(driver);
        contactsPage.clickNew();
        contactsPage.sendLName("Bayye123");
        contactsPage.sendAccName("priyanka bayye");
        contactsPage.clickSave();
        Assert.assertEquals(contactsPage.getAccNewContact(),"Bayye123");
        logger.info("new contact created");
    }

    @Test
    public void TC_26createNewViewInContactPage() {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.clickContacts();
        logger.info("contacts page displayed");
        ContactsPage contactsPage = new ContactsPage(driver);
        contactsPage.clickCreateNewView();
        Date curDate = new Date();
        String viewContactName = "contact priya - " + curDate.getTime();
        contactsPage.sendViewName(viewContactName);
        contactsPage.sendViewUniqName("unique_contact_priya");
        contactsPage.clickSaveBtn();
        Assert.assertEquals(contactsPage.selectContactViewDD(),viewContactName);
        logger.info("new contact view is created");
    }

    @Test
    public void TC_27checkRecentlyCreatedContact() throws InterruptedException {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.clickContacts();
        logger.info("contacts page displayed");

        ContactsPage contactsPage = new ContactsPage(driver);
        contactsPage.selectRecentlyDD("Recently Created");
        // TODO: 9/11/23  Assert.assertEquals(contactsPage.recentCnt(),"Bayye123");

    }

    @Test
    public void TC_28checkMyContactsView() {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.clickContacts();
        logger.info("contacts page displayed");

        ContactsPage contactsPage = new ContactsPage(driver);
        contactsPage.sltMycontsDD("My Contacts");
        contactsPage.clickGoBtn();
        logger.info("my contacts displayed");
        contactsPage.clickContactName();
        // TODO: 9/11/23  Assert.assertEquals(contactsPage.getAllContTitle(),"Mr.Tim Barr");


    }

    @Test
    public void TC_29viewContact() throws InterruptedException {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.clickContacts();
        logger.info("contacts page displayed");

        ContactsPage contactsPage = new ContactsPage(driver);
        contactsPage.clickContactName();
        Assert.assertEquals(contactsPage.getCntTitle(),"Bayye123");

    }

    @Test
    public void TC_30errorMsg() throws InterruptedException {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.clickContacts();
        logger.info("contacts page displayed");

        ContactsPage contactsPage = new ContactsPage(driver);
        contactsPage.clickCreateNewView();
        contactsPage.sendViewUniqName("EFGH");
        contactsPage.clickSaveBtn();
        Assert.assertEquals(contactsPage.getErrorMsg(),"Error: You must enter a value");
    }

    @Test
    public void TC_31cancelBtn() throws InterruptedException {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.clickContacts();
        logger.info("contacts page displayed");

        ContactsPage contactsPage = new ContactsPage(driver);
        contactsPage.clickCreateNewView();
        contactsPage.sendViewName("XYZ");
        contactsPage.sendViewUniqName("123");
        contactsPage.clickCancel();
        Assert.assertFalse(contactsPage.checkViewDD("XYZ"));
    }

    @Test
    public void TC_32checkSaveNewButton() {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.clickContacts();
        logger.info("contacts page displayed");

        ContactsPage contactsPage = new ContactsPage(driver);
        contactsPage.clickNewBtn();
        contactsPage.sendLName("Indian");
        contactsPage.sendAccName("Global Media");
        contactsPage.clickSaveNewBtn();
        Assert.assertTrue(false);
    }
}
