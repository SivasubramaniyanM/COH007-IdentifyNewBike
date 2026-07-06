package org.zigwheels.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseTest;
import org.zigwheels.pages.BikesPage;
import org.zigwheels.pages.HomePage;

public class TC_8HeroDealerDetails extends BaseTest {
    @Test
    public void heroDealerDetails() {
        HomePage hp = new HomePage(driver);
        BikesPage bp = new BikesPage(driver);
        hp.clickNewBikes();
        hp.clickBikeDealers();
        bp.clickChennai();
        bp.clickHeroMotoCorp();
        bp.getHeroDealerDetails();
        Assert.assertTrue(driver.getPageSource().contains("Hero"), "Hero page not loaded");
        logger.info("Hero dealer details extracted successfully");
    }
}