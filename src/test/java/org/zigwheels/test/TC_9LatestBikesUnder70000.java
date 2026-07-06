package org.zigwheels.test;
import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;
import org.zigwheels.pages.BikesPage;
import org.zigwheels.pages.HomePage;

public class TC_9LatestBikesUnder70000 extends BaseTest {
    @Test
    public void latestBikesUnder70000() {
        HomePage hp = new HomePage(driver);
        BikesPage bp = new BikesPage(driver);
        hp.clickNewBikes();
        hp.clickLatestBikes();
        bp.clickUnder70000();
        Assert.assertTrue(bp.getBikeCount() > 0, "No bikes are displayed under 70000");
        logger.info("Under 70000 bikes displayed successfully");
    }
}