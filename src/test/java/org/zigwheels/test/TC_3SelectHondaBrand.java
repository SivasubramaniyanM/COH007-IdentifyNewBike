package org.zigwheels.test;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.BikesPage;
import org.zigwheels.pages.HomePage;

public class TC_3SelectHondaBrand extends BaseTest{
    @Test
    public void selectHondaBrand() {
        HomePage hp = new HomePage(driver);
        hp.clickNewBikes();
        hp.clickUpcmngBikes();
        BikesPage bp = new BikesPage(driver);
        bp.clickHondaBrand();
        System.out.println(driver.getCurrentUrl());
        Assert.assertTrue(
                driver.getPageSource().contains("Honda"),
                "Honda not found on page"
        );
    }
}
