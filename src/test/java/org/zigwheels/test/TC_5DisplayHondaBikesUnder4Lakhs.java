package org.zigwheels.test;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.BikesPage;
import org.zigwheels.pages.HomePage;
import utilities.Log;

public class TC_5DisplayHondaBikesUnder4Lakhs extends BaseTest {
    @Test
    public void displayHondaBikesUnder4Lakhs() {
        Log.info("Opening Honda Bikes Page");
        HomePage hp = new HomePage(driver);
        hp.clickNewBikes();
        hp.clickUpcmngBikes();
        BikesPage bp = new BikesPage(driver);
        bp.clickHondaBrand();
        Assert.assertTrue(
                driver.getCurrentUrl().contains("honda"),
                "Honda page is not opened"
        );
        bp.getHondaBikesUnder4Lakhs();
        Log.info("Honda Bikes under 4 Lakhs displayed successfully");
    }
}