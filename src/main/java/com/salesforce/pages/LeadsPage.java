package com.salesforce.pages;

import com.salesforce.utils.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class LeadsPage {
    WebDriver driver;

    @FindBy(xpath = "//h3[normalize-space()='Recent Leads']")
    private WebElement recentLeads;

    @FindBy(id = "fcf")
    private WebElement leadsDD;

    //tc-22
    @FindBy(xpath = "//input[@value=' Go! ']")
    private WebElement goBtn;

    @FindBy(id = "00BHu00000GsNJ8_listSelect")
    private WebElement leadsDDAfterGo;

    //tc24
    @FindBy(xpath = "//input[@value=' New ']")
    private WebElement newBtn;

    @FindBy(id ="name_lastlea2")
    private WebElement lastName;

    @FindBy(id = "lea3")
    private WebElement company;

    @FindBy(xpath = "//td[@id='bottomButtonRow']//input[1]")
    private WebElement save;

    @FindBy(xpath = "//h2[@class='topName']")
    private WebElement newLeadActText;

    public LeadsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getRecentLeadsText() {
        return recentLeads.getText();
    }

    public List<String> getLeadsDDOptions() {
        List<String> leads = new ArrayList<>();
        Select getLeads = new Select(leadsDD);

        List<WebElement> leadsOptions = getLeads.getOptions();
        for (WebElement leadsOpt:leadsOptions){
            leads.add(leadsOpt.getText());
        }
        return leads;
    }

    public void selectLeadsDDOption(String leadsOptionText) {
        Select sltLeads = new Select(leadsDD);
        sltLeads.selectByVisibleText(leadsOptionText);

    }

    public void clickGo() {
        goBtn.click();
    }

    public String findSelectedLeadsDDTc22() {
        Select sltLeads = new Select(leadsDDAfterGo);
        return  sltLeads.getFirstSelectedOption().getText();
    }

    public String findSelectedLeadsDDTc23() {
        Select sltLeads = new Select(leadsDD);
        return  sltLeads.getFirstSelectedOption().getText();
    }

    //tc24
    public void clickNew() {
        newBtn.click();
    }

    public void sendLNameValue(String lastnameTxt) {
        lastName.sendKeys(lastnameTxt);
    }

    public void sendCompanyValue(String companyTxt) {
        company.sendKeys(companyTxt);
    }

    public void clickSave() {
        save.click();
    }

    public String getNewLeadTitle() {
        return newLeadActText.getText();
    }
}
