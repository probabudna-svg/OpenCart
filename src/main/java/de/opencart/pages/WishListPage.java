package de.opencart.pages;

import de.opencart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class WishListPage extends BasePage {

    private By heading = By.cssSelector("#content h2");
    private By wishListTable = By.cssSelector(".table-responsive table");
    private By productNames = By.cssSelector(".table-responsive table tbody tr td.text-left a");
    private By removeButtons = By.cssSelector("a.btn-danger[href*='remove']");
    private By emptyMessage = By.cssSelector("#content p");
    private By continueButton = By.cssSelector(".buttons .btn-primary");

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeading() {
        return getWait(10).until(ExpectedConditions.visibilityOfElementLocated(heading)).getText().trim();
    }

    public boolean isWishListTableDisplayed() {
        List<WebElement> tables = driver.findElements(wishListTable);
        return !tables.isEmpty() && tables.get(0).isDisplayed();
    }

    public List<String> getWishListProductNames() {
        List<WebElement> elements = driver.findElements(productNames);
        List<String> names = new ArrayList<>();
        for (WebElement el : elements) {
            names.add(el.getText().trim());
        }
        return names;
    }

    public WishListPage removeFirstProduct() {
        List<WebElement> removeBtns = driver.findElements(removeButtons);
        if (!removeBtns.isEmpty()) {
            click(removeBtns.get(0));
            getWait(5).until(ExpectedConditions.stalenessOf(removeBtns.get(0)));
        }
        return this;
    }

    public String getEmptyMessage() {
        return driver.findElement(emptyMessage).getText().trim();
    }

    public void clickContinue() {
        click(driver.findElement(continueButton));
    }
}
