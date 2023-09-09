package com.salesforce.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserMenuPage {

    protected Logger logger = LogManager.getLogger(getClass().getName());

    public UserMenuPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userNavLabel")
    public WebElement userNavLabel;


    @FindBy(xpath = "//div[@id='userNav-menuItems']//a")
    public List<WebElement> userMenuOptions;


    public void showUserMenu() {

        this.userNavLabel.click();
    }

    public void logout() {
        for (WebElement dropDownOption : this.userMenuOptions) { //getting individual options
            if (dropDownOption.getText().contains("Logout")) {
                dropDownOption.click();
                break;
            }
        }
    }

    public List<String> getUserMenuOptions() {
        List<String> availableMenuItems = new ArrayList<>();

        for (WebElement menuItem : userMenuOptions) {
            availableMenuItems.add(menuItem.getText());
        }
        return availableMenuItems;

    }



    public boolean selectUserMenu(String text) {
        boolean isSelected = false;
        for (WebElement dropDownOption : userMenuOptions) {
            if (dropDownOption.getText().contains(text)) {
                dropDownOption.click();
                isSelected = true;
                break;
            }

        }
        return isSelected;
    }
}