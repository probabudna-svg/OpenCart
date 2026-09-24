package de.opencart.checkout;

import de.opencart.core.KarynaTestBase;
import de.opencart.pages.CheckoutPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ShippingPaymentTests extends KarynaTestBase {

    @Test
    public void flatShippingAndCashOnDeliveryReachOrderSummary() {
        CheckoutPage checkout = prepareShippingMethod();
        assertTrue(checkout.shippingMethodText().contains("Flat Shipping Rate"));

        checkout.continueFromShippingToPaymentMethod();
        checkout.selectCashOnDeliveryAndContinue();

        assertTrue(checkout.orderSummary().contains("iPhone"));
        assertTrue(checkout.orderSummary().contains("Flat Shipping Rate:"));
    }

    private CheckoutPage prepareShippingMethod() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.startGuestCheckout();
        checkout.fillBillingAddress();
        checkout.continueFromBillingToDelivery();
        checkout.fillDeliveryAddress();
        checkout.continueFromDeliveryToShippingMethod();
        return checkout;
    }

}
