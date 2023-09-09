package com.salesforce.menu;

import com.salesforce.BaseTest;
import com.salesforce.login.LoginTest;
import com.salesforce.pages.LoginPage;
import com.salesforce.pages.MyProfilePage;
import com.salesforce.pages.MySettingsPage;
import com.salesforce.pages.UserMenuPage;
import com.salesforce.utils.CommonUtils;
import com.salesforce.utils.ConfigUtil;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class UserMenuPageTest extends BaseTest {


    public UserMenuPage showUserMenuOptions() {
        WebDriver driver = getDriver();

        driver.get(getProperty("salesforce.url"));
        login(driver);

        UserMenuPage userMenuPage = new UserMenuPage(driver);
        userMenuPage.showUserMenu();

        return userMenuPage;


    }

    @Test
    public void checkUserNameMenu_TC5() {

        UserMenuPage userMenuPage = showUserMenuOptions();
        List<String> userMenuOptions = userMenuPage.getUserMenuOptions();
        String[] expectedMenuItems = {
                "My Profile",
                "My Settings",
                "Developer Console",
                "Switch to Lightning Experience",
                "Logout"
        };

        for (String expectedValue : expectedMenuItems) {
            Assert.assertListContainsObject(userMenuOptions, expectedValue, "missing an expected item");
        }
    }

    @Test
    public void selectMyProfile_TC6() {

        UserMenuPage userMenuPage = showUserMenuOptions();
        checkUserNameMenu_TC5();
        userMenuPage.selectUserMenu("My Profile");
        MyProfilePage myProfilePage = new MyProfilePage(getDriver());
        Assert.assertEquals(myProfilePage.getProfileName(), "Priyanka Selenium ");
        myProfilePage.editProfile("Selenium");
        Assert.assertEquals(myProfilePage.getProfileName(), "Priyanka Selenium ");
        myProfilePage.postLink("Automation FrameWork");
        myProfilePage.clickFileLink("/Users/priya/Downloads/QA picture.png");
        myProfilePage.clickAddPhotoLink("/Users/priya/Downloads/QA picture.png");
    }

    @Test
    public void selectMySettings_TC7() throws InterruptedException {
        UserMenuPage userMenuPage = showUserMenuOptions();
        checkUserNameMenu_TC5();
        userMenuPage.selectUserMenu("My Settings");
        MySettingsPage mySettingsPage = new MySettingsPage(getDriver());
        Assert.assertEquals(mySettingsPage.getMySettingsText(), "My Settings");
        mySettingsPage.personalLink.click();
        mySettingsPage.selectSetting("Personal", "Login History");
       // mySettingsPage.selectFromPersonal("Login History");
        mySettingsPage.clickLoginHistoryDownloadLink();

        String downloadPath = ConfigUtil.getProperty("download.path");
        // TODO: 9/6/23  Assert for login download

        mySettingsPage.selectSetting("Display & Layout", "Customize My Tabs");
        mySettingsPage.selectFromCustomAppDropDown("Salesforce Chatter");
        mySettingsPage.selectOptionsFromTags("Reports");
        Assert.assertEquals(mySettingsPage.checkReportTab(), "Reports");

        mySettingsPage.selectSetting("Email", "My Email Settings");
        mySettingsPage.sendEmailName("Priyanka B");
        mySettingsPage.sendEmailAddress("priyanka.mandrumaka@gmail.com");
        mySettingsPage.selectBbcRadioBtn();

        String actConfMsg = mySettingsPage.checkConfMsg();
        Assert.assertEquals(actConfMsg, "Your settings have been successfully saved.");

        mySettingsPage.selectSetting("Calendar & Reminders", "Activity Reminders");
        mySettingsPage.clickReminderBtn();
        String mainWindowHandle = getDriver().getWindowHandle();
        Set<String> windows = getDriver().getWindowHandles();
        for (String childWindow : windows) {
            if (!childWindow.equals(mainWindowHandle)) {
                getDriver().switchTo().window(childWindow);
                Assert.assertTrue(mySettingsPage.getReminderPopUpSub(),"popup did not come");
            }
        }
    }


    @Test
    public void developersConsole_TC8() {
        UserMenuPage userMenuPage = showUserMenuOptions();
        checkUserNameMenu_TC5();
        boolean isDevMEnuSelected = userMenuPage.selectUserMenu("Developer Console");
       Assert.assertTrue(isDevMEnuSelected);
        logger.info("Developers Console is selected");
        MySettingsPage mySettingsPage = new MySettingsPage(getDriver());
        boolean isDevWindowClosed = mySettingsPage.closeDevConsoleWindow();
        logger.info("checking developers window closed or not ");
        Assert.assertTrue(isDevWindowClosed);

    }

    @Test
    public void logout_TC9() {
        UserMenuPage userMenuPage = showUserMenuOptions();
        checkUserNameMenu_TC5();
        logger.info("userMenu options displayed");
        userMenuPage.selectUserMenu("Logout");
        LoginPage loginPage = new LoginPage(getDriver());
        Assert.assertEquals(loginPage.getUsername(),"" );
        logger.info("logout complete");
    }
}


