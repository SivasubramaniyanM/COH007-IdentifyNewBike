package org.zigwheels.test;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CarPage;
import org.zigwheels.pages.HomePage;
import utilities.Log;

public class TC_19CarOffer extends BaseTest{
    @Test
    public void CarOffers() {
        HomePage hp = new HomePage(driver);
        CarPage c = new CarPage(driver);
        hp.clickNewCars();
        hp.clickCarOffer();
        c.clickOfferDetail();
        c.verifyOfferButtonVisible();
        Assert.assertTrue(c.isContinueButtonDisplayed(),
                "'Continue to View Offers' button is displayed");
        String expectedUrl = p.getProperty("getofferurl");
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        Log.info("View Offers display verification completed");
    }
}