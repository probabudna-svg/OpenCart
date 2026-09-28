package opencart.tests;

import opencart.core.TestBase;
import opencart.pages.CategoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CategoryDisplayAndSortTests extends TestBase {

    @Test
    public void viewModeToggleAndSortingTest() {
        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.open("https://opencart.abstracta.us/index.php?route=product/category&path=20");

        categoryPage.clickListView();
        Assert.assertTrue(categoryPage.isListViewActive(), "List view button should have active state");
        Assert.assertTrue(categoryPage.allProductsHaveClass("product-list"), "Products should be styled with product-list layout");

        categoryPage.clickGridView();
        Assert.assertTrue(categoryPage.isGridViewActive(), "Grid view button should have active state");
        Assert.assertTrue(categoryPage.allProductsHaveClass("product-grid"), "Products should be styled with product-grid layout");

        categoryPage.selectSortBy("Price (Low > High)");
        Assert.assertEquals(categoryPage.getSelectedSortOption(), "Price (Low > High)", "Selected option should be Price (Low > High)");
        Assert.assertTrue(driver.getCurrentUrl().contains("sort=p.price&order=ASC"), "URL should contain price ascending sort query parameter");

        categoryPage.selectSortBy("Name (A - Z)");
        Assert.assertEquals(categoryPage.getSelectedSortOption(), "Name (A - Z)", "Selected option should be Name (A - Z)");
        Assert.assertTrue(driver.getCurrentUrl().contains("sort=pd.name&order=ASC"), "URL should contain name A-Z sort query parameter");

        categoryPage.selectSortBy("Rating (Highest)");
        Assert.assertEquals(categoryPage.getSelectedSortOption(), "Rating (Highest)", "Selected option should be Rating (Highest)");
        Assert.assertTrue(driver.getCurrentUrl().contains("sort=rating&order=DESC"), "URL should contain rating highest sort query parameter");
    }
}
