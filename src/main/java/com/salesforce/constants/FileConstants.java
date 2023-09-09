package com.salesforce.constants;

import com.salesforce.utils.CommonUtils;

public class FileConstants {

    public static final String LOGIN_TESTDATA_FILE_PATH2 =  System.getProperty("user.dir") + "/src/main/resources/testdata/loginTestData.properties";
    public static final String REPORT_PATH =  System.getProperty("user.dir") + "/src/main/resources/reports/"+ CommonUtils.getStringDateAndTimeStamp()+"_SFDC.html";


    public static final String SCREENSHOT_PATH =  System.getProperty("user.dir") + "/src/main/resources/reports/"+CommonUtils.getStringDateAndTimeStamp()+"_SFDC.PNG";
}
