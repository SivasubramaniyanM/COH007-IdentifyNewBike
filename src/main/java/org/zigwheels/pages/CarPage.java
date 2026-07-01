package org.zigwheels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CarPage extends StandardCode{

    public CarPage(WebDriver driver) {

        super(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Your City']")
    WebElement searchBar;

    @FindBy(xpath = "//a[@data-value='Chennai']")
    WebElement chennaiEle;

    public void SearchCity(String city){
        searchBar.sendKeys(city);
    }
    public  void clickChennai(){
        chennaiEle.click();
    }
}
