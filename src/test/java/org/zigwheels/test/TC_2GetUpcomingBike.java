package org.zigwheels.test;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import utilities.Log;

public class TC_2GetUpcomingBike extends BaseTest {
    @Test
    public void verifyUpcomingBikeURL() throws InterruptedException {
        Log.info("Entering Upcoming Bike URL page");
        HomePage hp = new HomePage(driver);
        hp.clickNewBikes();
        hp.clickUpcmngBikes();
        String url = p.getProperty("getbikesurl");
        String bikeUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(bikeUrl, url);
        Log.info("Upcoming Bike URL page verification completed");
        System.out.println("Upcoming Bike URL page verification completed");
    }
}
