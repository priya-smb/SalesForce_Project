package com.salesforce;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.salesforce.utils.CommonUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        Object testClass = result.getInstance();
        BaseTest baseTest = (BaseTest) testClass;
        baseTest.test.pass(MarkupHelper.createLabel(result.getName()+ " PASSED", ExtentColor.GREEN));

    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        BaseTest baseTest = (BaseTest) testClass;
        baseTest.test.fail(MarkupHelper.createLabel(result.getName() + " FAILED", ExtentColor.RED));

        try {
            baseTest.test.addScreenCaptureFromPath(CommonUtils.getScreenshot(baseTest.getDriver()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
