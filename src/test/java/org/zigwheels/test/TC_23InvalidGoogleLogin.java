package org.zigwheels.test;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.LoginPage;
import utilities.Log;
import java.io.File;
import java.time.Duration;

public class TC_23InvalidGoogleLogin extends BaseTest {

    @Test
    public void verifyInvalidGoogleLogin() throws Exception {
        Log.info("Entering Login Page");
        HomePage hp = new HomePage(driver);
        hp.clickLogin();
        LoginPage lp = new LoginPage(driver);
        lp.clickGoogle();
        String parentWindow = driver.getWindowHandle();
        // Switch to popup if opened
        try {
            wait.withTimeout(Duration.ofSeconds(8))
                    .until(d -> d.getWindowHandles().size() > 1);
            for (String window : driver.getWindowHandles()) {
                if (!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    Log.info("Switched to Google Sign-In popup window");
                    break;
                }
            }
        } catch (Exception e) {
            Log.info("Google Sign-In opened in same tab");
        }
        wait.withTimeout(Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("identifierId")));
        lp.enterInvalidEmail(p.getProperty("invalidEmail"));
        Assert.assertEquals(lp.getEnteredEmail(), (p.getProperty("invalidEmail")), "Email was not entered correctly");
        lp.clickNext();
        wait.until(driver -> {
            try {
                String txt = driver.findElement(By.id("headingText")).getText().toLowerCase();
                return txt.contains("couldn") || txt.contains("sign you in");
            } catch (Exception e) {
                return false;
            }
        });
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Try')]")));
        String errorHeading = driver.findElement(By.id("headingText")).getText();
        Log.info("Error message displayed : " + errorHeading);
        Assert.assertTrue(errorHeading.toLowerCase().contains("couldn") || errorHeading.toLowerCase().contains("sign you in"), "Expected error message not displayed. Actual : " + errorHeading);
        takeScreenShot(driver, "InvalidGoogleLoginError");
        File screenshotFile = new File(System.getProperty("user.dir") + "/screenshots/InvalidGoogleLoginError.png");

        Log.info("Invalid Google Login verified successfully");

    }
}
