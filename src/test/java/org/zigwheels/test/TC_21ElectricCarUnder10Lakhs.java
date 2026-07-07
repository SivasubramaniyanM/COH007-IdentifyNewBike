package org.zigwheels.test;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CarPage;
import org.zigwheels.pages.HomePage;

import utilities.ExportVehicleDetails;
import utilities.Log;

public class TC_21ElectricCarUnder10Lakhs extends BaseTest {
    @Test
    public void verifyElectricCarsUnder10LakhsPage() {
        HomePage hp = new HomePage(driver);
        CarPage cp = new CarPage(driver);
        Log.info("Verify Electric Cars Under 10 Lakhs Page ");
        hp.clickNewCars();
        Log.info("Clicked New Cars");
        hp.clickElectricCars();
        Log.info("Clicked Electric Cars");
        cp.clickUnder10Lakh();
        Log.info("Clicked Under 10 Lakh");
        String actualHeading = cp.getUnder10LakhHeading();
        Assert.assertEquals(
                actualHeading,
                "Electric Cars Under 10 Lakhs",
                "User is not navigated to Electric Cars Under 10 Lakhs page"
        );
        Log.info("Successfully verified Electric Cars Under 10 Lakhs page");
    }
}
