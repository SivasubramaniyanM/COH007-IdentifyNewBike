package org.zigwheels.test;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CarPage;
import org.zigwheels.pages.HomePage;
import basetest.BaseTest;
import utilities.Log;

public class TC_17CheapestAndCostliestCar extends BaseTest {
    @Test
    public void findCheapestAndCostliestCar() throws Exception {
        HomePage hp = new HomePage(driver);
        hp.hoverMore();
        hp.hoverAndClickUsedCars();
        CarPage cp = new CarPage(driver);
        cp.SearchCity("chennai");
        cp.clickChennai();
        cp.waitForCarsToLoad();
        List<String> names = cp.getCarNames();
        List<String> prices = cp.getCarPrices();
        Assert.assertFalse(names.isEmpty(), "No cars found");
        Assert.assertFalse(prices.isEmpty(), "No prices found");
        String[] result = cp.getCheapestAndCostliestCar(names, prices);
        String cheapestCar = result[0];
        double minPrice = Double.parseDouble(result[1]);
        String costliestCar = result[2];
        double maxPrice = Double.parseDouble(result[3]);
        Log.info("CHEAPEST CAR");
        Log.info(String.format("%s - Rs. %.2f Lakh", cheapestCar, minPrice));
        Log.info("COSTLIEST CAR");
        Log.info(String.format("%s - Rs. %.2f Lakh", costliestCar, maxPrice));
        Assert.assertFalse(cheapestCar.isEmpty(), "Cheapest car not found");
        Assert.assertFalse(costliestCar.isEmpty(), "Costliest car not found");
        Log.info("Cheapest and Costliest cars identified successfully");
    }
}