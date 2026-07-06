package org.zigwheels.test;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.CarPage;
import org.zigwheels.pages.HomePage;

public class TC_18CarComparisons extends BaseTest {
    @Test
    public void CarComparisons() {
        HomePage hp = new HomePage(driver);
        hp.clickNewCars();
        hp.clickCarComparisons();
        CarPage cp = new CarPage(driver);
        cp.clickAddOrEditCar1();
        cp.selectCar(
                p.getProperty("car1brandOrModel"),
                p.getProperty("car1variant")
        );
        cp.clickAddOrEditCar2();
        cp.selectCar(
                p.getProperty("car2brandOrModel"),
                p.getProperty("car2variant")
        );
        cp.clickCompareCars();
        cp.displayPriceAndCCComparison();
        Assert.assertTrue(
                driver.getCurrentUrl().contains("compare"),
                "Comparison page not opened"
        );
        Assert.assertTrue(
                cp.getComparisonHeading().contains("Key Highlights"),
                "Comparison table is not displayed"
        );
    }
}
