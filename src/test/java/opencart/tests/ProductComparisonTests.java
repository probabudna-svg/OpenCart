package opencart.tests;

import opencart.core.TestBase;
import opencart.pages.CategoryPage;
import opencart.pages.ProductComparePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductComparisonTests extends TestBase {

    @Test
    public void productComparisonAndSpecificationTableTest() {
        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.open("https://opencart.abstracta.us/index.php?route=product/category&path=20");

        String firstProduct = categoryPage.getProductTitle(0);
        String secondProduct = categoryPage.getProductTitle(1);

       categoryPage.addProductToCompare(0);
        String alertMessage1 = categoryPage.getAlertText();
        Assert.assertTrue(
                alertMessage1.contains("Success: You have added") && alertMessage1.contains("product comparison"),
                "Alert message should confirm adding first product to comparison"
        );

       categoryPage.addProductToCompare(1);
        String alertMessage2 = categoryPage.getAlertText();
        Assert.assertTrue(
                alertMessage2.contains("Success: You have added") && alertMessage2.contains("product comparison"),
                "Alert message should confirm adding second product to comparison"
        );

        ProductComparePage comparePage = categoryPage.clickCompareTotal();

        Assert.assertEquals(comparePage.getPageHeading(), "Product Comparison");
        Assert.assertTrue(comparePage.isCompareTableDisplayed(), "Product comparison table should be displayed");


        List<String> comparedNames = comparePage.getComparedProductNames();
        Assert.assertTrue(comparedNames.contains(firstProduct), "First product should be present in comparison table");
        Assert.assertTrue(comparedNames.contains(secondProduct), "Second product should be present in comparison table");


        Assert.assertTrue(comparePage.hasSpecificationRow("Price"), "Specification row 'Price' should exist in comparison table");
        Assert.assertTrue(comparePage.hasSpecificationRow("Model"), "Specification row 'Model' should exist in comparison table");
        Assert.assertTrue(
                comparePage.hasSpecificationRow("Brand") || comparePage.hasSpecificationRow("Brands"),
                "Specification row 'Brand' or 'Brands' should exist in comparison table"
        );
        Assert.assertTrue(comparePage.hasSpecificationRow("Availability"), "Specification row 'Availability' should exist in comparison table");
    }
}
