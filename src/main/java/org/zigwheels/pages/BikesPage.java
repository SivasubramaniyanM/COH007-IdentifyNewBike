
package org.zigwheels.pages;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonCode;

public class BikesPage extends CommonCode {

    // ---------- Constructor ----------
    public BikesPage(WebDriver driver) {
        super(driver);
    }

    // ---------- Web Elements (Locators) ----------
    @FindBy(xpath = "//a[normalize-space()='Honda']")
    WebElement hondaLink;

    @FindBy(xpath = "//ul[@id='modelList']/li")
    List<WebElement> hondaBikes;

    @FindBy(xpath = "//ul[@id='modelList']/li//strong")
    List<WebElement> bikeNames;

    @FindBy(xpath = "//ul[@id='modelList']/li//*[contains(text(),'Rs.')]")
    List<WebElement> bikePrices;

    @FindBy(xpath = "//ul[@id='modelList']/li//*[contains(text(),'Launch')]")
    List<WebElement> bikeLaunchDates;

    @FindBy(xpath = "//a[contains(@href,'/bikes/dealers/Chennai')]")
    WebElement chennai;

    @FindBy(xpath = "//a[contains(text(),'Hero Moto Corp')]")
    WebElement heroMotoCorp;

    @FindBy(xpath="//div[contains(@class,'deal-crd')]")
    List<WebElement> dealerCards;

    // ---------- Page Actions ----------

    /**
     * Click on the Honda brand link
     */
    public void clickHondaBrand() {
        waitForVisibility(hondaLink);
        scrollIntoView(hondaLink);
        clickByJS(hondaLink);
    }

    /**
     * Print details of all Honda bikes
     */
    public void getHondaBikeDetails() {
        System.out.println("Total Bikes Found : " + hondaBikes.size());

        for (int i = 0; i < hondaBikes.size(); i++) {
            try {
                String bikeName = bikeNames.get(i).getText();
                String price = bikePrices.get(i).getText();
                String launchDate = bikeLaunchDates.get(i).getText();

                System.out.println("Bike " + (i + 1));
                System.out.println("Bike Name   : " + bikeName);
                System.out.println("Price       : " + price);
                System.out.println("Launch Date : " + launchDate);
                System.out.println("--------------------------------");
            } catch (Exception e) {
                System.out.println("Unable to fetch details for Bike " + (i + 1));
            }
        }
    }

    /**
     * Print Honda bikes with price under 4 Lakhs
     */
    public void getHondaBikesUnder4Lakhs() {
        int bikeNumber = 1;

        for (int i = 0; i < hondaBikes.size(); i++) {
            try {
                String bikeName = bikeNames.get(i).getText();
                String priceText = bikePrices.get(i).getText();
                String launchDate = bikeLaunchDates.get(i).getText();

                double priceInRupees;
                if (priceText.contains("Lakh")) {
                    priceInRupees =
                            Double.parseDouble(
                                    priceText.replace("Rs.", "")
                                            .replace("Lakh", "")
                                            .trim()
                            ) * 100000;
                } else {
                    priceInRupees =
                            Double.parseDouble(
                                    priceText.replace("Rs.", "")
                                            .replace(",", "")
                                            .trim()
                            );
                }

                if (priceInRupees < 400000) {
                    System.out.println("Bike Number : " + bikeNumber);
                    System.out.println("Bike Name   : " + bikeName);
                    System.out.println("Price       : " + priceText);
                    System.out.println("Launch Date : " + launchDate);
                    System.out.println("--------------------------------");
                    bikeNumber++;
                }
            } catch (Exception e) {
                // skip bikes with missing details
            }
        }
    }


    public void clickChennai() {
        waitForVisibility(chennai);
        scrollIntoView(chennai);
        clickByJS(chennai);
        System.out.println("Chennai selected");
    }

    public void clickHeroMotoCorp() {
        waitForVisibility(heroMotoCorp);
        scrollIntoView(heroMotoCorp);
        clickByJS(heroMotoCorp);
        System.out.println("Hero Moto Corp clicked");
    }

    public void getHeroDealerDetails() {
        int count = Math.min(5, dealerCards.size());
        System.out.println("Displaying First " + count + " Dealers");
        for (int i = 0; i < count; i++) {
            try {
                System.out.println("================================");
                System.out.println("Dealer Number : " + (i + 1));
                System.out.println(dealerCards.get(i).getText());
                System.out.println("================================");
            } catch (Exception e) {
                System.out.println("Unable to fetch Dealer " + (i + 1));
            }
        }
    }
}








