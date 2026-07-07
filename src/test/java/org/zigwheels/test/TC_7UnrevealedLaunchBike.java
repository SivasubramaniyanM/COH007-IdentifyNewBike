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
<<<<<<< Updated upstream
        bp.clickHondaBrand();
        Assert.assertTrue(driver.getCurrentUrl().contains("honda"), "Honda page is not opened");
        List<String> unrevealedBikes = bp.getUnrevealedLaunchDateBikes();
        Log.info("Total unrevealed Honda bikes under 4 Lakhs : " + unrevealedBikes.size());
=======
        //  Click Honda brand filter
        bp.clickHondaBrand();
        Assert.assertTrue(
                driver.getCurrentUrl().contains("honda"),
                "Honda page is not opened"
        );
        List<String> unrevealedBikes = bp.getUnrevealedLaunchDateBikes();
        Log.info("Total unrevealed Honda bikes under 4 Lakhs : " + unrevealedBikes.size());
        System.out.println("Honda Bikes with Unrevealed Launch Date (Under 4 Lakhs) : ");
>>>>>>> Stashed changes
        for (String bike : unrevealedBikes) {
            System.out.println(" -> " + bike);
            Log.info("Unrevealed Honda bike : " + bike);
        }
<<<<<<< Updated upstream
        Assert.assertNotNull(unrevealedBikes, p.getProperty("bikenotnull"));
=======
        Assert.assertNotNull(unrevealedBikes, "Bike list should not be null");
>>>>>>> Stashed changes
        Log.info("Honda unrevealed launch date bikes verification completed");
    }
}