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

    public void clickNewBikes() {
        clickElement(newBikes);
        System.out.println("NEW BIKES clicked");
    }

    public void clickUpcmngBikes() {
        waitForVisibility(upcomingBikes);
        clickElement(upcomingBikes);
        System.out.println("Upcoming Bikes clicked");
    }
<<<<<<< Updated upstream
=======
    public void clickBikeDealers() {
        waitForVisibility(bikeDealers);
        clickElement(bikeDealers);
        System.out.println("Bike Dealers clicked");
    }
    public void clickMore() {
        moreMenu.click();
    }
    public void clickUsedCars() {
        waitForVisibility(usedCars);
        clickByJS(usedCars);
    }
    public void hoverMore() {
        waitForVisibility(moreMenu);
        scrollIntoView(moreMenu);
        new Actions(driver).moveToElement(moreMenu).perform();
    }
    public void hoverAndClickUsedCars() {
        new Actions(driver)
                .moveToElement(moreMenu)
                .pause(Duration.ofMillis(500))
                .moveToElement(usedCarsLink)
                .click()
                .perform();

        wait.until(d -> {
            String url = d.getCurrentUrl();
            return url != null && url.toLowerCase().contains("used-car");
        });
    }
    public void clickLogin() {
        super.clickByJS(login);
    }
    public void scrollToFeedback() {
        scrollToBottom();
    }
    public void clickFeedback() {
        clickByJS(feedbackBtn);
    }
    public void enterFeedback(String feedback) {
        enterText(feedbackComment, feedback);
    }
    public void enterText(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }
    public void clickSubmit() {
        clickElement(submitBtn);
    }
    public void clickExcellentEmoji() {
        clickElement(excellentEmoji);
    }
    public boolean validateFeedbackTextBox() {
        return feedbackComment.isDisplayed();
    }
    public boolean validateSubmitButton() {
        return submitBtn.isDisplayed();
    }
    public boolean validateExcellentEmoji() {
        return excellentEmoji.isDisplayed();
    }
    public void scrollToTopContributors() {
        scrollIntoView(topContributorsSection);
    }
    public List<String> getTopContributorNames() {
        waitForVisibility(topContributorsSection);
        List<String> contributors = new ArrayList<>();
        for (WebElement contributor : contributorNames) {
            String name = contributor.getText().trim();
            if (!name.isEmpty()) {
                contributors.add(name);
            }
        }
        return contributors;
    }
    public void scrollToLatestUserReviews() {
        scrollIntoView(latestUserReviewsSection);
    }
    public List<String> getFirstThreeReviews() {
        waitForVisibility(latestUserReviewsSection);
        List<String> reviews = new ArrayList<>();
        int reviewCount = Math.min(3, userReviews.size());
        for (int i = 0; i < reviewCount; i++) {
            String review = userReviews.get(i).getText().trim();
            if (!review.isEmpty()) {
                reviews.add(review);
            }
        }
        return reviews;
    }
    public void clickNewCars() {
        clickElement(newCars);
    }
    public void clickCarComparisons() {
        clickElement(carComparisons);
    }

    @FindBy(linkText = "Electric Cars")
    WebElement electricCars;

    public void clickElectricCars() {
        waitForVisibility(electricCars);
        clickElement(electricCars);
    }



>>>>>>> Stashed changes
}