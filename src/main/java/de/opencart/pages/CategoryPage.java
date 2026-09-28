package de.opencart.pages;

import de.opencart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CategoryPage extends BasePage {

    private By listViewButton = By.id("list-view");
    private By gridViewButton = By.id("grid-view");
    private By sortSelect = By.id("input-sort");
    private By productLayouts = By.cssSelector("#content .product-layout");
    private By productTitles = By.cssSelector("#content .product-thumb h4 a");
    private By compareButtons = By.cssSelector("button[onclick*='compare.add']");
    private By wishlistButtons = By.cssSelector("button[onclick*='wishlist.add']");
    private By compareTotalLink = By.id("compare-total");
    private By wishlistTotalLink = By.id("wishlist-total");
    private By alertSuccess = By.cssSelector(".alert.alert-success");

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public CategoryPage open(String url) {
        driver.get(url);
        return this;
    }

    public CategoryPage clickListView() {
        click(driver.findElement(listViewButton));
        getWait(5).until(ExpectedConditions.attributeContains(listViewButton, "class", "active"));
        return this;
    }

    public CategoryPage clickGridView() {
        click(driver.findElement(gridViewButton));
        getWait(5).until(ExpectedConditions.attributeContains(gridViewButton, "class", "active"));
        return this;
    }

    public boolean isListViewActive() {
        WebElement btn = driver.findElement(listViewButton);
        return btn.getAttribute("class").contains("active");
    }

    public boolean isGridViewActive() {
        WebElement btn = driver.findElement(gridViewButton);
        return btn.getAttribute("class").contains("active");
    }

    public boolean allProductsHaveClass(String className) {
        List<WebElement> products = driver.findElements(productLayouts);
        if (products.isEmpty()) {
            return false;
        }
        for (WebElement product : products) {
            String productClass = product.getAttribute("class");
            if (productClass == null || !productClass.contains(className)) {
                return false;
            }
        }
        return true;
    }

    public CategoryPage selectSortBy(String sortText) {
        WebElement oldSelect = driver.findElement(sortSelect);
        new Select(oldSelect).selectByVisibleText(sortText);
        getWait(10).until(ExpectedConditions.stalenessOf(oldSelect));
        getWait(10).until(ExpectedConditions.visibilityOfElementLocated(sortSelect));
        return this;
    }

    public String getSelectedSortOption() {
        WebElement selectElem = driver.findElement(sortSelect);
        return new Select(selectElem).getFirstSelectedOption().getText().trim();
    }

    public String getProductTitle(int index) {
        List<WebElement> titles = driver.findElements(productTitles);
        return titles.get(index).getText().trim();
    }

    public CategoryPage addProductToCompare(int index) {
        String previousTotal = driver.findElement(compareTotalLink).getText();
        List<WebElement> buttons = driver.findElements(compareButtons);
        clickWithJS(buttons.get(index));
        getWait(5).until(ExpectedConditions.visibilityOfElementLocated(alertSuccess));
        getWait(5).until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElementLocated(compareTotalLink, previousTotal)
        ));
        return this;
    }

    public CategoryPage addProductToWishList(int index) {
        List<WebElement> buttons = driver.findElements(wishlistButtons);
        clickWithJS(buttons.get(index));
        getWait(5).until(ExpectedConditions.visibilityOfElementLocated(alertSuccess));
        return this;
    }

    public String getAlertText() {
        WebElement alert = getWait(5).until(ExpectedConditions.visibilityOfElementLocated(alertSuccess));
        return alert.getText();
    }

    public ProductComparePage clickCompareTotal() {
        WebElement link = driver.findElement(compareTotalLink);
        clickWithJS(link);
        return new ProductComparePage(driver);
    }

    public WishListPage clickWishListHeader() {
        WebElement link = driver.findElement(wishlistTotalLink);
        clickWithJS(link);
        return new WishListPage(driver);
    }
}
