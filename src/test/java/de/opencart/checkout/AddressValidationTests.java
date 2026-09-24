package de.opencart.checkout;

import de.opencart.core.KarynaTestBase;
import de.opencart.pages.CheckoutPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AddressValidationTests extends KarynaTestBase {

    @Test
    public void emptyBillingAddressShowsErrors() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.startGuestCheckout();
        checkout.submitEmptyBillingAddress();

        String errors = checkout.billingErrors();
        assertTrue(errors.contains("First Name"));
        assertTrue(errors.contains("Last Name"));
        assertTrue(errors.contains("E-Mail"));
        assertTrue(errors.contains("Address 1"));
        assertTrue(errors.contains("City"));
    }

    @Test
    public void validBillingAddressOpensDeliveryDetails() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.startGuestCheckout();
        checkout.fillBillingAddress();
        checkout.continueFromBillingToDelivery();
    }

    @Test
    public void emptyDeliveryAddressShowsErrors() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.startGuestCheckout();
        checkout.fillBillingAddress();
        checkout.continueFromBillingToDelivery();
        checkout.submitEmptyDeliveryAddress();

        String errors = checkout.deliveryErrors();
        assertTrue(errors.contains("First Name"));
        assertTrue(errors.contains("Last Name"));
        assertTrue(errors.contains("Address 1"));
        assertTrue(errors.contains("City"));
    }

    @Test
    public void validDeliveryAddressOpensDeliveryMethod() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.startGuestCheckout();
        checkout.fillBillingAddress();
        checkout.continueFromBillingToDelivery();
        checkout.fillDeliveryAddress();
        checkout.continueFromDeliveryToShippingMethod();

        assertTrue(checkout.shippingMethodText().contains("Flat Shipping Rate"));
    }
}
