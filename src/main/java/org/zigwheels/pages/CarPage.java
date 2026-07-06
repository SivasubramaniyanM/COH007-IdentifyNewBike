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

<<<<<<< Updated upstream
    @FindBy(xpath = "//span[contains(@class,'pull-left zm-cmn-colorBlack')]")
    WebElement filtersSection;
=======
    @FindBy(xpath="(//a[contains(@title,'Compare')])[1]")
    WebElement addCar1;
    @FindBy(xpath="(//a[contains(@title,'Compare')])[2]")
    WebElement addCar2;

    @FindBy(xpath="//a[contains(@id,'editCar')]")
    WebElement pencilIcon;

    @FindBy(xpath="//input[@placeholder='Search Brand/Model']")
    WebElement brandOrModel;

    @FindBy(xpath="//input[@placeholder='Search Variant']")
    WebElement variant;

    @FindBy(xpath="//div[@class='col-sm-12 txt-c clr']/a")
    WebElement compareCarsBtn;

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


    // ---------- Actions ----------
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
}
=======
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
        WebElement carOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[contains(text(),'"
                                + brandOrModelName + "')]")
                )
        );
        carOption.click();
        waitForVisibility(variant);
        variant.click();
        variant.clear();
        variant.sendKeys(variantName);
        WebElement variantOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[contains(text(),'"
                                + variantName + "')]")
                )
        );
        variantOption.click();
    }

    public void clickCompareCars() {
        WebElement compareButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@class='col-sm-12 txt-c clr']/a")
                )
        );
        js.executeScript("arguments[0].click();", compareButton);
    }

    public String getComparisonHeading() {
        return comparisonHeading.getText();
    }

    public void displayPriceAndCCComparison() {
        scrollIntoView(car1Price);
        System.out.println("===== PRICE COMPARISON =====");
        System.out.println(
                "Car 1 Price : " + car1Price.getText()
        );
        System.out.println(
                "Car 2 Price : " + car2Price.getText()
        );
        System.out.println("\n===== ENGINE DISPLACEMENT =====");
        System.out.println(
                "Car 1 CC : " + car1CC.getText()
        );
        System.out.println(
                "Car 2 CC : " + car2CC.getText()
        );
    }
}
>>>>>>> Stashed changes
