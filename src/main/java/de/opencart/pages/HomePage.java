package de.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private final WebDriver driver;

    private final By searchInput = By.cssSelector("#search input[name='search']");
    private final By searchButton = By.cssSelector("#search button");

    public HomePage(WebDriver driver) {
        this.driver = driver;
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
}
