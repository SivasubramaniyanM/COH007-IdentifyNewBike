package org.zigwheels.test;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CarPage;
import org.zigwheels.pages.HomePage;
import utilities.Log;

public class TC_20CarOfferInvalidPhno extends BaseTest {
    @Test
    public void InvalidNo() {
        HomePage hp = new HomePage(driver);
        CarPage c= new CarPage(driver);
        hp.clickNewCars();
        hp.clickCarOffer();
        c.clickOfferDetail();
        c.SelectYourCity(p.getProperty("city"));
        Log.info("Selected City");
        c.SelectPinCode(p.getProperty("pincode"));
        Log.info("Selected Pin Code");
        c.SelectAgency();
        Log.info("Selected Agency");
        c.ClickOffer();
        Log.info("Clicked on Offer");
        c.EnterMobNo(p.getProperty("invalidmobileno"));
        Log.info("Entered Mobile Number");
        Assert.assertEquals(c.GetText(), "Please enter valid mobile number");
        Log.info("Invalid Mobile Number verification completed");

    }
}


