package opencart.tests;

import opencart.core.TestBase;
import opencart.pages.HomePage;
import opencart.pages.RegisterPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class RegisterTests extends TestBase {

    @Test
    public void registerPositiveTest() {
        RegisterPage registerPage = new HomePage(driver)
                .openMyAccount()
                .clickOnRegister();

        Assert.assertTrue(
                registerPage.enterUserData(
                                "Swetlana",
                                "Budna",
                                registerPage.newEmaile(),
                                "1234567890",
                                "xUDUs8bfbQjLNj!",
                                "xUDUs8bfbQjLNj!")
                        .checkPrivacyPolicy()
                        .clickOnContinue()
                        .isLogoutButtonPresent()
        );
    }

    @Test
    public void registerNegativeTestDuplicateEmail () {
        RegisterPage registerPage = new HomePage(driver)
                .openMyAccount()
                .clickOnRegister()
                .enterUserData(
                        "Swetlana",
                        "Budna",
                        "swetlana123@test.com",
                        "1234567890",
                        "xUDUs8bfbQjLNj!",
                        "xUDUs8bfbQjLNj!")
                .checkPrivacyPolicy()
                .clickOnContinue();

        Assert.assertTrue(registerPage.isErrorsPresent());

    }

    @Test
    public void registerNegativePasswordMismatch () {
        RegisterPage registerPage = new HomePage(driver)
                .openMyAccount()
                .clickOnRegister();

        registerPage.enterUserData(
                        "Swetlana",
                        "Budna",
                        registerPage.newEmaile(),
                        "1234567890",
                        "xUDUs8bfbQjLNj!",
                        "xUDUs8bfbQjLNj")
                .checkPrivacyPolicy()
                .clickOnContinue();

        Assert.assertTrue(registerPage.isTextDanger());
    }

    @Test
    public void registerNegativeMissingMandatoryFields () {
        RegisterPage registerPage = new HomePage(driver)
                .openMyAccount()
                .clickOnRegister();

        registerPage.enterUserData(
                        "Swetlana",
                        "Budna",
                        registerPage.newEmaile(),
                        "",
                        "xUDUs8bfbQjLNj!",
                        "xUDUs8bfbQjLNj!")
                .checkPrivacyPolicy()
                .clickOnContinue();

        Assert.assertTrue(registerPage.isTextDanger());
    }

}
