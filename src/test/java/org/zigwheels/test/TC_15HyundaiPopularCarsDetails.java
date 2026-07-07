package org.zigwheels.test;
import java.util.List;
import basetest.BaseTest;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CarPage;
import org.zigwheels.pages.HomePage;
import utilities.ExportVehicleDetails;
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
        softAssert.assertTrue(
                cp.isPopularModelsDisplayed(),
                "Popular Models section is NOT displayed"
        );
        cp.goToPopularModels();
        cp.clickThirdPopularModel();
        List<String[]> carData = cp.getCarDetailsForExcel();
        softAssert.assertFalse(
                carData.isEmpty(),
                "No Used Cars found in Chennai"
        );
        softAssert.assertTrue(
                carData.size() > 0,
                "Car names list size is 0"
        );
        ExportVehicleDetails.writeHyundaiPopularCars(carData);
        Log.info("Total Cars Found : " + carData.size());
        Log.info("Hyundai Popular Cars extracted and stored in Excel successfully");
        softAssert.assertAll();
    }
}