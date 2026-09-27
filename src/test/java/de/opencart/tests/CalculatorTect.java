package de.opencart.tests;

import de.opencart.core.TestBase;
import de.opencart.pages.CartPage;
import de.opencart.pages.HomePage;
import de.opencart.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CalculatorTect extends TestBase {

@BeforeMethod
    public void precondition () {

    new HomePage(driver)
            .clickDesktops()
            .clickMac()
            .addCart();

}

@Test
    public void CalculatorPositive () {
    CartPage cartPage = new HomePage(driver)
            .clickCart()
            .viewCart()
            .clickViewCart()
            .clickShipping()
            .selectCountry("Ukraine")
            .selectRegion("Kharkivs'ka Oblast'")
            .inputPostcode("61000")
            .clickQuotes()
            .cliskFlatRate()
            .clickButtonShipping();

    Assert.assertTrue(cartPage.isShippigRatePresent());

}




}
