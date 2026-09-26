package de.opencart.tests;

import de.opencart.core.TestBase;
import de.opencart.pages.CheckoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class GuestCheckoutTests extends TestBase {

    @Test
    public void openCheckoutFromCart() {

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.openProductPage();
        checkoutPage.addProductToCart();
        checkoutPage.openCart();
        checkoutPage.clickCheckout();
        checkoutPage.selectGuestCheckout();
        checkoutPage.continueFromAccountStep();
        checkoutPage.enterBillingPersonalDetails(
                "Test",
                "Gast",
                "test.gast@example.com",
                "0123456789");
        checkoutPage.enterBillingAddress(
                "Teststraße 5", "Berlin", "10115");

        checkoutPage.selectCountryAndRegion();
        checkoutPage.continueFromBillingDetails();
        checkoutPage.continueFromPaymentMethod();
        checkoutPage.confirmOrder();
        checkoutPage.waitForOrderSuccess();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("route=checkout/success"),
                "Die Bestätigungsseite wurde nicht geöffnet");

        Assert.assertTrue(
                driver.findElement(By.tagName("body"))
                        .getText()
                        .contains("Your order has been placed!"),
                "Die Bestellung wurde nicht erfolgreich bestätigt");
    }
}
