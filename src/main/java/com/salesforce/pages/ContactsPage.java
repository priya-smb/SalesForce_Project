package com.salesforce.pages;

import com.salesforce.utils.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class ContactsPage {
    WebDriver driver;

    //Tc-25
    @FindBy(xpath = "//input[@value=' New ']")
    private WebElement contactsNew;

    @FindBy(id = "name_lastcon2")
    private WebElement lNameInputBox;

    @FindBy(id = "con4")
    private WebElement accName;

    @FindBy(xpath = "//td[@id='bottomButtonRow']//input[1]")
    private WebElement save;

    @FindBy(xpath = "//h2[@class='topName']")
    private WebElement accNewContact;

    //tc-26
    @FindBy(xpath = "//a[contains(text(),'Create New View')]")
    private WebElement createNewView;

    @FindBy(id = "fname")
    private WebElement viewNameBox;

    @FindBy(id = "devname")
    private WebElement viewUniqNameBox;

    @FindBy(xpath = "//div[@class='pbBottomButtons']//input[1]")
    private WebElement saveBtn;

    @FindBy(xpath = "//select[@class='title']")
    private WebElement newContactViewDD;

    //tc-27
    @FindBy(xpath = "//select[@id='hotlist_mode']")
    private WebElement recentlyCreatedDD;

    @FindBy(xpath = "//tr[@class='dataRow even first']//th//a")
    private WebElement recentCntName;

    //tc28
    @FindBy(id = "fcf")
    private WebElement contactsView;

    @FindBy(xpath = "//input[@value=' Go! ']")
    private WebElement goBtn;

    @FindBy(xpath = " //span[normalize-space()='Barr, Tim']")
    private WebElement allContactContact;

    @FindBy(xpath = "//h2[normalize-space()='Mr. Tim Barr']")
    private WebElement allContTitle;

    //tc29
    @FindBy(xpath = "//tr[contains(@class,'dataRow even first')]//a")
    private WebElement contactName;

    @FindBy(className = "topName")
    private WebElement contactTitle;

    //tc30
    @FindBy(xpath = " //div[@class='requiredInput']//div[@class='errorMsg']")
    private WebElement errorMsg;

    //tc31
    @FindBy(xpath = "//div[@class='pbBottomButtons']//input[@value='Cancel']")
    private WebElement cancel;

    @FindBy(xpath = "//select[@id='fcf']")
    private WebElement viewDD;

    //32
    @FindBy(xpath = "//input[@value=' New '] ")
    private WebElement newBtn;

    @FindBy(xpath = "//td[@id='bottomButtonRow']//input[2]")
    private WebElement saveNewBtn;

    @FindBy(id = "errorDiv_ep")
    private  WebElement errorMsgSaveNewBtn;




    public ContactsPage(WebDriver driver) {

        PageFactory.initElements(driver, this);

    }

    //tc25
    public void clickNew() {
        contactsNew.click();
    }

    public void sendLName(String lNameText) {
        lNameInputBox.sendKeys(lNameText);
    }

    public void sendAccName(String accNameText) {
        accName.sendKeys(accNameText);
    }

    public void clickSave() {
        save.click();
    }

    public String getAccNewContact() {
        return accNewContact.getText();
    }

    //tc-26
    public void clickCreateNewView() {
        createNewView.click();
    }

    public void sendViewName(String viewContactNameTxt) {

        viewNameBox.sendKeys(viewContactNameTxt);
    }

    public void sendViewUniqName(String ViewUniqNameTxt) {
        viewUniqNameBox.sendKeys(ViewUniqNameTxt);
    }

    public void clickSaveBtn() {
        saveBtn.click();
    }

    public String selectContactViewDD() {
        Select select = new Select(newContactViewDD);
        WebElement selectedContactView = select.getFirstSelectedOption();
        return selectedContactView.getText();
    }

    //tc27
    public void selectRecentlyDD(String recentText) {

        Select sltRecent = new Select(recentlyCreatedDD);
        sltRecent.selectByVisibleText(recentText);
    }

    public String recentCnt() {
        CommonUtils.waitForElement(driver,recentCntName);
       return recentCntName.getText();
    }

    //tc28
    public void sltMycontsDD(String cntOptionText) {
        Select sltcontactsView = new Select(contactsView);
        sltcontactsView.selectByVisibleText(cntOptionText);
    }

    public void clickGoBtn() {
        goBtn.click();
    }

    public void clickAllContact() {
        allContactContact.click();
    }

    public String getAllContTitle() {
        return allContTitle.getText();
    }


    //tc29
    public void clickContactName() {
        contactName.click();
    }

    public String getCntTitle() {
        return contactTitle.getText();
    }

    //tc30
    public String getErrorMsg() {
        return errorMsg.getText();
    }

    //tc31
    public void clickCancel() {
        cancel.click();
    }

    public boolean checkViewDD(String canceledViewText) {
        Select select = new Select(viewDD);
        List<WebElement> viewList = select.getOptions();

        boolean  isCanceledViewPresent = false ;
        for (WebElement viewOneByOne : viewList){
            String viewOneByOneText = viewOneByOne.getText();
            if (viewOneByOneText.equals(canceledViewText)){
                isCanceledViewPresent = true;
                break;
            }
        }
        return isCanceledViewPresent;
    }

    //TC32
    public void clickNewBtn() {
        newBtn.click();
    }

    public void clickSaveNewBtn() {
        CommonUtils.waitForElement(driver,saveNewBtn);
        saveNewBtn.click();
    }

    public String getErrorMsgSaveNewBtn() {
        return errorMsgSaveNewBtn.getText();
    }
}
