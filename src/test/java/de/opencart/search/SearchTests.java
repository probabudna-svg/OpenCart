package de.opencart.search;

import de.opencart.core.KarynaTestBase;
import de.opencart.pages.HomePage;
import de.opencart.pages.SearchResultsPage;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchTests extends KarynaTestBase {

    @Test
    public void existingProductCanBeFound() {
        SearchResultsPage results = new HomePage(driver).searchFor("macbook");

        assertEquals(
                results.getProductNames(),
                List.of("MacBook", "MacBook Air", "MacBook Pro")
        );
    }

    @Test
    public void nonExistingProductReturnsEmptyResult() {
        SearchResultsPage results = new HomePage(driver).searchFor("NoSuchProduct12345");

        assertTrue(results.getProductNames().isEmpty());
    }

    @Test
    public void noResultsMessageIsShownForNonExistingProduct() {
        SearchResultsPage results = new HomePage(driver).searchFor("NoSuchProduct12345");

        assertEquals(
                results.getEmptyResultsMessage(),
                "There is no product that matches the search criteria."
        );
    }

    @Test
    public void productCanBeFoundByDescription() {
        SearchResultsPage results = new HomePage(driver)
                .openSearchPage()
                .searchWithFilters("Intel", null, false, true);

        assertEquals(
                results.getProductNames(),
                List.of("iMac", "MacBook", "MacBook Pro", "Sony VAIO")
        );
    }

    @Test
    public void productCanBeFoundInCategoryAndSubcategories() {
        SearchResultsPage results = new HomePage(driver)
                .openSearchPage()
                .searchWithFilters("MacBook", "Laptops & Notebooks", true, false);

        assertEquals(
                results.getProductNames(),
                List.of("MacBook", "MacBook Air", "MacBook Pro")
        );
    }
}
