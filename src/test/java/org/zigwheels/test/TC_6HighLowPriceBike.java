package org.zigwheels.test;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.BikesPage;
import org.zigwheels.pages.HomePage;
import utilities.Log;

public class TC_6HighLowPriceBike extends BaseTest {
    @Test
    public void displayHighAndLowPriceBike() {
        Log.info("Opening Upcoming Bikes Page");
        HomePage hp = new HomePage(driver);
        hp.clickNewBikes();
        hp.clickUpcmngBikes();
        BikesPage bp = new BikesPage(driver);
        String highestPriceBike = bp.getHighestPriceBikeName();
        Log.info("Highest Price Bike : " + highestPriceBike);
        String lowestPriceBike = bp.getLowestPriceBikeName();
        Log.info("Lowest Price Bike : " + lowestPriceBike);
        Assert.assertNotNull(highestPriceBike, p.getProperty("higehstbikepricenull"));
        Assert.assertNotNull(lowestPriceBike, p.getProperty("lowesrbikepricenull"));
        Log.info("High and Low price bikes displayed successfully");
    }
}