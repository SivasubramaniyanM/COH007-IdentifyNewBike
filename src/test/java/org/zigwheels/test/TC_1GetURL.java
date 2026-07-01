package org.zigwheels.test;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import utilities.Log;

public class TC_1GetURL extends BaseTest {
    @Test
    public void verify_url() throws InterruptedException {
        Log.info("Getting URL of the page");
        String url=p.getProperty("geturl");
        String baseUrl= driver.getCurrentUrl();
        Assert.assertEquals(baseUrl, url,"");
        HomePage hp=new HomePage(driver);

        Log.info("Base URL verification completed");
        System.out.println("Base URL verification completed");
    }
}
