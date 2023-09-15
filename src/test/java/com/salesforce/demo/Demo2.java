package com.salesforce.demo;

import com.salesforce.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class Demo2 extends BaseTest {

    protected static Logger logger = LogManager.getLogger(Demo2.class);

    @Test
    public void method1() {
        logger.info("----- method1 -------");
    }

    @Test
    public void method2() {
        logger.info("----- method2 -------");
    }
}
