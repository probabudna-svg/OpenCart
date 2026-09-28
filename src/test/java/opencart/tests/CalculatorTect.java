package opencart.tests;

import opencart.core.TestBase;
import opencart.pages.CartPage;
import opencart.pages.HomePage;
import opencart.pages.RegisterPage;
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
