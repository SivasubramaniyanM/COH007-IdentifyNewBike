package org.zigwheels.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CommonCode;
import utilities.Log;

public class CarPage extends CommonCode {

    public CarPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[contains(@placeholder,'Enter Your City')]")
    WebElement searchBar;

    @FindBy(xpath = "//a[@data-value='Chennai']")
    WebElement chennaiEle;

    @FindBy(xpath="(//a[contains(@title,'Compare')])[1]")
    WebElement addCar1;

    @FindBy(xpath="//a[contains(@id,'editCar')]")
    WebElement pencilIcon;

    @FindBy(xpath="//input[@placeholder='Search Brand/Model']")
    WebElement brandOrModel;

    @FindBy(xpath="//input[@placeholder='Search Variant']")
    WebElement variant;

    @FindBy(xpath = "//tr[td[contains(text(),'Ex-Showroom Price')]]/td[2]")
    WebElement car1Price;

    @FindBy(xpath = "//tr[td[contains(text(),'Ex-Showroom Price')]]/td[3]")
    WebElement car2Price;

    @FindBy(xpath = "(//tr[td[contains(text(),'Engine Displacement')]]/td[2])[1]")
    WebElement car1CC;

    @FindBy(xpath = "(//tr[td[contains(text(),'Engine Displacement')]]/td[3])[1]")
    WebElement car2CC;

    @FindBy(xpath = "//h2[contains(text(),'Key Highlights')]")
    WebElement comparisonHeading;

    @FindBy(xpath="//span[@title='View Offer Details'][1]")
    WebElement offerDetails;

    @FindBy(xpath="//button[@name='submitBtn']//span[normalize-space()='Continue to View Offers']")
    WebElement CheckOffer;

    @FindBy(xpath="//input[@id='popupcityName']")
    WebElement CityName;

    @FindBy(xpath="//input[@id='locality']")
    WebElement pincode;

    @FindBy(xpath="//span[@title='PPS Motors Pvt. Ltd.-Rajajinagar']")
    WebElement agency;

    @FindBy(xpath="//input[@type='tel' and @maxlength='15']")
    WebElement mobNo;

    @FindBy(xpath="//span[text()='Please enter valid mobile number']")
    WebElement InvalidText;

    @FindBy(xpath = "//a[contains(text(),'Under 10 Lakh')]")
    WebElement under10Lakh;

    @FindBy(xpath = "//h1[contains(text(),'Electric Cars Under 10 Lakhs')]")
    WebElement under10LakhHeading;

    public void SearchCity(String city) {
        enterText(searchBar, city);
    }
    public void clickChennai() {
        waitForClickable(chennaiEle);
        scrollIntoView(chennaiEle);
        clickByJS(chennaiEle);
        wait.until(d -> {
            String url = d.getCurrentUrl();
            return url != null && url.toLowerCase().contains("chennai");
        });
    }
    public void goToPopularModels() {
        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        By popularBy = By.xpath("//div[contains(@class,'popularCardBrand')]");
        localWait.until(ExpectedConditions.presenceOfElementLocated(popularBy));
        // Retry loop with stale ignored via WebDriverWait
        for (int i = 0; i < 3; i++) {
            try {
                WebElement section = driver.findElement(popularBy);
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center'});", section);
                // Confirm still present after scroll
                localWait.until(ExpectedConditions.visibilityOfElementLocated(popularBy));
                return;
            } catch (StaleElementReferenceException e) {
                Log.info("Stale on goToPopularModels, retry " + (i + 1));
                // Wait for DOM to settle using WebDriverWait (no Thread.sleep)
                new WebDriverWait(driver, Duration.ofSeconds(2))
                        .ignoring(StaleElementReferenceException.class)
                        .until(ExpectedConditions.presenceOfElementLocated(popularBy));
            }
        }
        throw new RuntimeException("Could not scroll to Popular Models section after 3 retries");
    }
    public boolean isPopularModelsDisplayed() {
        try {
            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            By popularBy = By.xpath("//div[contains(@class,'popularCardBrand')]");
            // Re-locate freshly + retry to handle stale
            for (int i = 0; i < 3; i++) {
                try {
                    WebElement section = localWait.until(
                            ExpectedConditions.visibilityOfElementLocated(popularBy));
                    return section.isDisplayed();
                } catch (StaleElementReferenceException e) {
                    Log.info("Stale on isPopularModelsDisplayed, retry " + (i + 1));
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    public List<String> getPopularModelNames() {
        // Re-locate freshly to avoid stale
        By popularLinksBy =
                By.xpath("//div[contains(@class,'popularCardBrand')]//ul/li//a");
        return safeGetTextsByLocator(popularLinksBy);
    }
    public void clickThirdPopularModel() {
        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        By popularLinksBy =
                By.xpath("//div[contains(@class,'popularCardBrand')]//ul/li//a");
        // Re-locate freshly each time
        List<WebElement> links = localWait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(popularLinksBy));
        if (links.size() >= 3) {
            for (int i = 0; i < 3; i++) {
                try {
                    List<WebElement> refreshed = driver.findElements(popularLinksBy);
                    scrollIntoView(refreshed.get(2));
                    clickByJS(refreshed.get(2));
                    return;
                } catch (StaleElementReferenceException e) {
                    Log.info("Stale on clickThirdPopularModel, retry " + (i + 1));
                }
            }
        }
    }
    public void waitForCarsToLoad() {
        By carNamesBy = By.xpath("//a[@data-track-label='Car-name']");
        By carPricesBy = By.xpath("//div[contains(@class,'zw-sr-searchTarget')]//*[contains(text(),'Lakh')]");
        wait.until(d -> !d.findElements(carNamesBy).isEmpty());
        wait.until(d -> !d.findElements(carPricesBy).isEmpty());
        // Wait until car count is stable across two polls
        wait.until(d -> {
            int a = d.findElements(carNamesBy).size();
            int b = d.findElements(carNamesBy).size();
            return a > 0 && a == b;
        });
    }
    public List<String> getCarNames() {
        By carNamesBy = By.xpath("//a[@data-track-label='Car-name']");
        return safeGetTextsByLocator(carNamesBy);
    }
    public List<String> getCarPrices() {
        By carPricesBy = By.xpath("//div[contains(@class,'zw-sr-searchTarget')]//*[contains(text(),'Lakh')]");
        return safeGetTextsByLocator(carPricesBy);
    }
    // Re-fetches elements from DOM on each attempt (stale-proof)
    public List<String> safeGetTextsByLocator(By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                List<String> texts = new ArrayList<>();
                List<WebElement> elements = driver.findElements(locator);
                for (WebElement el : elements) {
                    String txt = el.getText();
                    if (txt != null && !txt.trim().isEmpty()) {
                        texts.add(txt.trim());
                    }
                }
                return texts;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
        return new ArrayList<>();
    }
    public void clickAddOrEditCar1() {
        try {
            clickElement(addCar1);
        } catch(Exception e) {
            clickElement(pencilIcon);
        }
    }
    public void clickAddOrEditCar2() {
        try {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'Add Car 2')]")
                    )
            ).click();
        } catch(Exception e) {
            clickElement(pencilIcon);
        }
    }
    public void selectCar(String brandOrModelName,
                          String variantName) {
        waitForVisibility(brandOrModel);
        brandOrModel.click();
        brandOrModel.clear();
        brandOrModel.sendKeys(brandOrModelName);
        WebElement carOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + brandOrModelName + "')]")));
        carOption.click();
        waitForVisibility(variant);
        variant.click();
        variant.clear();
        variant.sendKeys(variantName);
        WebElement variantOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + variantName + "')]")));
        variantOption.click();
    }
    public void clickCompareCars() {
        WebElement compareButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='col-sm-12 txt-c clr']/a")));
        js.executeScript("arguments[0].click();", compareButton);
    }
    public String getComparisonHeading() {
        return comparisonHeading.getText();
    }
    public void displayPriceAndCCComparison() {
        scrollIntoView(car1Price);
        Log.info("===== PRICE COMPARISON =====");
        Log.info(String.format("Car 1 Price : %s", car1Price.getText()));
        Log.info(String.format("Car 2 Price : %s", car2Price.getText()));
        Log.info("===== ENGINE DISPLACEMENT =====");
        Log.info(String.format("Car 1 CC : %s", car1CC.getText()));
        Log.info(String.format("Car 2 CC : %s", car2CC.getText()));
    }

    public void clickUnder10Lakh() {
        scrollIntoView(under10Lakh);
        waitForVisibility(under10Lakh);
        clickByJS(under10Lakh);
    }
    public String getUnder10LakhHeading() {
        waitForVisibility(under10LakhHeading);
        return under10LakhHeading.getText().trim();
    }
    public List<String[]> getCarDetailsForExcel() {
        List<String[]> carData = new ArrayList<>();
        List<String> names = getCarNames();
        List<String> prices = getCarPrices();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i).trim();
            String price = "";
            if (i < prices.size()) {
                price = prices.get(i).trim();
            }
            carData.add(new String[]{
                    name,
                    price
            });
            Log.info("Car Name : " + name);
            Log.info("Car Price : " + price);
        }
        return carData;
    }
    public double parsePriceInLakh(String priceText) {
        try {
            String cleaned = priceText
                    .replaceAll("(?i)Rs\\.?", "")
                    .replaceAll("(?i)Lakh", "")
                    .replaceAll("[₹,]", "")
                    .trim();
            return Double.parseDouble(cleaned.split("\\s+")[0]);
        } catch (Exception e) {
            return -1;
        }
    }
    public String[] getCheapestAndCostliestCar(List<String> names, List<String> prices) {
        double minPrice = Double.MAX_VALUE;
        double maxPrice = Double.MIN_VALUE;
        String cheapestCar = "";
        String costliestCar = "";
        int count = Math.min(names.size(), prices.size());
        for (int i = 0; i < count; i++) {
            double price = parsePriceInLakh(prices.get(i));
            if (price <= 0) {
                System.out.println("Unable to parse price : " + prices.get(i));
                continue;
            }
            if (price < minPrice) {
                minPrice = price;
                cheapestCar = names.get(i);
            }
            if (price > maxPrice) {
                maxPrice = price;
                costliestCar = names.get(i);
            }
        }
        return new String[] {
                cheapestCar,
                String.valueOf(minPrice),
                costliestCar,
                String.valueOf(maxPrice)
        };
    }
    public void clickOfferDetail(){
        super.clickByJS(offerDetails);

    }
    public boolean isContinueButtonDisplayed() {
        waitForVisibility(CheckOffer);
        return CheckOffer.isDisplayed();
    }
    public void verifyOfferButtonVisible() {
        waitForVisibility(CheckOffer);
    }
    public void SelectYourCity(String city){
        CityName.sendKeys(city + Keys.ENTER);
    }
    public void SelectPinCode(String pin){
        pincode.sendKeys(pin + Keys.ENTER);
    }
    public void EnterMobNo(String mobileNo) {
        mobNo.sendKeys(mobileNo);
    }
    public void SelectAgency(){
        super.clickByJS(agency);
    }
    public void ClickOffer(){
        super.clickByJS(CheckOffer);
    }
    public String GetText(){
        return InvalidText.getText();
    }
}
