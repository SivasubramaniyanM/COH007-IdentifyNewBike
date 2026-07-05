package org.zigwheels.test;

import java.util.List;

import basetest.BaseTest;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CarPage;
import org.zigwheels.pages.HomePage;
import utilities.Log;

public class TC_15HyundaiPopularCarsDetails extends BaseTest {

    @Test
    public void extractUsedCarDetails() throws Exception {
        SoftAssert softAssert = new SoftAssert();

        Log.info("Navigating to Chennai Used Cars Page");
        HomePage hp = new HomePage(driver);
        hp.clickMore();
        hp.clickUsedCars();

        CarPage cp = new CarPage(driver);
        cp.SearchCity("chennai");
        cp.clickChennai();

        // Soft check — Popular Models section is displayed
        softAssert.assertTrue(
                cp.isPopularModelsDisplayed(),
                "Popular Models section is NOT displayed");

        cp.goToPopularModels();
        cp.clickThirdPopularModel();

        List<String> carNames = cp.getCarNames();

        // Soft check — Car names list is not empty
        softAssert.assertFalse(
                carNames.isEmpty(),
                "No Used Cars found in Chennai");

        // Soft check — At least one car should be available
        softAssert.assertTrue(
                carNames.size() > 0,
                "Car names list size is 0");

        System.out.println("===== USED CAR NAMES =====");
        for (String car : carNames) {
            System.out.println(car);
        }

        Log.info("Total Cars Found : " + carNames.size());
        Log.info("Used car names extracted successfully");

        // Final call — reports all failures collected above
        softAssert.assertAll();
    }
}
