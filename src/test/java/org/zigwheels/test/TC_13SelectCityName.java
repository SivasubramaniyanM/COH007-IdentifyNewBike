package org.zigwheels.test;

import basetest.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CarPage;
import org.zigwheels.pages.HomePage;
import utilities.Log;

public class TC_13SelectCityName extends BaseTest {
    @Test
    public void GoTocity() throws Exception {
        Log.info("Navigating to city page");
        HomePage hp = new HomePage(driver);
        hp.clickMore();
        hp.clickUsedCars();
        CarPage cp = new CarPage(driver);
        cp.SearchCity("chennai");
        cp.clickChennai();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.zigwheels.com/used-car/Chennai");
        Log.info("select city name completed");
        System.out.println("select city name completed");



    }
}

