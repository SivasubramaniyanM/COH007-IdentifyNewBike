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
        System.out.println();
        System.out.println("==============================================");
        System.out.println("      LAST MONTH TOP CONTRIBUTORS");
        System.out.println("==============================================");
        int count = 1;
        for (String contributor : contributors) {
            if (contributor != null &&
                    !contributor.trim().isEmpty()) {
                System.out.println(
                        count + ". " + contributor
                );
                count++;
            }
        }
        System.out.println("==============================================");
        System.out.println();
        softAssert.assertTrue(
                count > 1,
                "No contributors found"
        );
        logger.info(
                "Total Contributors Found : " + (count - 1)
        );
        softAssert.assertAll();
    }
}