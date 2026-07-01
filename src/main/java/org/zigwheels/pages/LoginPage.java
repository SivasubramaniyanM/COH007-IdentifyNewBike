package org.zigwheels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends StandardCode {
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath  ="(//*[contains(text(),'Google')])[3]")
    public WebElement google;
    public void clickGoogle()
    {
        super.clickByJS(google);
    }
}