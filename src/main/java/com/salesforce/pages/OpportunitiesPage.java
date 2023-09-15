package com.salesforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OpportunitiesPage {

    @FindBy(xpath = "//select[@id='fcf']//option[1]")
    private WebElement myOptys;

    @FindBy(xpath = "//input[@value=' New ']")
    private WebElement newBtn;

    @FindBy(id ="opp3")
    private WebElement optnyName;

    @FindBy(id = "opp4")
    private WebElement accName;

    @FindBy(id = "opp9")
    private WebElement closeDate;

    @FindBy(className = "calToday")
    private WebElement todayCloseDate;

    @FindBy(id ="opp11" )
    private WebElement stageDropDown;

    @FindBy(id = "opp12")
    private WebElement probability;

    @FindBy(id = "opp6")
    private WebElement leadSourceDD;

    @FindBy(id = "opp17")
    private WebElement primaryCampaignSource;

    @FindBy(xpath = "//td[@id='bottomButtonRow']//input[1]")
    private WebElement saveBtn;

    //validation
    @FindBy(className = "pageDescription")
    private WebElement newOpytText;

    //TC-17
    @FindBy(xpath = "//a[contains(text(),'Opportunity Pipeline')]")
    private WebElement optyPipeline;

    @FindBy(xpath = "//h1[@class='noSecondHeader pageType']")
    private WebElement pipelineTitle;

    //TC-18

    @FindBy(xpath = "//a[contains(text(),'Stuck Opportunities')]")
    private WebElement StuckOpportunities;

    @FindBy(xpath = "//h1[normalize-space()='Stuck Opportunities']")
    private WebElement stuckOpportunitiesTitle;

    //TC-19
    @FindBy(id = "quarter_q")
    private WebElement intervalDD;

    @FindBy(id = "open")
    private WebElement includeDD;

    @FindBy(xpath = "//input[@value='Run Report']")
    private WebElement runReport;

    @FindBy(xpath = "//h1[normalize-space()='Opportunity Report']")
    private WebElement reportTitle;






    public OpportunitiesPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public String getMyOptyTitle() {
        return myOptys.getText();
    }

    public void clickNewBtn() {
        newBtn.click();
    }

    public void selectOpptyName(String OptyNameText) {
        optnyName.sendKeys(OptyNameText);
    }
    public void sendAccName(String accNameText) {
        accName.sendKeys(accNameText);
    }
    public void clickCloseDate() {
        closeDate.click();
    }
    public void clickToday() {
        todayCloseDate.click();
    }

    public void sltStageDropDown(String stageOptionText) {
        Select sltStageDD = new Select(stageDropDown);
        sltStageDD.selectByVisibleText(stageOptionText);
    }

    public void sendProbability(String probabilityValue) {
        probability.sendKeys(probabilityValue);
    }

    public void sltLeadSourceDD(String leadSourceText) {
        Select sltLeadSource = new Select(leadSourceDD);
        sltLeadSource.selectByVisibleText(leadSourceText);
    }

    public void sendPrimaryCampaign(String campaignValue) {
        primaryCampaignSource.sendKeys(campaignValue);
    }

    public void clickSave() {
        saveBtn.click();
    }

    public String getNewOptyText() {
       return newOpytText.getText();
    }

    //TC-17
    public void clickpipeline() {
        optyPipeline.click();
    }

    public String getPipelineText() {
        return pipelineTitle.getText();
    }

    //TC-18

    public void clickStuckOpportunities() {
        StuckOpportunities.click();
    }

    public String getStuckOpportunitiesTitle() {
        return stuckOpportunitiesTitle.getText();
    }

    //TC-19
    public void selectInterval(String intervalDDOption) {
        Select sltInterval = new Select(intervalDD);
        sltInterval.selectByVisibleText(intervalDDOption);
    }

    public void selectInclude(String includeDDOption) {
        Select sltInterval = new Select(includeDD);
        sltInterval.selectByVisibleText(includeDDOption);
    }

    public void clickRunReport() {
        runReport.click();
    }

    public String getReportTitle() {
        return reportTitle.getText();

    }
}
