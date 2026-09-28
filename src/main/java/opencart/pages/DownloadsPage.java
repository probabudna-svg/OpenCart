package opencart.pages;

import opencart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DownloadsPage extends BasePage {

    private By heading = By.cssSelector("#content h1, #content h2");
    private By contentParagraph = By.cssSelector("#content p");
    private By continueButton = By.cssSelector(".buttons a.btn-primary");
    private By downloadsTable = By.cssSelector(".table-responsive table");

    public DownloadsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeading() {
        return getWait(10)
                .until(ExpectedConditions.visibilityOfElementLocated(heading))
                .getText()
                .trim();
    }

    public boolean isDownloadsTablePresent() {
        List<WebElement> tables = driver.findElements(downloadsTable);
        return !tables.isEmpty() && tables.get(0).isDisplayed();
    }

    public String getContentMessage() {
        List<WebElement> paragraphs = driver.findElements(contentParagraph);
        return paragraphs.isEmpty() ? "" : paragraphs.get(0).getText().trim();
    }

    public AccountPage clickContinue() {
        WebElement btn = getWait(10)
                .until(ExpectedConditions.presenceOfElementLocated(continueButton));

        clickWithJS(btn);

        return new AccountPage(driver);
    }
}