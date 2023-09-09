package com.salesforce.utils;

import com.salesforce.constants.FileConstants;
import com.salesforce.utils.FileUtils;

import java.util.Properties;

public class ConfigUtil {

    static Properties configs;

    public static void readConfig() {
        configs = FileUtils.readAllProperties(FileConstants.LOGIN_TESTDATA_FILE_PATH2);
    }

    static {
        readConfig();
    }

    public static String getProperty(String key) {
        return configs.getProperty(key);
    }


}
