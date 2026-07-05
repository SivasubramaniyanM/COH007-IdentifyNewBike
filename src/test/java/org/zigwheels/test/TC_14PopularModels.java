package org.zigwheels.test;

import java.util.List;

import basetest.BaseTest;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CarPage;
import org.zigwheels.pages.HomePage;
import utilities.Log;

public class TC_14PopularModels extends BaseTest {
    public static List<String> popularModelsList;

    @Test
    public void verifyPopularModels() throws Exception {
        SoftAssert softAssert = new SoftAssert();

        Log.info("Navigating to Chennai Used Cars page");
        HomePage hp = new HomePage(driver);
        hp.clickMore();
        hp.clickUsedCars();

        CarPage cp = new CarPage(driver);
        cp.SearchCity("chennai");
        cp.clickChennai();

        // Soft check — URL matches Chennai Used Cars page
        String locationurl = p.getProperty("getchennaiurl");
        softAssert.assertEquals(
                driver.getCurrentUrl(),
                locationurl,
                "Failed to navigate to Chennai Used Cars page");

        cp.goToPopularModels();

        // Soft check — Popular Models section is displayed
        softAssert.assertTrue(
                cp.isPopularModelsDisplayed(),
                "Popular Models section is not displayed");
        Log.info("Popular Models section is displayed");

        popularModelsList = cp.getPopularModelNames();

        System.out.println("========== POPULAR MODELS ==========");
        for (String model : popularModelsList) {
            System.out.println(model);
        }

        // Soft check — Popular Models list is not empty
        softAssert.assertFalse(
                popularModelsList.isEmpty(),
                "Popular Models list is empty");

        // Soft check — At least one model found
        softAssert.assertTrue(
                popularModelsList.size() > 0,
                "Popular Models count is 0");

        Log.info("Total Popular Models Found : " + popularModelsList.size());
        Log.info("Popular Models section verified and model names extracted successfully");

        // Final call — reports all failures collected above
        softAssert.assertAll();
    }
}
