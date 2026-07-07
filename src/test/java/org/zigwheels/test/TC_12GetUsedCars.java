package org.zigwheels.test;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import utilities.Log;

public class TC_12GetUsedCars extends BaseTest {
    @Test
    public void verifyUsedCarsURL()throws InterruptedException {
        Log.info("Navigating to Used Cars page");
        HomePage hp = new HomePage(driver);
        hp.clickMore();
        hp.clickUsedCars();
        String expectedUrl = p.getProperty("getcarurl");
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        Log.info("Used Cars URL verification completed");
    }
}