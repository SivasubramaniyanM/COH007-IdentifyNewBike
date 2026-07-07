package org.zigwheels.test;

import java.util.List;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import basetest.BaseTest;
import org.zigwheels.pages.HomePage;

public class TC_26TopContributorsValidation extends BaseTest {
    @Test
    public void validateLastMonthTopContributors() {
        SoftAssert softAssert = new SoftAssert();
        HomePage hp = new HomePage(driver);
        logger.info("Scrolling to Last Month Top Contributors section");
        hp.scrollToTopContributors();
        logger.info("Fetching contributor names");
        List<String> contributors = hp.getTopContributorNames();
        logger.info("Total Contributors Found : " + (contributors.size() - 1));
        softAssert.assertAll();
    }
}