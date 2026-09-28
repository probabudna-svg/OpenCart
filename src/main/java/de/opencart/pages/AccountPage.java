package de.opencart.pages;

import de.opencart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountPage extends BasePage {

    private By heading = By.cssSelector("#content h2");
    private By orderHistoryLink = By.cssSelector("#content a[href*='route=account/order']");
    private By downloadsLink = By.cssSelector("#content a[href*='route=account/download']");
    private By wishListLink = By.cssSelector("#content a[href*='route=account/wishlist']");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeading() {
        return getWait(10).until(ExpectedConditions.visibilityOfElementLocated(heading)).getText().trim();
    }

    public OrderHistoryPage clickOrderHistory() {
        WebElement link = getWait(10).until(ExpectedConditions.presenceOfElementLocated(orderHistoryLink));
        clickWithJS(link);
        return new OrderHistoryPage(driver);
    }

    public DownloadsPage clickDownloads() {
        WebElement link = getWait(10).until(ExpectedConditions.presenceOfElementLocated(downloadsLink));
        clickWithJS(link);
        return new DownloadsPage(driver);
    }

    public WishListPage clickWishList() {
        WebElement link = getWait(10).until(ExpectedConditions.presenceOfElementLocated(wishListLink));
        clickWithJS(link);
        return new WishListPage(driver);
    }
}
