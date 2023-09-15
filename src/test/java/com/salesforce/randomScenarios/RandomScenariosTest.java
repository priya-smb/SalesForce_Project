package com.salesforce.randomScenarios;

import com.salesforce.BaseTest;
import com.salesforce.pages.HomePage;
import com.salesforce.pages.MyProfilePage;
import com.salesforce.pages.RandomScenariosPage;
import com.salesforce.pages.UserMenuPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class RandomScenariosTest extends BaseTest {

    @Test
    public void TC_33verifyFLNames() {
        WebDriver driver = openLoginPage();
        login(driver);

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.clickHome();

        RandomScenariosPage randomScenariosPage = new RandomScenariosPage(driver);
        randomScenariosPage.clickFirstLastNameLink();
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Assert.assertEquals(randomScenariosPage.getUserName(),myProfilePage.getProfileName());
        logger.info("first name last name from home page is same as my profile page");
    }

    @Test
    public void TC_34verifyEditedLNameUpdate() {
        WebDriver driver = openLoginPage();
        login(driver);

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.clickHome();

        RandomScenariosPage randomScenariosPage = new RandomScenariosPage(driver);
        randomScenariosPage.clickFirstLastNameLink();

        String expUpdatedUserName = editProfileLName(driver, "Abcd");

        Assert.assertEquals(randomScenariosPage.getUserName().trim(),expUpdatedUserName);
        logger.info("user last name is updated in first name last name page ");

        Assert.assertEquals(randomScenariosPage.getUserMenuText().trim(),expUpdatedUserName);
        editProfileLName(driver, "Selenium");
    }

    private String editProfileLName(WebDriver driver, String lName) {
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        myProfilePage.editProfile(lName);
        String expUpdatedUserName = ("Priyanka " + lName).trim();
        return expUpdatedUserName;
    }

    @Test
    public void TC_35verifyTabCustomization() {
        WebDriver driver = openLoginPage();
        login(driver);

        RandomScenariosPage randomScenariosPage = new RandomScenariosPage(driver);
        randomScenariosPage.clickPlus();
        randomScenariosPage.clickCustomizeMyTabs();
        randomScenariosPage.selectFromSelectedTabs("Leads");
        randomScenariosPage.clickSave();
        HomePage homePage = new HomePage(driver);
        homePage.showUserMenu();
        homePage.logout();
        login(driver);
        Assert.assertFalse(randomScenariosPage.checkRemovedTabInTabBar("leads"));
    }

    @Test
    public void TC_36BlockingEventInCalender() {
        WebDriver driver = openLoginPage();
        login(driver);

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.clickHome();

        RandomScenariosPage randomScenariosPage = new RandomScenariosPage(driver);
        // TODO: 9/13/23
        //randomScenariosPage.checkCurrentDateFormat();
        randomScenariosPage.clickCurrentDate();
        randomScenariosPage.clickTime8pm();
        randomScenariosPage.chooseOtherInChildWindow();
        randomScenariosPage.clickEndTime();
        randomScenariosPage.select9pm();
        randomScenariosPage.clickSaveEvent();
        Assert.assertTrue(randomScenariosPage.checkCalenderEvents("Other"));

    }

    @Test
    public void TC_37BlockingEventInCalenderWithWeeklyRecurrence() {
        WebDriver driver = openLoginPage();
        login(driver);

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.clickHome();
        RandomScenariosPage randomScenariosPage = new RandomScenariosPage(driver);
        randomScenariosPage.clickCurrentDate();
        randomScenariosPage.select4pm();
        randomScenariosPage.chooseOtherInChildWindow();
        randomScenariosPage.clickEndTime();
        randomScenariosPage.select7pm();
        randomScenariosPage.selectRecurringBox();
        randomScenariosPage.selectWeeklyBtn();
        randomScenariosPage.clickRecurrenceEnd();
        LocalDate localDatePlus2weeks = LocalDate.now().plusWeeks(2);
        randomScenariosPage.setRecurrenceDate(localDatePlus2weeks);
        randomScenariosPage.clickSaveRecurrence();
        randomScenariosPage.selectMonthView();



    }
}
