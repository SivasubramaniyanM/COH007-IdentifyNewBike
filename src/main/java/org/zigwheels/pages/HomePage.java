package org.zigwheels.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.CommonCode;

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

    @FindBy(xpath = "//div[@id='des_lIcon']")
    WebElement login;

    @FindBy(id = "des_lIcon")
    WebElement loginIcon;


    // Feedback
    @FindBy(xpath = "/html/body/footer/div[2]/div/ul[2]/li[3]/span")
    WebElement feedbackBtn;

    // Feedback Textbox
    @FindBy(id = "feedback_comment")
    WebElement feedbackComment;

    // Submit Button
    @FindBy(id = "feedback_submit")
    WebElement submitBtn;


    // Excellent Emoji
    @FindBy(xpath = "(//*[@id='feedback_form']//label/span)[3]")
    WebElement excellentEmoji;

    public void clickNewBikes() {
        clickElement(newBikes);
        System.out.println("NEW BIKES clicked");
    }

    public void clickUpcmngBikes() {
        waitForVisibility(upcomingBikes);
        clickElement(upcomingBikes);
        System.out.println("Upcoming Bikes clicked");
    }

    public void clickMore() { moreMenu.click();
    }

    public void clickUsedCars() {
        waitForVisibility(usedCars);
        clickByJS(usedCars);
    }

    public void clickLogin(){
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

// ------------------ Emoji Actions ------------------

    public void clickExcellentEmoji() {
        clickElement(excellentEmoji);
    }

// ------------------ Validations ------------------

    public boolean validateFeedbackTextBox() {
        return feedbackComment.isDisplayed();
    }

    public boolean validateSubmitButton() {
        return submitBtn.isDisplayed();
    }

    public boolean validateExcellentEmoji() {
        return excellentEmoji.isDisplayed();
    }
    @FindBy(linkText = "Bike Dealers")
    WebElement bikeDealers;

    public void clickBikeDealers() {
        waitForVisibility(bikeDealers);
        clickElement(bikeDealers);
        System.out.println("Bike Dealers clicked");
    }

}