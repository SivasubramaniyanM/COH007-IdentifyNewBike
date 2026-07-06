package org.zigwheels.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utilities.CommonCode;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class HomePage extends CommonCode {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(.,'NEW BIKES')]")
    WebElement newBikes;

    @FindBy(linkText = "Upcoming Bikes")
    WebElement upcomingBikes;

    @FindBy(xpath = "//ul[contains(@class,'h-d-nav fnt')]/li[5]/span[contains(text(),'MORE')]")
    WebElement moreMenu;

    @FindBy(xpath = "//a[@title='Used Cars']")
    WebElement usedCars;

    @FindBy(xpath = "//a[@title='Used Cars']")
    WebElement usedCarsLink;

    @FindBy(xpath = "//div[@id='des_lIcon']")
    WebElement login;

    @FindBy(id = "des_lIcon")
    WebElement loginIcon;

    @FindBy(xpath = "/html/body/footer/div[2]/div/ul[2]/li[3]/span")
    WebElement feedbackBtn;

    @FindBy(id = "feedback_comment")
    WebElement feedbackComment;

    @FindBy(id = "feedback_submit")
    WebElement submitBtn;

    @FindBy(xpath = "(//*[@id='feedback_form']//label/span)[3]")
    WebElement excellentEmoji;

    @FindBy(linkText = "Bike Dealers")
    WebElement bikeDealers;

    @FindBy(linkText = "Latest Bikes")
    WebElement latestBikes;

    @FindBy(xpath = "//div[@id='qns-tctrc']//h3")
    WebElement topContributorsSection;

    @FindBy(xpath = "//*[@id='topLikeList']/li/div[2]/div[1]")
    List<WebElement> contributorNames;

    @FindBy(xpath = "//*[@id='latest_user_review']/div/h2")
    WebElement latestUserReviewsSection;

    @FindBy(xpath = "//*[@id='latest_user_review']//li")
    List<WebElement> userReviews;

    @FindBy(xpath = "//nav[@class='headerNav']/ul/li[2]/span")
    WebElement newCars;

    @FindBy(xpath="//a[text()='Car Comparisons']")
    WebElement carComparisons;

    public void clickLatestBikes() {
        waitForVisibility(latestBikes);
        clickElement(latestBikes);
        System.out.println("Latest Bikes clicked");
    }
    public void clickNewBikes() {
        clickElement(newBikes);
        System.out.println("NEW BIKES clicked");
    }
    public void clickUpcmngBikes() {
        waitForVisibility(upcomingBikes);
        clickElement(upcomingBikes);
        System.out.println("Upcoming Bikes clicked");
    }
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
}