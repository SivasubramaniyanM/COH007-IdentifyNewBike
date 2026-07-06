package utilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class CommonCode {
    public WebDriver driver;
<<<<<<< Updated upstream
    WebDriverWait wait;
    JavascriptExecutor js;
=======
    public WebDriverWait wait;
    public JavascriptExecutor js;
>>>>>>> Stashed changes

    public CommonCode(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        js = (JavascriptExecutor) driver;
    }

    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickByJS(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].click();", element);
    }

    public void scrollIntoView(WebElement element) {
        js.executeScript(
                "arguments[0].scrollIntoView(true);",
                element
        );
    }

    public void clickElement(WebElement element) {
        waitForClickable(element);
        Actions act = new Actions(driver);
        act.moveToElement(element).click().perform();
    }
}