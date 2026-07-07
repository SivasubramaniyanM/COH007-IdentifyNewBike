package org.zigwheels.test;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.BikesPage;
import utilities.Log;
import org.zigwheels.pages.HomePage;
import java.util.List;

public class TC_7UnrevealedLaunchBike extends BaseTest {
    @Test
    public void displayUnrevealedLaunchDateBikes() {
        Log.info("Opening Upcoming Bikes Page");
        HomePage hp = new HomePage(driver);
        hp.clickNewBikes();
        hp.clickUpcmngBikes();
        BikesPage bp = new BikesPage(driver);
        bp.clickHondaBrand();
        Assert.assertTrue(driver.getCurrentUrl().contains("honda"), "Honda page is not opened");
        List<String> unrevealedBikes = bp.getUnrevealedLaunchDateBikes();
        Log.info("Total unrevealed Honda bikes under 4 Lakhs : " + unrevealedBikes.size());
        for (String bike : unrevealedBikes) {
            System.out.println(" -> " + bike);
            Log.info("Unrevealed Honda bike : " + bike);
        }
        Assert.assertNotNull(unrevealedBikes, p.getProperty("bikenotnull"));
        Log.info("Honda unrevealed launch date bikes verification completed");
    }
}