package com.salesforce.pages;

import com.aventstack.extentreports.util.Assert;
import com.salesforce.utils.CommonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.beans.Transient;
import java.io.File;
import java.util.List;
import java.util.Set;

public class MySettingsPage {
    protected Logger logger = LogManager.getLogger(getClass().getName());

    WebDriver driver;

    public MySettingsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@id='PersonalSetup_font']//span[2]")
    private WebElement mySettingsText;

    @FindBy(id = "PersonalInfo_font")
    public WebElement personalLink;

    @FindBy(xpath = "//div[@id='PersonalInfo_child']//div[@class='setupLeaf']")
    private List<WebElement> personalOptionsList;

    @FindBy(xpath = "//div[@class='pShowMore']//a[1]")
    private WebElement loginHistoryDownloadLink;

    @FindBy(xpath = "//div[@id='AutoNumber5']//div[contains(@class,'parent')]")
    private List<WebElement> settingsParentElements;

    @FindBy(id = "p4")
    private WebElement customAppDrop;

    @FindBy(id = "duel_select_0")
    private WebElement availableTabsContainer;

    @FindBy(id = "duel_select_1")
    private WebElement selectedTabsContainer;

    @FindBy(className = "rightArrowIcon")
    private WebElement addArrowBtn;

    @FindBy(className = "leftArrowIcon")
    private WebElement removeArrowBtn;

    @FindBy(xpath = "//td[@id='bottomButtonRow']//input[1]")
    private WebElement saveBtn;

    @FindBy(id = "report_Tab")
    private WebElement reportTab;

    @FindBy(id = "sender_name")
    private WebElement emailName;

    @FindBy(id = "sender_email")
    private WebElement emailAddress;

    @FindBy(id = "auto_bcc1")
    private WebElement bbcRadioBtn;

    @FindBy(xpath = "//input[@value=' Save ']")
    private WebElement emailSettingSaveBtn;

    @FindBy(className = "messageText")
    private WebElement settingConfMsg;

    @FindBy(id = "testbtn")
    private WebElement testReminderBtn;

    @FindBy(className = "subject")
    private WebElement reminderPopUp;


    public String getMySettingsText() {
        return mySettingsText.getText();
    }

    //selecting from personal options
//    public void selectFromPersonal(String optionInPersonal) {
//        for (WebElement personalOption : personalOptionsList) {
//            if (personalOption.getText().equals(optionInPersonal)) {
//                personalOption.click();
//                break;
//            }
//        }
//    }

    public void clickLoginHistoryDownloadLink() {
        loginHistoryDownloadLink.click();
    }


    public void selectSetting(String parentName, String childName) {
        logger.info("Selecting settings......");
        for (WebElement setting : settingsParentElements) {
            String currentParentName = setting.findElement(By.xpath(".//span[@class='folderText']")).getText();
            if (currentParentName.equals(parentName)) {
                if (!setting.getAttribute("class").contains("active")) {
                    setting.click();
                }
                List<WebElement> childSettings = setting.findElements(By.xpath(".//div[@class='setupLeaf']//a"));
                for (WebElement childSetting : childSettings) {
                    if (childSetting.getText().equals(childName)) {
                        childSetting.click();
                        return;
                    }
                }
            }
        }
    }

    public void selectFromCustomAppDropDown(String customOption) {
        Select customDropDown = new Select(customAppDrop);
        customDropDown.selectByVisibleText(customOption);
    }

    public void selectOptionsFromTags(String selectingTab) {
        Select availTab = new Select(availableTabsContainer);
        Select selectTab = new Select(selectedTabsContainer);
        if (availableTabsContainer.getText().contains(selectingTab)) {
            availTab.selectByVisibleText(selectingTab);
        } else if (selectedTabsContainer.getText().contains(selectingTab)) {
            selectTab.selectByVisibleText(selectingTab);
            clickRemoveArrowBtn();
        }
        clickAddArrowBtn();
        clickSaveBtn();
    }

    public void clickAddArrowBtn() {
        addArrowBtn.click();
    }

    public void clickRemoveArrowBtn() {
        removeArrowBtn.click();
    }

    public void clickSaveBtn() {
        saveBtn.click();
    }

    public String checkReportTab() {
        return reportTab.getText();
    }

    public void sendEmailName(String emailNameText) {
        emailName.clear();
        emailName.sendKeys(emailNameText);
    }

    public void sendEmailAddress(String emailAddress) {
        this.emailAddress.clear();
        this.emailAddress.sendKeys(emailAddress);
    }

    public void selectBbcRadioBtn() {
        if (bbcRadioBtn.isSelected()) {
            emailSettingSaveBtn.click();
        } else {
            emailSettingSaveBtn.click();
        }
    }

    public String checkConfMsg() {
        return settingConfMsg.getText();
    }

    public void clickReminderBtn() {
        testReminderBtn.click();
    }

    public boolean getReminderPopUpSub() {
        return reminderPopUp.isDisplayed();
    }

    public boolean closeDevConsoleWindow() {
        boolean isClosed = false;
        String mainWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        if (windowHandles.size() == 2) {
            for (String windowHandle : windowHandles) {
                if (!windowHandle.equals(mainWindow)) {
                    driver.switchTo().window(windowHandle);
                    driver.close();
                    driver.switchTo().window(mainWindow);
                    isClosed = true;
                    break;
                }
            }
        }
        return isClosed;
    }
}

