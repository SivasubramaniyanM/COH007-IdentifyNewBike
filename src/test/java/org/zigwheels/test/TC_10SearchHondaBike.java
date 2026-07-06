    package org.zigwheels.test;
    import basetest.BaseTest;
    import org.testng.Assert;
    import org.testng.annotations.Test;
    import org.zigwheels.pages.HomePage;
    import utilities.Log;
    import org.zigwheels.pages.BikesPage;

    public class TC_10SearchHondaBike extends BaseTest {
        @Test
        public void SearchHonda() {
            Log.info("Searching the Honda Bike");
            HomePage hp = new HomePage(driver);
            hp.clickNewBikes();
            hp.SearchHonda();
            BikesPage bp = new BikesPage(driver);
            bp.searchHondaActiva125();
            Assert.assertEquals(
                    bp.getBikeName(),
                    p.getProperty("bikename"),
                    "Bike name does not match"
            );

            Log.info("Honda Activa 125 verified successfully");
        }
    }
