package org.zigwheels.test;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.BikesPage;
import org.zigwheels.pages.HomePage;
import utilities.ExportVehicleDetails;
import utilities.ExtentReportManager;
import utilities.Log;
import utilities.ExtentReportManager;

import java.io.IOException;
import java.util.*;

public class TC_4ExtractHondaBikeDetails extends BaseTest {
    @Test
    public void getHondaBikeDetails() throws IOException {
        Log.info("Entering Honda Bikes Page");
        HomePage hp = new HomePage(driver);
        hp.clickNewBikes();
        hp.clickUpcmngBikes();
        BikesPage bp = new BikesPage(driver);
        bp.clickHondaBrand();
        Assert.assertTrue(
                driver.getCurrentUrl().contains("honda"),
                "Honda page is not opened"
        );
        List<String[]> bikeData = bp.getHondaBikeDetailsForExcel();
        ExportVehicleDetails.writeHondaBikeDetails(bikeData);
        Assert.assertFalse(
                bikeData.isEmpty(),
                "Honda bike details are empty"
        );
        Log.info("Honda bike details extracted and stored successfully");


        Log.info("Honda Bike details extracted successfully");
    }
}
