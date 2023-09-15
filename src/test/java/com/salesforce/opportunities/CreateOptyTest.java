package com.salesforce.opportunities;
import com.salesforce.BaseTest;
import com.salesforce.pages.HomePage;
import com.salesforce.pages.OpportunitiesPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.annotation.concurrent.ThreadSafe;

public class CreateOptyTest extends BaseTest {

    protected static Logger logger = LogManager.getLogger(CreateOptyTest.class);

    @Test
    public void TC_15opportunitiesDropDown() throws InterruptedException {

        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        HomePage homePage = new HomePage(driver);
        homePage.selectOptyTab();

        OpportunitiesPage opportunitiesPage = new OpportunitiesPage(driver);
        Assert.assertEquals(opportunitiesPage.getMyOptyTitle(),"All Opportunities");
        logger.info("opportunities page is displayed");
    }

    @Test
    public void TC_16createNewOpty() throws InterruptedException {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        HomePage homePage = new HomePage(driver);
        homePage.selectOptyTab();

        OpportunitiesPage opportunitiesPage = new OpportunitiesPage(driver);
        Assert.assertEquals(opportunitiesPage.getMyOptyTitle(),"All Opportunities");
        logger.info("opportunities page is displayed");

        opportunitiesPage.clickNewBtn();
        opportunitiesPage.selectOpptyName("Burlington Textiles Weaving Plant Generator");
        opportunitiesPage.sendAccName("Priyanka Bayye");
        opportunitiesPage.clickCloseDate();
        opportunitiesPage.clickToday();
        opportunitiesPage.sltStageDropDown("Perception Analysis");
        opportunitiesPage.sendProbability("");
        opportunitiesPage.sltLeadSourceDD("Phone Inquiry");
        opportunitiesPage.sendPrimaryCampaign("");
        opportunitiesPage.clickSave();
        Assert.assertEquals(opportunitiesPage.getNewOptyText(),"Burlington Textiles Weaving Plant Generator");
        logger.info("new opportunity created succesfully");
    }

    @Test
    public void TC_17testOpportunityPipelineReport() {

        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        HomePage homePage = new HomePage(driver);
        homePage.selectOptyTab();

        OpportunitiesPage opportunitiesPage = new OpportunitiesPage(driver);
        Assert.assertEquals(opportunitiesPage.getMyOptyTitle(),"All Opportunities");
        logger.info("opportunities page is displayed");

        opportunitiesPage.clickpipeline();
        Assert.assertEquals(opportunitiesPage.getPipelineText(),"Opportunity Pipeline");
        logger.info("Opportunity Pipeline page is displayed");
    }

    @Test
    public void TC_18testStuckOpportunitiesReport() {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        HomePage homePage = new HomePage(driver);
        homePage.selectOptyTab();

        OpportunitiesPage opportunitiesPage = new OpportunitiesPage(driver);
        Assert.assertEquals(opportunitiesPage.getMyOptyTitle(),"All Opportunities");
        logger.info("opportunities page is displayed");

        opportunitiesPage.clickStuckOpportunities();
        Assert.assertEquals(opportunitiesPage.getStuckOpportunitiesTitle(), "Stuck Opportunities");
        logger.info("Stuck Opportunities report is displayed");

    }

    @Test
    public void TC_19testQuarterlySummaryReport() {
        WebDriver driver = openLoginPage();
        login(driver);
        logger.info("logged into salesforce application");

        HomePage homePage = new HomePage(driver);
        homePage.selectOptyTab();

        OpportunitiesPage opportunitiesPage = new OpportunitiesPage(driver);
        Assert.assertEquals(opportunitiesPage.getMyOptyTitle(),"All Opportunities");
        logger.info("opportunities page is displayed");

        opportunitiesPage.selectInterval("Current and Next FQ");
        opportunitiesPage.selectInclude("All Opportunities");
        opportunitiesPage.clickRunReport();
        Assert.assertEquals(opportunitiesPage.getReportTitle(),"Opportunity Report");
        logger.info("Opportunity Report is displayed");
    }
}
