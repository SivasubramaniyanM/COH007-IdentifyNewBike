package org.zigwheels.test;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.BikesPage;
import org.zigwheels.pages.HomePage;
import utilities.Log;

public class TC_4ExtractHondaBikeDetails extends BaseTest {
    @Test
    public void getHondaBikeDetails() {
        Log.info("Entering Honda Bikes Page");

        // ---------- Get page source once ----------
        String pageSource = driver.getPageSource().toLowerCase();

        HomePage hp = new HomePage(driver);
        hp.clickNewBikes();
        hp.clickUpcmngBikes();
        BikesPage bp = new BikesPage(driver);
        bp.clickHondaBrand();
        Assert.assertTrue(
                driver.getCurrentUrl().contains("honda"),
                "Honda page is not opened"
        );
        bp.getHondaBikeDetails();

        Assert.assertTrue(
                pageSource.contains("bike") || pageSource.contains("model"),
                "Page source does not contain expected bike/model content"
        );


        Log.info("Honda Bike details extracted successfully");
    }
}