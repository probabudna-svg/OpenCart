package de.opencart.tests;

import de.opencart.core.TestBase;
import de.opencart.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductReviewTest extends TestBase {

    @Test
    public void submitValidReviewTest() {
        ProductPage productPage = new ProductPage(driver);

        productPage.openAppleCinema()
                .openReviewsTab()
                .enterReviewName("Tester")
                .enterReviewText("This is an automated review for OpenCart product testing.")
                .selectRating(4);

        Assert.assertTrue(productPage.isRatingSelected(4), "Rating 4 was not selected");

        productPage.submitReview();

        Assert.assertTrue(productPage.getReviewSuccessMessage().contains("Thank you for your review"),
                "Review was not submitted successfully");
    }

    @Test
    public void reviewTextValidationTest() {
        ProductPage productPage = new ProductPage(driver);

        productPage.openAppleCinema()
                .openReviewsTab()
                .enterReviewName("Tester")
                .enterReviewText("Too short")
                .selectRating(4)
                .submitReview();

        Assert.assertTrue(productPage.getReviewErrorMessage().contains("Review Text must be between 25 and 1000 characters"),
                "Review text validation message was not displayed");
    }
}