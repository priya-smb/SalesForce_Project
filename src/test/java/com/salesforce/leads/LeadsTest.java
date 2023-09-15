package com.salesforce.leads;

import com.salesforce.BaseTest;
import com.salesforce.pages.LeadsPage;
import com.salesforce.pages.LoginPage;
import com.salesforce.pages.UserMenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LeadsTest extends BaseTest {

    @Test
    public void TC_20leadsTab() {
        WebDriver driver = openLoginPage();;
        login(driver);
        logger.info("logged into salesforce application");

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.clickLeads();

        LeadsPage leadsPage = new LeadsPage(driver);
        Assert.assertEquals(leadsPage.getRecentLeadsText(),"Recent Leads");
        logger.info("leads home page is displayed");
    }

    @Test
    public void TC_21leadsSelectView() throws InterruptedException {
        WebDriver driver = openLoginPage();;
        login(driver);
        logger.info("logged into salesforce application");

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.clickLeads();

        LeadsPage leadsPage = new LeadsPage(driver);
        String[] expLeadsOptions = {
                "All Open Leads",
                "My Unread Leads",
                "Recently Viewed Leads",
                "Today's Leads",
                "View - Custom 1",
                "View - Custom 2"
        };

        List<String> actLeadsOptions = leadsPage.getLeadsDDOptions();
        for (String expLeadsOpt:expLeadsOptions) {
            Assert.assertListContainsObject(actLeadsOptions, expLeadsOpt, "missing " + expLeadsOpt+ " item in leads dropdown");
        }
    }

    @Test
    public void TC_22defaultView() throws InterruptedException {
        WebDriver driver = openLoginPage();;
        login(driver);
        logger.info("logged into salesforce application");

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.clickLeads();

        LeadsPage leadsPage = new LeadsPage(driver);
        leadsPage.selectLeadsDDOption("All Open Leads");
        userMenuPage.showUserMenu();
        boolean menuSelected = userMenuPage.selectUserMenu("Logout");
        Assert.assertTrue(menuSelected);

        login(driver);
        userMenuPage.clickLeads();
        leadsPage.clickGo();
        Assert.assertEquals(leadsPage.findSelectedLeadsDDTc22(),"All Open Leads");

    }

    @Test
    public void TC_23todaysLeads() {
        WebDriver driver = openLoginPage();;
        login(driver);
        logger.info("logged into salesforce application");

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.clickLeads();

        LeadsPage leadsPage = new LeadsPage(driver);
        leadsPage.selectLeadsDDOption("Today's Leads");
        Assert.assertEquals(leadsPage.findSelectedLeadsDDTc23(),"Today's Leads");

    }

    @Test
    public void TC_24newButton() {

        WebDriver driver = openLoginPage();;
        login(driver);
        logger.info("logged into salesforce application");

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.clickLeads();

        LeadsPage leadsPage = new LeadsPage(driver);
        leadsPage.clickNew();
        leadsPage.sendLNameValue("ABCD");
        leadsPage.sendCompanyValue("ABCD");
        leadsPage.clickSave();
        Assert.assertEquals(leadsPage.getNewLeadTitle(),"ABCD");


    }
}
