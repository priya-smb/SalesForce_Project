package com.salesforce.pages;

import com.salesforce.utils.CommonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AccountPage {
    WebDriver driver;
    protected Logger logger = LogManager.getLogger(getClass().getName());

    //tc-10
    @FindBy(xpath = "//a[@title='Accounts Tab']")
    private WebElement accountLink;

    @FindBy(xpath = "//input[@value =' New ']")
    private WebElement newBtn;

    @FindBy(id = "acc2")
    private WebElement accNameInputBox;

    @FindBy(id = "acc6")
    private WebElement accTypeDropDown;

    @FindBy(xpath = "//select[@id='00NHu00000PENAf']")
    private WebElement customerPriorityDropDown;

    @FindBy(xpath = "//td[@id='bottomButtonRow']//input[1]")
    private WebElement saveCreateAccBtn;

    @FindBy(xpath = "//td[@class='pbTitle']//h3")
    private WebElement recentAccountsTitle;


    @FindBy(className = "topName")
    private WebElement accNameTitle;

    @FindBy(xpath = "//td[@id='topButtonRow']//input[@value='Delete']")
    private WebElement deleteCreatedAccountBtn;

    //tc-11
    @FindBy(xpath = "//span[@class='fFooter']")
    private WebElement createNewViewBtn;

    @FindBy(id = "fname")
    private WebElement viewNameTextBox;

    @FindBy(id = "devname")
    private WebElement viewUniqueNameTextBox;

    @FindBy(xpath = "//div[@class='pbBottomButtons']//td[2]//input[1]")
    private WebElement saveNewViewBtn;

    //validation
    @FindBy(xpath = "//select[@class='title']//option")
    private List<WebElement> viewList;


    //Tc-12
    @FindBy(xpath = "//select[@id='fcf']")
    private WebElement viewDropDownList;

    //tc-13

    @FindBy(xpath = "//div[@class='toolsContentRight']//li[4]//a")
    private WebElement mergeAccLink;

    @FindBy(id = "srch")
    private WebElement findAccTextBox;

    @FindBy(xpath = "//input[@value='Find Accounts']")
    private WebElement findAccBtn;

    @FindBy(xpath = "//input[@id='cid0']")
    private WebElement optionOne;

    @FindBy(xpath = "//input[@id='cid1']")
    private WebElement optionTwo;

    @FindBy(xpath = "//div[@class='pbBottomButtons']//input[@value=' Next ']")
    private WebElement nextBtn;

    @FindBy(xpath = "//div[@class='pbBottomButtons']//input[@value=' Merge ']")
    private WebElement four;

    @FindBy(xpath = "//div[@class='pbBottomButtons']//input[@value=' Merge ']")
    private WebElement mergeAccBtn;


    //driver.findElement(By.xpath("//div[@class='toolsContentRight']//li[4]//a")).click();
    // driver.findElement(By.id("srch")).sendKeys("Priya");
    //driver.findElement(By.xpath("//input[@value='Find Accounts']")).click();
    //driver.findElement(By.xpath("//input[@id='cid0']")).click();
    // driver.findElement(By.xpath("//input[@id='cid1']")).click();
    // driver.findElement(By.xpath("//div[@class='pbBottomButtons']//input[@value=' Next ']")).click();
    //driver.findElement(By.xpath("//div[@class='pbBottomButtons']//input[@value=' Merge ']")).click();
    //driver.switchTo().alert().accept();


    public AccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickAccountLink() {
        accountLink.click();
    }

    public void clickNewBtn() {
        newBtn.click();
    }

    public void sendAccName(String accName) {
        CommonUtils.waitForElement(driver, accNameInputBox);
        accNameInputBox.click();
        accNameInputBox.sendKeys(accName);
    }

    public void selectAccType(String accType) {
        Select sltAccType = new Select(accTypeDropDown);
        sltAccType.selectByVisibleText(accType);
    }

    public void selectCustomerPriority(String priorityLevel) {
        Select sltCustomerPriority = new Select(customerPriorityDropDown);
        sltCustomerPriority.selectByVisibleText(priorityLevel);
    }

    public void clickSaveBtnCreateAcc() {
        saveCreateAccBtn.click();

    }

    public String getRecentAccountsTitle() {
        return recentAccountsTitle.getText();
    }

    public String getAccNameTitle() {
        return accNameTitle.getText();
    }

    public void deleteCreatedAcc() {
        deleteCreatedAccountBtn.click();
        driver.switchTo().alert().accept();
    }

    //TC-11

    public void clickCreateNewView() {
        createNewViewBtn.click();
    }

    public void sendValueToViewName(String viewNameText) {
        viewNameTextBox.click();
        viewNameTextBox.sendKeys(viewNameText);
    }

    public void sendValueToUniqueName(String uniqueNameText) {
        viewUniqueNameTextBox.click();
        viewUniqueNameTextBox.sendKeys(uniqueNameText);
    }

    public void ClickSaveBtnCreateView() {
        saveNewViewBtn.click();
    }

//    public String getCreatedView() {
//        WebElement viewFirst = viewList.get(1);
//        return viewFirst.getText();
//
//    }

    public void chooseDropDownList() {

        CommonUtils.waitForElement(driver,viewDropDownList);
        Select select = new Select(viewDropDownList);
        List<WebElement> viewOptions = select.getOptions();
        for (WebElement viewOptionOne:viewOptions){
            viewOptionOne.click();
        }
    }

    public void clickMergeAcc() {
        mergeAccLink.click();
    }

    public void sendValueAccTextBox(String textBoxTest) {
        findAccTextBox.clear();
        findAccTextBox.sendKeys(textBoxTest);

    }

    public void clickAccBtn() {
        findAccBtn.click();
    }

    public void clickOptOne() {
        optionOne.click();
    }

    public void clickOptTwo() {
        optionTwo.click();
    }

    public void clickNext() {
        nextBtn.click();
    }

    public void clickMergeBtn() {
        mergeAccLink.click();
    }

    //TC-13
    public void clickMergeLink() {

        mergeAccLink.click();
    }

    public void sendValueToFindAccTextBox(String findAccText) {
        findAccTextBox.click();
        findAccTextBox.clear();
        findAccTextBox.sendKeys(findAccText);
    }

    public void clickFindAccBtn() {
        findAccBtn.click();
    }


    public void clickDeleteAcc() {
        deleteCreatedAccountBtn.click();
    }

    public void clickMergeAccBtn() {
        mergeAccBtn.click();
        driver.switchTo().alert().accept();
    }
}