package opencart.pages;

import opencart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private final By searchInput = By.cssSelector("#search input[name='search']");
    private final By searchButton = By.cssSelector("#search button");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public SearchResultsPage searchFor(String productName) {
        WebElement input = driver.findElement(searchInput);
        input.clear();
        input.sendKeys(productName);
        driver.findElement(searchButton).click();
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage openSearchPage() {
        WebElement input = driver.findElement(searchInput);
        input.clear();
        driver.findElement(searchButton).click();
        return new SearchResultsPage(driver);
    }

    public CategoryPage openCategory(String categoryName) {
        WebElement category = driver.findElement(By.xpath(
                "//nav[@id='menu']//a[normalize-space()='" + categoryName + "']"
        ));

        if (category.getAttribute("class").contains("dropdown-toggle")) {
            category.click();
            driver.findElement(By.xpath(
                    "//nav[@id='menu']//a[contains(@class,'see-all') " +
                            "and normalize-space()='Show All " + categoryName + "']"
            )).click();
        } else {
            category.click();
        }

        return new CategoryPage(driver);
    }

    public HomePage openMyAccount() {
        clickWithJS(driver.findElement(
                By.cssSelector("ul[class='list-inline'] li[class='dropdown']")));
        return this;
    }

    public RegisterPage clickOnRegister() {
        clickWithJS(driver.findElement(
                By.cssSelector("a[href*=\"route=account/register\"]")));
        return new RegisterPage(driver);
    }

    public HomePage clickDesktops() {
        clickWithJS(driver.findElement(
                By.xpath("//a[@class='dropdown-toggle'][normalize-space()='Desktops']")));
        return this;
    }

    public HomePage clickMac() {
        clickWithJS(driver.findElement(
                By.xpath("//ul[@class='list-unstyled']//a[contains(text(),'Mac (1)')]")));
        return this;
    }

    public HomePage addCart() {
        clickWithJS(driver.findElement(
                By.cssSelector("div[class='button-group'] i[class='fa fa-shopping-cart']")));
        return this;
    }

    public HomePage clickCart() {
        clickWithJS(driver.findElement(
                By.cssSelector(".btn.btn-inverse.btn-block.btn-lg.dropdown-toggle")));
        return this;
    }

    public CartPage viewCart() {
        By locator = By.cssSelector(".btn.btn-inverse.btn-block.btn-lg.dropdown-toggle");
        getWait(10).until(ExpectedConditions.elementToBeClickable(locator));
        clickWithJS(driver.findElement(locator));
        return new CartPage(driver);
    }
}