package org.zigwheels.pages;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonCode;

public class CarPage extends CommonCode {
    public CarPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Your City']")
    WebElement searchBar;

    @FindBy(xpath = "//a[@data-value='Chennai']")
    WebElement chennaiEle;

    @FindBy(xpath = "//div[@class='zw-sr-filterWrap']/ul/li[2]/div[2]/div[4]")
    WebElement popularModelsHeader;

    @FindBy(xpath = "//div[@class='gsc_thin_scroll']//li")
    List<WebElement> popularModels;

    @FindBy(xpath = "//div/a[@data-track-label='Car-name']")
    List<WebElement> carNames;

    @FindBy(xpath = "//div[@class='gsc_thin_scroll']//input[@type='checkbox']")
    List<WebElement> popularModelCheckboxes;

    @FindBy(xpath = "//span[contains(text(),'Lakh')]")
    List<WebElement> carPrices;

    @FindBy(xpath = "//span[contains(@class,'pull-left zm-cmn-colorBlack')]")
    WebElement filtersSection;

    public void SearchCity(String city) {
        searchBar.sendKeys(city);
    }

    public void clickChennai() {
        chennaiEle.click();
    }

    public void goToPopularModels() {
        scrollIntoView(popularModelsHeader);
        waitForVisibility(popularModelsHeader);
    }

    public boolean isPopularModelsDisplayed() {
        return popularModelsHeader.isDisplayed();
    }

    public List<String> getPopularModelNames() {
        List<String> modelNames = new ArrayList<>();
        for (WebElement model : popularModels) {
            String modelName = model.getText().trim();
            if (!modelName.isEmpty()) {
                modelNames.add(modelName);
            }
        }
        return modelNames;
    }

    public void clickThirdPopularModel() {
        if (popularModels.size() >= 3) {
            scrollIntoView(popularModels.get(2));
            clickByJS(popularModels.get(2));
        }
    }

    public List<String> getCarNames() {
        List<String> names = new ArrayList<>();
        for(WebElement car : carNames) {
            String name = car.getText().trim();
            if(!name.isEmpty()) {
                names.add(name);
            }
        }
        return names;
    }

    public void selectAllPopularModels() {
        for(WebElement checkbox : popularModelCheckboxes) {
            if(!checkbox.isSelected()) {
                clickByJS(checkbox);
            }
        }
    }

    public List<String> getCarPrices() {
        List<String> prices = new ArrayList<>();
        for(WebElement price : carPrices) {
            prices.add(price.getText().trim());
        }
        return prices;
    }

    public boolean isFiltersDisplayed() {
        return filtersSection.isDisplayed();
    }
}