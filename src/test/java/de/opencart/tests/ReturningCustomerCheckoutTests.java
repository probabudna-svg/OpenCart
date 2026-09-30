package de.opencart.tests;

import de.opencart.core.TestBase;
import de.opencart.pages.CheckoutPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReturningCustomerCheckoutTests extends TestBase {

    @Test
    public void returningCustomerCanStartCheckout() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.openProductPage();
        checkoutPage.addProductToCart();
        checkoutPage.openCart();
        checkoutPage.clickCheckout();

        checkoutPage.enterReturningCustomerCredentials(
                "Testergroup2@gmail.com",
                "123456789Abc!");

        checkoutPage.clickReturningCustomerLogin();

        Assert.assertTrue(new org.openqa.selenium.support.ui.WebDriverWait(
                        driver, java.time.Duration.ofSeconds(10))
                        .until(ExpectedConditions.textToBePresentInElementLocated(
                                By.id("accordion"), "Billing Details")),
                "Nach dem Login wurden die Billing Details nicht angezeigt");
    }
}

