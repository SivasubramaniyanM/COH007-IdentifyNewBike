package org.zigwheels.test;
import org.zigwheels.pages.HomePage;
import java.util.List;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CarPage;
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

        List<String> names  = cp.getCarNames();
        List<String> prices = cp.getCarPrices();

        Assert.assertFalse(names.isEmpty(),  "No cars found");
        Assert.assertFalse(prices.isEmpty(), "No prices found");

        double minPrice = Double.MAX_VALUE;
        double maxPrice = Double.MIN_VALUE;
        String cheapestCar  = "";
        String costliestCar = "";

        int count = Math.min(names.size(), prices.size());
        for (int i = 0; i < count; i++) {
            double price = parsePriceInLakh(prices.get(i));
            if (price <= 0) {
                System.out.println("Unable to parse price : " + prices.get(i));
                continue;
            }
            if (price < minPrice) {
                minPrice    = price;
                cheapestCar = names.get(i);
            }
            if (price > maxPrice) {
                maxPrice     = price;
                costliestCar = names.get(i);
            }
        }

        System.out.println("\n===== CHEAPEST CAR =====");
        System.out.println(cheapestCar + " - Rs. " + minPrice + " Lakh");

        System.out.println("\n===== COSTLIEST CAR =====");
        System.out.println(costliestCar + " - Rs. " + maxPrice + " Lakh");

        Assert.assertFalse(cheapestCar.isEmpty(),  "Cheapest car not found");
        Assert.assertFalse(costliestCar.isEmpty(), "Costliest car not found");

        Log.info("Cheapest and Costliest cars identified successfully");
    }

    public double parsePriceInLakh(String priceText) {
        try {
            String cleaned = priceText
                    .replaceAll("(?i)Rs\\.?", "")
                    .replaceAll("(?i)Lakh", "")
                    .replaceAll("[₹,]", "")
                    .trim();
            return Double.parseDouble(cleaned.split("\\s+")[0]);
        } catch (Exception e) {
            return -1;
        }
    }
}