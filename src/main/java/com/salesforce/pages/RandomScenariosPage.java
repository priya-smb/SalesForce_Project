package com.salesforce.pages;

import com.salesforce.utils.CommonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

public class RandomScenariosPage {

    protected static Logger logger = LogManager.getLogger(RandomScenariosPage.class);


    WebDriver driver;

    @FindBy(xpath = "//h1[@class='currentStatusUserName']//a")
    private WebElement fNameLNameLink;

    @FindBy(id = "tailBreadcrumbNode")
    private WebElement userName;

    @FindBy(xpath = "//div[@class='menuButtonButton']//span[@id='userNavLabel']")
    public WebElement userMenuDDBtn;

    //tc-35
    @FindBy(xpath = "//img[@class='allTabsArrow']")
    public WebElement plus;

    @FindBy(className = "btnImportant")
    private WebElement customizeMyTabs;

    @FindBy(id = "duel_select_1")
    private WebElement selectedTabsContainer;

    @FindBy(className = "leftArrowIcon")
    private WebElement removeBtn;

    @FindBy(className = "rightArrowIcon")
    private WebElement addBtn;

    @FindBy(xpath = "//td[@id='bottomButtonRow']//input[1]")
    private WebElement saveBtn;

    @FindBy(id = "duel_select_0")
    private WebElement availableTabsContainer;

    @FindBy(xpath = "//ul[@id='tabBar']//li")
    private List<WebElement> tabBarItems;

    //tc36
    @FindBy(xpath = "//span[@class='pageDescription']//a")
    private WebElement currentDateLink;

    @FindBy(xpath = "//div[@id='p:f:j_id25:j_id61:28:j_id64']//a")
    private WebElement time8PmLink;

    @FindBy(className = "comboboxIcon")
    private WebElement subjectIcon;

    @FindBy(xpath = "//div[contains(@class,'choicesBox')]//li[5]//a")
    private WebElement childWindowOther;

    @FindBy(id = "EndDateTime_time")
    private WebElement endTimeField;

    @FindBy(id = "timePickerItem_42")
    private WebElement time9pm;

    @FindBy(xpath = "//td[@id='topButtonRow']//input[@name='save']")
    private WebElement saveNewEvent;

    //vali
    @FindBy(xpath = "//div[@class='eventBlockDnD']//span//span")
    private List<WebElement> eventsAt8;

    //tc37
    @FindBy(xpath = "//div[@id='p:f:j_id25:j_id61:20:j_id64']//a")
    private WebElement time4pm;

    @FindBy(id = "timePickerItem_38")
    private WebElement time7pm;

    @FindBy(id = "IsRecurrence")
    private WebElement recurringCBox;

    @FindBy(id = "rectypeftw")
    private WebElement weeklyRadioBtn;

    @FindBy(id ="RecurrenceEndDateOnly")
    private WebElement recurrenceEndDate;

    @FindBy(xpath = "//td[@id='bottomButtonRow']//input[@name='save']")
    private WebElement saveRecurrence;

    @FindBy(className = "monthViewIcon")
    private WebElement monthView;

    @FindBy(xpath = "//select[@id='calMonthPicker']")
    private WebElement calenderMonthContainer;

    @FindBy(xpath = "//table[@class='calDays']//td")
    private List<WebElement> calenderDays;

    @FindBy(id = "calYearPicker")
    private WebElement calenderYear;

    //practice
    @FindBy(xpath = "//select[@id='calMonthPicker']//option")
    private List<WebElement> monthsList;


    public RandomScenariosPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    //tc33
    public void clickFirstLastNameLink() {
        fNameLNameLink.click();
    }

    public String getUserName() {
        return userName.getText();
    }

    //tc34
    public String getUserMenuText() {
        return userMenuDDBtn.getText();
    }

    //tc35

    public void clickPlus() {
        plus.click();
    }

    public void clickCustomizeMyTabs() {
        customizeMyTabs.click();
    }


    public boolean selectFromSelectedTabs(String selectTabOptionText) {

        boolean tabNotInSelectedList = checkFromSelectedTabs(selectTabOptionText);

        if (tabNotInSelectedList) {
            selectFromAvailableTabs(selectTabOptionText);
        }

        tabNotInSelectedList = checkFromSelectedTabs(selectTabOptionText);

        return tabNotInSelectedList;

    }

    public boolean checkFromSelectedTabs(String selectTabOptionText) {
        Select selectedTabsList = new Select(selectedTabsContainer);
        List<WebElement> selectTabOptions = selectedTabsList.getOptions();
        boolean tabNotInSelectedList = true;
        for (WebElement selectTabOption : selectTabOptions) {
            if (selectTabOption.getText().equals(selectTabOptionText)) {
                selectTabOption.click();
                clickRemove();
                tabNotInSelectedList = false;
                break;
            }
        } // End of for loop
        return tabNotInSelectedList;
    }

    public void selectFromAvailableTabs(String availableTabOptionText) {
        Select availableTabsList = new Select(availableTabsContainer);
        List<WebElement> availableTabOptions = availableTabsList.getOptions();
        for (WebElement availableTabOption : availableTabOptions) {
            if (availableTabOption.getText().equals(availableTabOptionText)) {
                availableTabOption.click();
                clickAdd();
                break;
            }
        }
    }

    public void clickRemove() {
        removeBtn.click();
    }

    public void clickAdd() {
        addBtn.click();
    }

    public void clickSave() {
        saveBtn.click();
    }

    public boolean checkRemovedTabInTabBar(String tabOptionText) {
        boolean isTextPresent = false;
        for (WebElement tabBarItem : tabBarItems) {
            if (tabBarItem.getText().equals(tabOptionText)) {
                isTextPresent = true;
                break;
            }
        }
        return isTextPresent;
    }


    //tc36
    public String getSystemCurrentDateFormat() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String requiredDate = simpleDateFormat.format(new Date()).toString();
        return requiredDate;
    }

    //    public String checkCurrentDateFormat() {
//        return currentDateLink.getText();
//    }
    public void clickCurrentDate() {
        currentDateLink.click();
    }

    public void clickTime8pm() {
        time8PmLink.click();
    }


    public void chooseOtherInChildWindow() {
        String mainWindow = driver.getWindowHandle();
        subjectIcon.click();
        Set<String> windows = driver.getWindowHandles();
        logger.info("mainWindow " + mainWindow);
        logger.info("Total windows " + windows.size());
        String childWindow = null;
        for (String window : windows) {
            logger.info("Current window " + window);
            if (!window.equals(mainWindow)) {
                logger.info("Popup window " + window);
                childWindow = window;
                break;
            }
        }
        driver.switchTo().window(childWindow);
        childWindowOther.click();
        driver.switchTo().window(mainWindow);
    }

    public void clickEndTime() {
        endTimeField.click();
    }

    public void select9pm() {
        time9pm.click();
    }

    public void clickSaveEvent() {
        saveNewEvent.click();
    }

    public boolean checkCalenderEvents(String eventNameText) {
        boolean isEventPresentAt8 = false;
        int lastEventIndex = eventsAt8.size()-1;
        WebElement lastEvent = eventsAt8.get(lastEventIndex);
        if (lastEvent.getText().equals(eventNameText)){
            isEventPresentAt8 = true;
        }
        return isEventPresentAt8;
    }

    //tc37
    public void select4pm() {
        time4pm.click();
    }

    public void select7pm() {
        time7pm.click();
    }

    public void selectRecurringBox() {
        if (!recurringCBox.isSelected())
            recurringCBox.click();
    }

    public void selectWeeklyBtn() {
        if (!weeklyRadioBtn.isSelected()) {
            weeklyRadioBtn.click();
        }
    }

    public void clickRecurrenceEnd() {
        recurrenceEndDate.click();
    }

    public void setRecurrenceDate(LocalDate localDate) {

        //year
        Select sltYear = new Select(calenderYear);
        int year = localDate.getYear();
        sltYear.selectByVisibleText(Integer.toString(year));
        logger.info("Selected Year: " + year);

        //Month
        Select sltMonth = new Select(calenderMonthContainer);
        int month = localDate.getMonthValue() - 1;//without -1 october is selecting(java giving 1-12 month format,but html 0-11)
        sltMonth.selectByValue(Integer.toString(month));
        logger.info("Selected Month: " + month);

//        String monthStr = localDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
//        sltMonth.selectByVisibleText(monthStr);
//        logger.info("Selected Month: " + monthStr);




        logger.info("---------------");
        logger.info("Final Selected Month: " + sltMonth.getFirstSelectedOption().getText());
        logger.info("Final Selected Year: " + sltYear.getFirstSelectedOption().getText());

        //day
        int date = localDate.getDayOfMonth();
        logger.info("Selected Date: " + date);
        for (WebElement calenderDate : calenderDays) {
            if (!calenderDate.getAttribute("class").contains("prevMonth")//here for previous,months dates class attribute is different than current mon dates class attribute.
                    && calenderDate.getText().equals(Integer.toString(date))){
                logger.info("Final Selected Date: " + calenderDate.getText());
                calenderDate.click();
                break;
            }
        }
    }

        public void clickSaveRecurrence() {
            saveRecurrence.click();
        }

        public void selectMonthView() {
            monthView.click();
        }

}

