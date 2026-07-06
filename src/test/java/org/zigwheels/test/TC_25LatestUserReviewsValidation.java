package org.zigwheels.test;

import java.util.List;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import basetest.BaseTest;
import org.zigwheels.pages.HomePage;

public class TC_25LatestUserReviewsValidation extends BaseTest {
    @Test
    public void validateLatestUserReviews() {
        SoftAssert softAssert = new SoftAssert();
        HomePage hp = new HomePage(driver);
        logger.info("Scrolling to Latest User Reviews");
        hp.scrollToLatestUserReviews();
        logger.info("Fetching first 3 reviews");
        List<String> reviews = hp.getFirstThreeReviews();
        System.out.println();
        System.out.println("=======================================");
        System.out.println("        LATEST USER REVIEWS");
        System.out.println("=======================================");
        int count = 1;
        for (String review : reviews) {
            System.out.println();
            System.out.println("Review " + count + ":");
            System.out.println(review);
            count++;
        }
        System.out.println();
        System.out.println("=======================================");
        softAssert.assertEquals(
                reviews.size(),
                3,
                "Unable to retrieve 3 reviews"
        );
        softAssert.assertAll();
    }
}