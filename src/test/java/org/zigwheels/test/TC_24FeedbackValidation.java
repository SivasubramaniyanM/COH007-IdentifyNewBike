package org.zigwheels.test;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;

public class TC_24FeedbackValidation extends BaseTest {
    @Test
    public void validateFeedbackSubmission() {
        HomePage hp = new HomePage(driver);
        logger.info("Scrolling to feedback section");
        hp.scrollToFeedback();
        logger.info("Clicking Feedback button");
        hp.clickFeedback();
        Assert.assertTrue(hp.validateExcellentEmoji(), "Excellent Emoji is not displayed");
        logger.info("Clicking Excellent Emoji");
        hp.clickExcellentEmoji();
        logger.info("Validating Feedback Textbox");
        Assert.assertTrue(hp.validateFeedbackTextBox(), "Feedback Textbox is not displayed");
        logger.info("Entering Feedback");
        hp.enterFeedback("Website navigation is simple and user friendly.");
        logger.info("Validating Submit Button");
        Assert.assertTrue(hp.validateSubmitButton(), "Submit Button is not displayed");
        logger.info("Clicking Submit Button");
        hp.clickSubmit();
        logger.info("Feedback submitted successfully");
    }
}