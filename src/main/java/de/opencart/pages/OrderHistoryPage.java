package de.opencart.pages;

import de.opencart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OrderHistoryPage extends BasePage {

    private By heading = By.cssSelector("#content h1, #content h2");
    private By ordersTable = By.cssSelector(".table-responsive table");
    private By viewButtons = By.cssSelector("a.btn-info[href*='route=account/order/info']");
    private By orderDetailsHeading = By.xpath("//td[contains(text(),'Order Details')]");
    private By continueButton = By.cssSelector(".buttons a.btn-primary");
    private By downloadsSidebarLink = By.cssSelector("aside#column-right a[href*='route=account/download']");

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeading() {
        return getWait(10).until(ExpectedConditions.visibilityOfElementLocated(heading)).getText().trim();
    }

    public boolean isOrdersTablePresent() {
        List<WebElement> tables = driver.findElements(ordersTable);
        return !tables.isEmpty() && tables.get(0).isDisplayed();
    }

    public OrderHistoryPage clickViewOrder() {
        List<WebElement> buttons = driver.findElements(viewButtons);
        if (!buttons.isEmpty()) {
            click(buttons.get(0));
            getWait(10).until(ExpectedConditions.visibilityOfElementLocated(orderDetailsHeading));
        }
        return this;
    }

    public boolean isOrderDetailsDisplayed() {
        List<WebElement> elements = driver.findElements(orderDetailsHeading);
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

    public DownloadsPage clickDownloadsInSidebar() {
        WebElement link = getWait(10).until(ExpectedConditions.presenceOfElementLocated(downloadsSidebarLink));
        clickWithJS(link);
        return new DownloadsPage(driver);
    }

    public AccountPage clickContinue() {
        WebElement btn = getWait(10).until(ExpectedConditions.presenceOfElementLocated(continueButton));
        clickWithJS(btn);
        return new AccountPage(driver);
    }
}
