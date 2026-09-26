package de.opencart.tests;

import de.opencart.core.TestBase;
import de.opencart.pages.CheckoutPage;
import de.opencart.pages.ProductPage;
import de.opencart.pages.ShoppingCartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TermsAndConditionsTest extends TestBase {

    private CheckoutPage prepareCheckout() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.openCart().clearCart();

        ProductPage productPage = new ProductPage(driver);
        productPage.openMacBook().setQuantity(1).addToCart();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.openCheckout()
                .selectGuestCheckout()
                .fillBillingDetails(
                        "Tester",
                        "Automation",
                        "tester" + System.currentTimeMillis() + "@gmail.com",
                        "123456789",
                        "Test Street 1",
                        "London",
                        "SW1A 1AA"
                )
                .continueBillingDetails();

        return checkoutPage;
    }

    @Test
    public void termsAndConditionsRequiredTest() {
        CheckoutPage checkoutPage = prepareCheckout();

        checkoutPage.uncheckTermsAndConditions()
                .continuePaymentMethod();

        Assert.assertEquals(
                checkoutPage.getTermsErrorMessage(),
                "Warning: You must agree to the Terms & Conditions!",
                "Terms & Conditions warning is incorrect"
        );
    }

    @Test
    public void acceptTermsAndConditionsTest() {
        CheckoutPage checkoutPage = prepareCheckout();

        Assert.assertFalse(checkoutPage.isTermsAccepted(),
                "Terms & Conditions checkbox should not be selected initially");

        checkoutPage.acceptTermsAndConditions();

        Assert.assertTrue(checkoutPage.isTermsAccepted(),
                "Terms & Conditions checkbox was not selected");

        checkoutPage.continuePaymentMethod();

        Assert.assertTrue(checkoutPage.isConfirmOrderOpened(),
                "Confirm Order step was not opened");
    }
}