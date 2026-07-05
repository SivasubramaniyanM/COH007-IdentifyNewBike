package org.zigwheels.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.CommonCode;
import utilities.Log;

public class CarPage extends CommonCode {

    public CarPage(WebDriver driver) {
        super(driver);
    }

    // ---------- @FindBy elements ----------

    @FindBy(xpath = "//input[contains(@placeholder,'Enter Your City')]")
    WebElement searchBar;

    @FindBy(xpath = "//a[@data-value='Chennai']")
    WebElement chennaiEle;

    @FindBy(xpath = "//div[contains(@class,'popularCardBrand')]")
    WebElement popularModelsHeader;

    @FindBy(xpath = "//div[contains(@class,'popularCardBrand')]//ul/li//a")
    List<WebElement> popularModelLinks;

    @FindBy(xpath = "//a[@data-track-label='Car-name']")
    List<WebElement> carNames;

    @FindBy(xpath = "//div[contains(@class,'zw-sr-searchTarget')]//*[contains(text(),'Lakh')]")
    List<WebElement> carPrices;

    // ---------- Actions ----------

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

        // Wait for presence first
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

    // ---------- Helpers: retry-on-stale text extraction ----------

    public List<String> safeGetTexts(List<WebElement> elements) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                List<String> texts = new ArrayList<>();
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
}
