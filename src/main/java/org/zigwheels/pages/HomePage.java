package org.zigwheels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends StandardCode {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(.,'NEW BIKES')]")
    WebElement newBikes;

    @FindBy(linkText = "Upcoming Bikes")
    WebElement upcomingBikes;

    @FindBy(xpath = "//ul[contains(@class,'h-d-nav fnt')]/li[5]/span[contains(text(),'MORE')]")
    WebElement moreMenu;

    @FindBy(xpath = "//a[@title='Used Cars']")
    WebElement usedCars;

    public void clickNewBikes() {
        clickElement(newBikes);
        System.out.println("NEW BIKES clicked");
    }
    public void clickUpcmngBikes() {
        waitForVisibility(upcomingBikes);
        clickElement(upcomingBikes);
        System.out.println("Upcoming Bikes clicked");
    }
    public void clickMore() {
        moreMenu.click();
    }

    public void clickUsedCars() {
        waitForVisibility(usedCars);
        clickByJS(usedCars);
        System.out.println("Used Cars clicked");
    }
}