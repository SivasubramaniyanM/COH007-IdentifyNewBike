
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

    // ---------- Page Actions ----------

    /** Click on the Honda brand link */
    public void clickHondaBrand() {
        waitForVisibility(hondaLink);
        scrollIntoView(hondaLink);
        clickByJS(hondaLink);
    }

    /** Print details of all Honda bikes */
    public void getHondaBikeDetails() {
        System.out.println("Total Bikes Found : " + hondaBikes.size());

        for (int i = 0; i < hondaBikes.size(); i++) {
            try {
                String bikeName   = bikeNames.get(i).getText();
                String price      = bikePrices.get(i).getText();
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

    /** Print Honda bikes with price under 4 Lakhs */
    public void getHondaBikesUnder4Lakhs() {
        int bikeNumber = 1;

        for (int i = 0; i < hondaBikes.size(); i++) {
            try {
                String bikeName   = bikeNames.get(i).getText();
                String priceText  = bikePrices.get(i).getText();
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
}

