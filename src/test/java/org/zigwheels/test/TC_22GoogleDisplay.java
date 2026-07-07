package org.zigwheels.test;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.LoginPage;
import utilities.Log;

public class TC_22GoogleDisplay extends BaseTest {
    @Test
    public void isGoogleDisplayed(){
        Log.info("Entering the Login page");
        HomePage hp=new HomePage(driver);
        hp.clickLogin();
        LoginPage lp=new LoginPage(driver);
        boolean google = lp.google.isDisplayed();
        Assert.assertTrue(google);
        Log.info("Successfully entered into the Login page");
    }
}
