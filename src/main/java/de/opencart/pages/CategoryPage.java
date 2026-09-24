package de.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CategoryPage {

    private final WebDriver driver;

    private final By categoryTitle = By.cssSelector("#content h2");
    private final By productNames = By.cssSelector(".product-layout h4 a");

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.findElement(categoryTitle).getText().trim();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public List<String> getProductNames() {
        return driver.findElements(productNames)
                .stream()
                .map(element -> element.getText().trim())
                .toList();
    }
}
