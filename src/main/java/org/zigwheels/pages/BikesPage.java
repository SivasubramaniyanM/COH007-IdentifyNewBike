package org.zigwheels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BikesPage extends StandardCode {

    public BikesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Honda']")
    WebElement hondaLink;


    public void clickHondaBrand() {

        waitForVisibility(hondaLink);

        scrollIntoView(hondaLink);

        clickByJS(hondaLink);
    }
}
//    public void clickHondaBrand() {
//
//        js.executeScript(
//                "arguments[0].scrollIntoView({block:'center'});",
//                hondaLink
//        );
//
//        js.executeScript("arguments[0].click();", hondaLink);
//
//    }