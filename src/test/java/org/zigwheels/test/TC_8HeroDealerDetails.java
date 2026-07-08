package org.zigwheels.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseTest;
import org.zigwheels.pages.BikesPage;
import org.zigwheels.pages.HomePage;

public class TC_8HeroDealerDetails extends BaseTest {
    @Test
    public void heroDealerDetails()throws Exception{
        HomePage hp = new HomePage(driver);
        BikesPage bp = new BikesPage(driver);
        hp.clickNewBikes();
        hp.clickBikeDealers();
        bp.clickChennai();
        bp.clickHeroMotoCorp();
        takeScreenShot(driver, "Hero Dealer Details");
        bp.getHeroDealerDetails();
        Assert.assertTrue(driver.getPageSource().contains("Hero"), p.getProperty("nopageload"));
        logger.info("Hero dealer details extracted successfully");
    }
}
