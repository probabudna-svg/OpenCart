package de.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SearchResultsPage {

    private final WebDriver driver;

    private final By productNames = By.cssSelector(".product-layout h4 a");
    private final By searchCriteriaInput = By.id("input-search");
    private final By categorySelect = By.cssSelector("select[name='category_id']");
    private final By subcategoryCheckbox = By.cssSelector("input[name='sub_category']");
    private final By descriptionCheckbox = By.id("description");
    private final By advancedSearchButton = By.id("button-search");
    private final By emptyResultsMessage = By.cssSelector("#content > p");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getProductNames() {
        return driver.findElements(productNames)
                .stream()
                .map(element -> element.getText().trim())
                .toList();
    }

    public SearchResultsPage searchWithFilters(
            String keyword,
            String category,
            boolean includeSubcategories,
            boolean includeDescriptions
    ) {
        WebElement input = driver.findElement(searchCriteriaInput);
        input.clear();
        input.sendKeys(keyword);

        if (category != null) {
            new Select(driver.findElement(categorySelect)).selectByVisibleText(category);
        }

        setCheckbox(subcategoryCheckbox, includeSubcategories);
        setCheckbox(descriptionCheckbox, includeDescriptions);
        driver.findElement(advancedSearchButton).click();
        return this;
    }

    public String getEmptyResultsMessage() {
        return driver.findElements(emptyResultsMessage)
                .stream()
                .map(element -> element.getText().trim())
                .filter(text -> text.toLowerCase().contains("product that matches"))
                .findFirst()
                .orElse("");
    }

    private void setCheckbox(By locator, boolean expectedState) {
        WebElement checkbox = driver.findElement(locator);
        if (checkbox.isSelected() != expectedState) {
            checkbox.click();
        }
    }
}
