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

public class TC_23InvalidGoogleLogin extends BaseTest {
    @Test
    public void verifyInvalidGoogleLogin() throws Exception {
        Log.info("Entering Login Page");
        HomePage hp = new HomePage(driver);
        hp.clickLogin();
        LoginPage lp = new LoginPage(driver);
        lp.clickGoogle();
        String parentWindow = driver.getWindowHandle();
        wait.until(driver -> driver.getWindowHandles().size() > 1);
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("identifierId"))
        );
        String invalidEmail = "abc@gmail.com";
        lp.enterInvalidEmail(invalidEmail);
        Assert.assertEquals(
                lp.getEnteredEmail(),
                invalidEmail,
                "Email was not entered correctly"
        );
        lp.clickNext();
        takeScreenShot(driver, "InvalidGoogleLoginError");
        File screenshotFile = new File(
                System.getProperty("user.dir")
                        + "/MajorProject/screenshots/InvalidGoogleLoginError.png"
        );
        Assert.assertTrue(
                screenshotFile.exists(),
                "Screenshot was not captured"
        );
        Log.info("Invalid Google Login verified successfully");
        System.out.println("Screenshot captured successfully");
    }
}