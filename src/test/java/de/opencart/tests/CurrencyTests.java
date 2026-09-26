package de.opencart.tests;

import de.opencart.core.TestBase;
import de.opencart.pages.CurrencyPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CurrencyTests extends TestBase {
    @Test
    public void currencySwitcherRecalculatesProductPrice() {

        driver.get(
                "https://opencart.abstracta.us/index.php?route=product/category&path=20_27");

        CurrencyPage currencyPage = new CurrencyPage(driver);

        currencyPage.selectCurrency("USD");
        String usdPrice = currencyPage.getProductPriceText();

        Assert.assertTrue(
                usdPrice.contains("$"),
                "The price should be displayed in USD, but was: " + usdPrice);

        currencyPage.selectCurrency("EUR");
        String eurPrice = currencyPage.getProductPriceText();

        Assert.assertTrue(
                eurPrice.contains("€"),
                "The price should be displayed in EUR, but was: " + eurPrice);

        Assert.assertNotEquals(
                eurPrice,
                usdPrice,
                "The displayed price should change when switching from USD to EUR");

        currencyPage.selectCurrency("GBP");
        String gbpPrice = currencyPage.getProductPriceText();

        Assert.assertTrue(
                gbpPrice.contains("£"),
                "The price should be displayed in GBP, but was: " + gbpPrice);

        Assert.assertNotEquals(
                gbpPrice,
                eurPrice,
                "The displayed price should change when switching from EUR to GBP");
    }

    @Test(
            expectedExceptions = IllegalArgumentException.class
    )
    public void currencySwitcherRejectsUnsupportedCurrency() {

        CurrencyPage currencyPage = new CurrencyPage(driver);

        currencyPage.selectCurrency("CAD");
    }
}


