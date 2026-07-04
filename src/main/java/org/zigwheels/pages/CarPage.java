package org.zigwheels.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonCode;

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
        waitForVisibility(popularModelsHeader);
        scrollIntoView(popularModelsHeader);
    }

    public boolean isPopularModelsDisplayed() {
        try {
            waitForVisibility(popularModelsHeader);
            return popularModelsHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getPopularModelNames() {
        return safeGetTexts(popularModelLinks);
    }

    public void clickThirdPopularModel() {
        if (popularModelLinks.size() >= 3) {
            scrollIntoView(popularModelLinks.get(2));
            clickByJS(popularModelLinks.get(2));
        }
    }

    public void selectAllPopularModels() {
        for (WebElement brand : popularModelLinks) {
            scrollIntoView(brand);
            clickByJS(brand);
        }
    }

    public void waitForCarsToLoad() {
        wait.until(d -> !carNames.isEmpty());
        wait.until(d -> !carPrices.isEmpty());

        // Wait until car count is stable across two polls
        wait.until(d -> {
            int a = carNames.size();
            int b = carNames.size();
            return a > 0 && a == b;
        });
    }

    public List<String> getCarNames() {
        return safeGetTexts(carNames);
    }

    public List<String> getCarPrices() {
        return safeGetTexts(carPrices);
    }

    // ---------- Helper: retry-on-stale text extraction ----------

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
}