package com.salesforce.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class AccountReportPage {
    WebDriver driver;

    @FindBy(id ="ext-gen148")
    private WebElement dateFieldDD;

    @FindBy(xpath = "//div[contains(text(),'Created Date')]")
    private WebElement createdDate;

    @FindBy(xpath = "(//form[contains(@class, 'dateFiltersBody')]//div//img[contains(@class, 'x-form-date-trigger')])[1]")
    private WebElement fromDate;

    @FindBy(xpath = "(//div[contains(@class, 'x-date-picker')]//td[@class='x-date-bottom']//button[contains(@class, 'x-btn-text')])[1]")
    private WebElement fromToday;

    @FindBy(xpath = "(//form[contains(@class, 'dateFiltersBody')]//div//img[contains(@class, 'x-form-date-trigger')])[2]")
    private WebElement toDate;

    @FindBy(xpath = "(//div[contains(@class, 'x-date-picker')]//td[@class='x-date-bottom']//button[contains(@class, 'x-btn-text')])[2]")
    private WebElement toToday;

    @FindBy(id = "ext-gen49")
    private WebElement saveBtn;

    //popup
    @FindBy(id = "saveReportDlg_reportNameField")
    private WebElement reportNameBox;

    @FindBy(id = "saveReportDlg_DeveloperName")
    private WebElement UniqReportName;

    @FindBy(xpath = "//table[@id='dlgSaveAndRun']//td[@class='x-btn-mc']//em//button")
    private WebElement PopUpSaveBtn;

    //assert
    @FindBy(xpath = "//h2[@class='pageDescription']")
    private WebElement reportTitle;


    public AccountReportPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void clickDateFieldDD() {
        dateFieldDD.click();
    }

    public void selectCreatedDate() {
        createdDate.click();
    }

    public void clickFromDate() {
        fromDate.click();
    }

    public void clickFromToday() {
        fromToday.click();
    }

    public void clickToDate() {
        toDate.click();
    }

    public void clickToToday() {
        toToday.click();
    }

    public void clickSaveBtn() {
        saveBtn.click();
    }

    public void switchToPopUp(WebDriver driver) {
        Set<String> windowIds = driver.getWindowHandles();
        Iterator<String> id = windowIds.iterator();
        String childId = id.next();
        // String parentId = id.next();
        driver.switchTo().window(childId);
    }

    public void giveReportName() {
        Date curDate = new Date();
        String reportName = "priya report - " + curDate.getTime();
        reportNameBox.sendKeys(reportName);
    }

    public void giveUniqReportName(String reportUniqNameText) {
        UniqReportName.sendKeys(reportUniqNameText);
    }

    public void clickSaveInPopUp() {
        PopUpSaveBtn.click();
    }

    public String getReportTitle() {
        return reportTitle.getText();
    }
    }


