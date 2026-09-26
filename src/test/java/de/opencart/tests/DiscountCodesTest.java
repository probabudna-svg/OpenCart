package de.opencart.tests;

import de.opencart.core.TestBase;
import de.opencart.pages.ProductPage;
import de.opencart.pages.ShoppingCartPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class DiscountCodesTest extends TestBase {

    private ShoppingCartPage prepareCart() {
        ShoppingCartPage cart = new ShoppingCartPage(driver);
        cart.openCart().clearCart();

        ProductPage productPage = new ProductPage(driver);
        productPage.openMacBook().setQuantity(1).addToCart();

        cart.openCart();
        return cart;
    }

    @Test
    public void invalidCouponTest() {
        ShoppingCartPage cart = prepareCart();
        cart.applyCoupon("INVALID-COUPON-123");

        String error = cart.getErrorMessage();
        Reporter.log("Coupon validation response: " + error, true);

        Assert.assertFalse(error.isBlank(), "Invalid coupon was not rejected");
    }

    @Test
    public void invalidGiftCertificateTest() {
        ShoppingCartPage cart = prepareCart();
        cart.applyGiftCertificate("INVALID-GIFT-123");

        String error = cart.getErrorMessage();
        Reporter.log("Gift Certificate validation response: " + error, true);

        Assert.assertFalse(error.isBlank(), "Invalid Gift Certificate was not rejected");
    }
}