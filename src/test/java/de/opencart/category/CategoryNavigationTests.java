package de.opencart.category;

import de.opencart.core.KarynaTestBase;
import de.opencart.pages.CategoryPage;
import de.opencart.pages.HomePage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CategoryNavigationTests extends KarynaTestBase {

    @DataProvider(name = "catalogCategories")
    public Object[][] catalogCategories() {
        return new Object[][]{
                {"Desktops", "20"},
                {"Laptops & Notebooks", "18"},
                {"Components", "25"},
                {"Tablets", "57"},
                {"Phones & PDAs", "24"},
                {"Cameras", "33"}
        };
    }

    @Test(dataProvider = "catalogCategories")
    public void categoryCanBeOpenedFromMainMenu(String categoryName, String expectedPath) {
        CategoryPage category = new HomePage(driver).openCategory(categoryName);

        assertEquals(category.getTitle(), categoryName);
        assertTrue(category.getCurrentUrl().contains("route=product/category"));
        assertTrue(category.getCurrentUrl().contains("path=" + expectedPath));
    }

    @Test
    public void camerasCategoryShowsExpectedProducts() {
        CategoryPage category = new HomePage(driver).openCategory("Cameras");

        assertEquals(
                category.getProductNames(),
                List.of("Canon EOS 5D", "Nikon D300")
        );
    }
}
