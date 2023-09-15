package com.salesforce.pages;

import com.salesforce.utils.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyProfilePage {
    WebDriver driver;

    public MyProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "tailBreadcrumbNode")
    private WebElement myProfileName;

    public String getProfileName() {
        CommonUtils.waitForElement(driver, myProfileName);
        return myProfileName.getText();
    }

    @FindBy(xpath = "//a[@class='contactInfoLaunch editLink']//img")
    private WebElement editLink;


    @FindBy(id = "contactInfoContentId")
    private WebElement editFrame;


    @FindBy(xpath = "//li[@id='aboutTab']//a[@role='tab']")
    private WebElement aboutBtn;


    @FindBy(id = "lastName")
    private WebElement lastName;


    @FindBy(xpath = "//input[@class='zen-btn zen-primaryBtn zen-pas']")
    private WebElement saveAllBtn;

    @FindBy(className = "publisherattachtext")
    private WebElement post;

    @FindBy(xpath = "//iframe[@title='Rich Text Editor, publisherRichTextEditor']")
    private WebElement postFrame;

    @FindBy(xpath = "//body[@ contenteditable='true']")
    private WebElement inPostTextArea;

    @FindBy(id = "publishersharebutton")
    private WebElement shareBtn;

    @FindBy(id = "publisherAttachContentPost")
    private WebElement fileLink;

    @FindBy(id = "chatterUploadFileAction")
    private WebElement fileUploadComputer;

    @FindBy(id = "chatterFile")
    private WebElement chooseFile;


    @FindBy(xpath = "//div[@id='photoSection']//span[2]//img[1]")
    private WebElement myProfilePhoto;

    @FindBy(className = "photoUploadSection")
    private WebElement photoSection;

    @FindBy(id = "uploadLink")
    private WebElement updateLink;

    @FindBy(id = "uploadPhotoContentId")
    private WebElement photoFrame;

    @FindBy(id = "profileTabSet")
    private WebElement profileTabSet;

    @FindBy(id = "j_id0:uploadFileForm:uploadInputFile")
    private WebElement choosePhotoLink;

    @FindBy(id = "j_id0:uploadFileForm:uploadBtn")
    private WebElement savePhoto;

    @FindBy(id = "j_id0:j_id7:save")
    private WebElement savePhotoPopUp;


    public void editProfile(String lNameText) {
        CommonUtils.waitForElement(driver, profileTabSet);
        CommonUtils.waitForElement(driver, editLink);
        editLink.click();
        CommonUtils.waitForElement(driver, editFrame);
        driver.switchTo().frame(editFrame);
        CommonUtils.waitForElement(driver,aboutBtn);
        aboutBtn.click();
        CommonUtils.waitForElement(driver,lastName);
        lastName.clear();
        lastName.sendKeys(lNameText);
        CommonUtils.waitForElement(driver,saveAllBtn);
        saveAllBtn.click();
        driver.switchTo().defaultContent();
    }

    public void postLink(String postText) throws InterruptedException {
         CommonUtils.waitForElement(driver,post);
        post.click();
        driver.switchTo().frame(postFrame);
         CommonUtils.waitForElement(driver,inPostTextArea);
        inPostTextArea.click();
        inPostTextArea.sendKeys(postText);
        driver.switchTo().defaultContent();
         CommonUtils.waitForElement(driver,shareBtn);
        shareBtn.click();
    }

    public void clickFileLink(String filePath) {
        fileLink.click();
        fileUploadComputer.click();
        chooseFile.sendKeys(filePath);
    }


    public void clickAddPhotoLink(String photoPath) {
        // CommonUtils.waitForElement(driver,myProfileName);
        myProfilePhoto.click();
        //  CommonUtils.waitForElement(driver,photoSection);
        //  CommonUtils.waitForElement(driver,updateLink);
        if (photoSection.getText().startsWith("Update")) {
            updateLink.click();
        }
        driver.switchTo().frame(photoFrame);
        // CommonUtils.waitForElement(driver,choosePhotoLink);
        choosePhotoLink.sendKeys(photoPath);
        // CommonUtils.waitForElement(driver,savePhoto);
        savePhoto.click();
        // CommonUtils.waitForElement(driver,savePhotoPopUp);
        savePhotoPopUp.click();

    }


}
