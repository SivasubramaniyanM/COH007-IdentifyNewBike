package org.zigwheels.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonCode;
import utilities.Log;

public class BikesPage extends CommonCode {

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

    @FindBy(xpath = "//a[contains(text(),'Under 70000')]")
    WebElement under70000;

    @FindBy(xpath = "//ul[@id='modelList']//li")
    List<WebElement> bikes;

    @FindBy(id="headerSearch")
    WebElement searchBox;

    @FindBy(xpath="//h1")
    WebElement bikeName;

    @FindBy(xpath="//h1[contains(normalize-space(),'Ola Electric Bikes')]")
    WebElement olaelectric;

    @FindBy(xpath="//div[text()='Ola Electric']")
    WebElement olabike;

    public void clickHondaBrand() {
        waitForVisibility(hondaLink);
        scrollIntoView(hondaLink);
        clickByJS(hondaLink);
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
    private double convertPriceToRupees(String priceText) {
        try {
            if (priceText.contains("Lakh")) {
                return Double.parseDouble(
                        priceText.replace("Rs.", "")
                                .replace("Lakh", "")
                                .replace(",", "")
                                .trim()
                ) * 100000;
            } else if (priceText.contains("Crore")) {
                return Double.parseDouble(
                        priceText.replace("Rs.", "")
                                .replace("Crore", "")
                                .replace(",", "")
                                .trim()
                ) * 10000000;
            } else {
                return Double.parseDouble(
                        priceText.replace("Rs.", "")
                                .replace(",", "")
                                .trim()
                );
            }
        } catch (Exception e) {
            return -1;
        }
    }
    public List<String> getHondaBikeNamesUnder4Lakhs() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < hondaBikes.size(); i++) {
            try {
                String priceText = bikePrices.get(i).getText();
                double priceInRupees = convertPrice(priceText);
                if (priceInRupees > 0 && priceInRupees < 400000) {
                    names.add(bikeNames.get(i).getText());
                }
            } catch (Exception e) {
            }
        }
        return names;
    }
    public List<String> getHondaBikePricesUnder4Lakhs() {
        List<String> prices = new ArrayList<>();
        for (int i = 0; i < hondaBikes.size(); i++) {
            try {
                String priceText = bikePrices.get(i).getText();
                double priceInRupees = convertPrice(priceText);
                if (priceInRupees > 0 && priceInRupees < 400000) {
                    prices.add(priceText);
                }
            } catch (Exception e) {
            }
        }
        return prices;
    }
    public List<String> getHondaBikeDatesUnder4Lakhs() {
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < hondaBikes.size(); i++) {
            try {
                String priceText = bikePrices.get(i).getText();
                double priceInRupees = convertPrice(priceText);
                if (priceInRupees > 0 && priceInRupees < 400000) {
                    dates.add(bikeLaunchDates.get(i).getText());
                }
            } catch (Exception e) {
            }
        }
        return dates;
    }
    private double convertPrice(String priceText) {
        try {
            if (priceText.contains("Lakh")) {
                return Double.parseDouble(
                        priceText.replace("Rs.", "")
                                .replace("Lakh", "")
                                .replace(",", "")
                                .trim()) * 100000;
            } else {
                return Double.parseDouble(
                        priceText.replace("Rs.", "")
                                .replace(",", "")
                                .trim());
            }
        } catch (Exception e) {
            return -1;
        }
    }
    public List<String> getUnrevealedLaunchDateBikes() {
        List<String> unrevealed = new ArrayList<>();
        for (int i = 0; i < hondaBikes.size(); i++) {
            try {
                WebElement card = hondaBikes.get(i);
                String fullText = card.getText().toLowerCase().trim();
                if (fullText.isEmpty()) continue;
                if (fullText.contains("upcoming bikes by brand")) continue;
                String name = "";
                try {
                    name = card.findElement(
                                    By.xpath(".//strong | .//h3 | .//a[contains(@class,'title')]"))
                            .getText().trim();
                } catch (Exception e) {
                    continue;
                }
                String priceText = "";
                try {
                    priceText = card.findElement(
                                    By.xpath(".//*[contains(text(),'Rs.') " +
                                            "or contains(text(),'Price') " +
                                            "or contains(text(),'announced')]"))
                            .getText().trim();
                } catch (Exception e) {
                    continue;
                }
                if (priceText.toLowerCase().contains("announced")
                        || priceText.toLowerCase().contains("tba")) {
                    continue;
                }
                boolean isUnrevealed =
                        fullText.contains("unrevealed")
                                || fullText.contains("not revealed");
                if (!isUnrevealed) continue;
                double priceInRupees = convertPriceToRupees(priceText);
                if (priceInRupees <= 0 || priceInRupees >= 400000) continue;
                unrevealed.add(name + " | " + priceText);
            } catch (Exception ignored) {
            }
        }
        return unrevealed;
    }
    public String getHighestPriceBikeName() {
        String topBikeName = null;
        double topPrice = 0.0;
        for (int i = 0; i < hondaBikes.size(); i++) {
            try {
                String name = bikeNames.get(i).getText().trim();
                String priceTxt = bikePrices.get(i).getText().trim();
                double price = convertPriceToRupees(priceTxt);
                if (price < 0) continue;
                if (price > topPrice) {
                    topPrice = price;
                    topBikeName = name;
                }
            } catch (Exception ignored) {
            }
        }
        return topBikeName;
    }
    public String getLowestPriceBikeName() {
        String lowBikeName = null;
        double lowPrice = Double.MAX_VALUE;
        for (int i = 0; i < hondaBikes.size(); i++) {
            try {
                String name = bikeNames.get(i).getText().trim();
                String priceTxt = bikePrices.get(i).getText().trim();
                double price = convertPriceToRupees(priceTxt);
                if (price < 0) continue;
                if (price < lowPrice) {
                    lowPrice = price;
                    lowBikeName = name;
                }
            } catch (Exception ignored) {
            }
        }
        return lowBikeName;
    }
    public List<String[]> getHondaBikeDetailsForExcel() {
        List<String[]> bikeData = new ArrayList<>();
        for (int i = 0; i < hondaBikes.size(); i++) {
            try {
                String bikeName = bikeNames.get(i).getText().trim();
                String price = "";
                if (i < bikePrices.size()) {
                    price = bikePrices.get(i).getText().trim();
                }
                String launchDate = "";
                if (i < bikeLaunchDates.size()) {
                    launchDate = bikeLaunchDates.get(i).getText().trim();
                }
                bikeData.add(new String[]{bikeName, price, launchDate});
                Log.info("Bike Name : " + bikeName);
                Log.info("Bike Price : " + price);
                Log.info("Launch Date : " + launchDate);
            } catch (Exception e) {
                Log.error("Unable to fetch bike details at index : " + i
                        + " Reason : " + e.getMessage());
            }
        }
        return bikeData;
    }
    public void clickUnder70000() {
        scrollIntoView(under70000);
        waitForVisibility(under70000);
        clickByJS(under70000);
        System.out.println("Under 70000 clicked");
    }
    public int getBikeCount() {;
        return bikes.size();
    }
    public void searchHondaActiva125() {
        waitForVisibility(searchBox);
        searchBox.sendKeys("Honda Activa 125");
        searchBox.sendKeys(Keys.ENTER);
        System.out.println("Honda Activa 125 searched");
    }
    public String getBikeName() {
        waitForVisibility(bikeName);
        return bikeName.getText().trim();
    }
    public  void electricbike(){
        scrollIntoViewdealer(olabike);
        clickByJS(olabike);
    }
    public String olabike() {
        waitForVisibility(olaelectric);
        return olaelectric.getText().trim();
    }
}








