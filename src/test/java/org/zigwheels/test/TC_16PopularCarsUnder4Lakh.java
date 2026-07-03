package org.zigwheels.test;
import java.util.List;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CarPage;
import org.zigwheels.pages.HomePage;
import utilities.Log;

public class TC_16PopularCarsUnder4Lakh extends BaseTest {
    @Test
    public void extractCarsUnder4Lakh() throws Exception {
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
        Assert.assertFalse(names.isEmpty(), "No cars found");
        System.out.println("===== CARS BELOW 4 LAKH =====");
        for (int i = 0; i < Math.min(names.size(), prices.size()); i++) {
            try {
                String priceText = prices.get(i)
                        .replace("Rs.", "")
                        .replace("₹", "")
                        .replace("Lakh", "")
                        .replace(",", "")
                        .trim();
                double price = Double.parseDouble(priceText);
                if (price < 4.0) {
                    System.out.println(
                            names.get(i)
                                    + " - Rs. "
                                    + price
                                    + " Lakh"
                    );
                }
            } catch (Exception e) {
                System.out.println(
                        "Unable to parse price : "
                                + prices.get(i)
                );
            }
        }
        Log.info("Cars below 4 lakh displayed successfully");
    }
}

