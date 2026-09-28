package de.opencart.pages;

import de.opencart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ProductComparePage extends BasePage {

    private By heading = By.cssSelector("#content h1");
    private By compareTable = By.cssSelector("table.table-bordered");
    private By productLinks = By.cssSelector("table.table-bordered tbody tr:first-child td a strong");
    private By tableRows = By.cssSelector("table.table-bordered tbody tr");

    public ProductComparePage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeading() {
        return getWait(10).until(ExpectedConditions.visibilityOfElementLocated(heading)).getText().trim();
    }

    public boolean isCompareTableDisplayed() {
        List<WebElement> tables = driver.findElements(compareTable);
        return !tables.isEmpty() && tables.get(0).isDisplayed();
    }

    public List<String> getComparedProductNames() {
        List<WebElement> elements = driver.findElements(productLinks);
        List<String> names = new ArrayList<>();
        for (WebElement el : elements) {
            names.add(el.getText().trim());
        }
        return names;
    }

    public boolean hasSpecificationRow(String specName) {
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            if (!cells.isEmpty()) {
                String firstCellText = cells.get(0).getText().trim();
                if (firstCellText.equalsIgnoreCase(specName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
