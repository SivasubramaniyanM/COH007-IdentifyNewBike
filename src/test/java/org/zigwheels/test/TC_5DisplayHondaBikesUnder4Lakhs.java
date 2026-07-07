package org.zigwheels.test;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.BikesPage;
import org.zigwheels.pages.HomePage;
import utilities.ExportVehicleDetails;
import utilities.Log;

import java.io.IOException;
import java.util.List;

public class TC_5DisplayHondaBikesUnder4Lakhs extends BaseTest {

    @Test
    public void displayHondaBikesUnder4Lakhs() throws IOException {
        Log.info("Opening Honda Bikes Page");
        HomePage hp = new HomePage(driver);
        hp.clickNewBikes();
        hp.clickUpcmngBikes();
        BikesPage bp = new BikesPage(driver);
        bp.clickHondaBrand();
<<<<<<< Updated upstream
        Assert.assertTrue(driver.getCurrentUrl().contains("honda"), "Honda page is not opened");
        List<String> names = bp.getHondaBikeNamesUnder4Lakhs();
        List<String> prices = bp.getHondaBikePricesUnder4Lakhs();
        List<String> dates = bp.getHondaBikeDatesUnder4Lakhs();
        Assert.assertFalse(names.isEmpty(), "No Honda bikes under 4 Lakhs found");
=======
        Assert.assertTrue(
                driver.getCurrentUrl().contains("honda"),
                "Honda page is not opened"
        );
        List<String> names = bp.getHondaBikeNamesUnder4Lakhs();
        List<String> prices = bp.getHondaBikePricesUnder4Lakhs();
        List<String> dates = bp.getHondaBikeDatesUnder4Lakhs();
        Assert.assertFalse(
                names.isEmpty(),
                "No Honda bikes under 4 Lakhs found"
        );

>>>>>>> Stashed changes
        ExportVehicleDetails.writeBikeDetailsToExcel(names, prices, dates);
        Log.info("Honda Bikes stored in Vehicle.xlsx successfully");
    }
}
