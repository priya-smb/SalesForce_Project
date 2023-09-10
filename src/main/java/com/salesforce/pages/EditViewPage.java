package com.salesforce.pages;

import com.salesforce.utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class EditViewPage {

    private WebDriver driver;

    @FindBy(id = "fname")
    private WebElement viewNameTextBox;

    @FindBy(xpath = "//select[@title='Search By 1']")
    private WebElement sltFieldDD;

    @FindBy(id = "fop1")
    private WebElement sltOperatorDD;

    @FindBy(id = "fval1")
    private WebElement value;

    @FindBy(xpath = "//div[@class='pbBottomButtons']//input[1]")
    private WebElement saveBtn;

    @FindBy(id = "devname")
    private WebElement viewUniqueNameTextBox;



    public EditViewPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }



    public void setViewName(String viewName) {
        CommonUtils.waitForElement(driver, viewNameTextBox);
        viewNameTextBox.sendKeys(viewName);
    }

    public void setUniqueViewName(String uniqueViewName) {
        viewUniqueNameTextBox.sendKeys(uniqueViewName);
    }

    public void saveView() {
        saveBtn.click();
    }

    public void giveNewViewName(String newViewNameText) {
        viewNameTextBox.clear();
        viewNameTextBox.sendKeys(newViewNameText);
    }

    public void selectField(String fieldNameText) {
        Select sltField = new Select(sltFieldDD);
        sltField.selectByVisibleText(fieldNameText);
    }
    public void selectOperator(String operatorText) {
        Select sltOperator = new Select(sltOperatorDD);
        sltOperator.selectByVisibleText(operatorText);
    }

    public void addValue(String valueText) {
        value.sendKeys(valueText);
    }

    public void clickSave() {
        saveBtn.click();
    }

}
