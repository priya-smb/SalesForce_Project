package com.salesforce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SampleLog {
    static Logger logger = LogManager.getLogger(SampleLog.class);
    public static void main(String[] args) {
        logger.info("test--------");
        logger.error("test--------");

    }
}
