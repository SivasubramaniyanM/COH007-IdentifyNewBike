package org.zigwheels.test;
import java.util.List;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CarPage;
import org.zigwheels.pages.HomePage;
import utilities.Log;

public class TC_17CheapestAndCostliestCar extends BaseTest {
    @Test
    public void findCheapestAndCostliestCar() throws Exception {
        HomePage hp = new HomePage(driver);
        hp.clickMore();
        hp.clickUsedCars();
        CarPage cp = new CarPage(driver);
        cp.SearchCity("chennai");
        cp.clickChennai();
        cp.goToPopularModels();
        cp.selectAllPopularModels();
        cp.waitForCarsToLoad();
        List<String> names = cp.getCarNames();
        List<String> prices = cp.getCarPrices();
        Assert.assertFalse(
                names.isEmpty(),
                "No cars found"
        );
        double minPrice = Double.MAX_VALUE;
        double maxPrice = Double.MIN_VALUE;
        String cheapestCar = "";
        String costliestCar = "";
        int count = Math.min(names.size(), prices.size());
        for (int i = 0; i < count; i++) {
            try {
                String priceText = prices.get(i)
                        .replace("Rs.", "")
                        .replace("₹", "")
                        .replace("Lakh", "")
                        .replace(",", "")
                        .trim();
                double price = Double.parseDouble(priceText);
                if (price < minPrice) {
                    minPrice = price;
                    cheapestCar = names.get(i);
                }
                if (price > maxPrice) {
                    maxPrice = price;
                    costliestCar = names.get(i);
                }
            } catch (Exception e) {
                System.out.println(
                        "Unable to parse price : "
                                + prices.get(i)
                );
            }
        }
        System.out.println("\n===== CHEAPEST CAR =====");
        System.out.println(
                cheapestCar + " - Rs. "
                        + minPrice + " Lakh"
        );
        System.out.println("\n===== COSTLIEST CAR =====");
        System.out.println(
                costliestCar + " - Rs. "
                        + maxPrice + " Lakh"
        );
        Assert.assertFalse(
                cheapestCar.isEmpty(),
                "Cheapest car not found"
        );
        Assert.assertFalse(
                costliestCar.isEmpty(),
                "Costliest car not found"
        );
        Log.info("Cheapest and Costliest cars identified successfully");
    }
}