package org.zigwheels.test;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.BikesPage;
import org.zigwheels.pages.HomePage;
import utilities.Log;

public class TC_11OlaElectricBike extends BaseTest{
    @Test
    public  void electricbike(){
    Log.info("Entering The Electric Bike");
    HomePage hp = new HomePage(driver);
    hp.clickNewBikes();
    hp.electricbike();
    BikesPage lp=new BikesPage(driver);
    lp.electricbike();
        Assert.assertEquals(
                lp.olabike(),
                p.getProperty("olabiketpyeslisted"),
                "ola electric bike listed"
        );
}
}
