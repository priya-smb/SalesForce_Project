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
    private WebElement pipelineText;






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
        return pipelineText.getText();
    }







    //driver.findElement(By.id("opp3")).sendKeys("Burlington Textiles Weaving Plant Generator");
      //  driver.findElement(By.id("opp4")).sendKeys("Priyanka Bayye");
       // driver.findElement(By.id("opp9")).click();
        //driver.findElement(By.className("calToday")).click();
    //Select slt = new Select(driver.findElement(By.id("opp11")));
      //  slt.selectByVisibleText("Perception Analysis");
        //driver.findElement(By.id("opp12")).sendKeys("");
    //Select sltLead = new Select(driver.findElement(By.id("opp6")));
      //  sltLead.selectByVisibleText("Phone Inquiry");
        //driver.findElement(By.id("opp17")).sendKeys("");
       // driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[1]")).click();
}
