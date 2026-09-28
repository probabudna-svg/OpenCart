package opencart.tests;

import opencart.core.TestBase;
import opencart.pages.CheckoutPage;
import opencart.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisteredCheckoutTests extends TestBase {

    @Test
    public void registeredCustomerCheckout() {
        driver.get("https://opencart.abstracta.us/index.php?route=account/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Testergroup2@gmail.com", "123456789Abc!");

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.openProductPage();
        checkoutPage.addProductToCart();
        checkoutPage.openCart();
        checkoutPage.clickCheckout();
//        checkoutPage.fillBillingAddress();

        checkoutPage.continueFromBillingAddress();
        //checkoutPage.continueWithSavedShippingAddress();
        //checkoutPage.selectFlatRateShipping();
        //checkoutPage.continueFromShippingMethod();
        checkoutPage.selectBankTransferPayment();
        checkoutPage.agreeToTerms();
        checkoutPage.continueFromPaymentMethod();
        checkoutPage.confirmOrder();
        checkoutPage.waitForOrderSuccess();

        // Vorläufig prüfen wir, dass der Checkout weiterhin geöffnet ist.
        Assert.assertTrue(driver.getCurrentUrl().contains("route=checkout/"),
                "Der Checkout ist nach dem Rechnungsadress-Schritt nicht mehr geöffnet.");
        Assert.assertEquals(
                checkoutPage.getOrderSuccessMessage(),
                "Your order has been placed!",
                "Die Bestellbestätigungsseite wurde nicht angezeigt.");
    }
}
