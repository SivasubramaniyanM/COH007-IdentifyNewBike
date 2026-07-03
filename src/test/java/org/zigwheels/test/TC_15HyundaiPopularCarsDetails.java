package org.zigwheels.test;
import java.util.List;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CarPage;
import org.zigwheels.pages.HomePage;
import utilities.Log;

public class TC_15HyundaiPopularCarsDetails extends BaseTest {
    @Test
    public void extractUsedCarDetails() throws Exception {
        Log.info("Navigating to Chennai Used Cars Page");
        HomePage hp = new HomePage(driver);
        hp.clickMore();
        hp.clickUsedCars();
        CarPage cp = new CarPage(driver);
        cp.SearchCity("chennai");
        cp.clickChennai();
        cp.goToPopularModels();
        cp.clickThirdPopularModel();
        List<String> carNames = cp.getCarNames();
        Assert.assertFalse(
                carNames.isEmpty(),
                "No Used Cars found");
        System.out.println("===== USED CAR NAMES =====");
        for (String car : carNames) {
            System.out.println(car);
        }
        Log.info("Total Cars Found : " + carNames.size());
        Log.info("Used car names extracted successfully");
    }
}