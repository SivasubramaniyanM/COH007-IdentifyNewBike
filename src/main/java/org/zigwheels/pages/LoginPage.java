package org.zigwheels.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CommonCode;

import java.time.Duration;

public class LoginPage extends CommonCode {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(@data-track-label,'Google')]")
    WebElement google;

    @FindBy(id = "identifierId")
    WebElement email;

    @FindBy(xpath = "//span[contains(text(),'Next')]")
    WebElement next;

    public boolean isGoogleDisplayed() {
        waitForVisibility(google);
        return google.isDisplayed();
    }

    public void clickGoogle() {
        waitForVisibility(google);
        clickByJS(google);
    }

    public void enterInvalidEmail(String invalidEmail) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("identifierId"))
        );
        emailField.clear();
        emailField.sendKeys(invalidEmail);
    }

    public String getEnteredEmail() {
        return email.getAttribute("value");
    }

    public void clickNext() {
        clickByJS(next);
    }
}