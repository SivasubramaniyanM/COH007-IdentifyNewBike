package org.zigwheels.test;

import java.util.ArrayList;
import java.util.List;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CarPage;
import org.zigwheels.pages.HomePage;

import utilities.ExportVehicleDetails;
import utilities.Log;

public class TC_16PopularCarsUnder4Lakh extends BaseTest {
    @Test
    public void extractCarsUnder4Lakh() throws Exception {
        Log.info("Navigating to Chennai Used Cars Page");
        HomePage hp = new HomePage(driver);
        hp.clickMore();
        hp.clickUsedCars();
        CarPage cp = new CarPage(driver);
        cp.SearchCity("chennai");
        cp.clickChennai();
        cp.waitForCarsToLoad();
        List<String> names = cp.getCarNames();
        List<String> prices = cp.getCarPrices();
        Assert.assertFalse(names.isEmpty(), "No car names extracted");
        Assert.assertFalse(prices.isEmpty(), "No car prices extracted");
        List<String> carsUnder4Lakh = new ArrayList<>();
        int limit = Math.min(names.size(), prices.size());
        for (int i = 0; i < limit; i++) {
            double lakh = cp.parsePriceInLakh(prices.get(i));
            if (lakh > 0 && lakh < 4.0) {
                carsUnder4Lakh.add(
                        names.get(i) + " -> " + prices.get(i)
                );
            }
        }
        Log.info("Total Cars Under 4 Lakh : " + carsUnder4Lakh.size());
        ExportVehicleDetails.writeCarDetailsToExcel(carsUnder4Lakh);
        Assert.assertFalse(carsUnder4Lakh.isEmpty(), "No cars found under Rs. 4 Lakh in Chennai");
    }
}