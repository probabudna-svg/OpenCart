package de.opencart.pages;

import de.opencart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    private final By addToCartButton =
            By.id("button-cart");

    private final By cartButton =
            By.id("cart");

    private final By checkoutLink =
            By.cssSelector("a[href*='route=checkout/checkout']");
    public void openProductPage() {
        driver.get(
                "https://opencart.abstracta.us/index.php?route=product/product&product_id=43"
        );
    }

    public void addProductToCart() {
        getWait(10).until(
                ExpectedConditions.elementToBeClickable(addToCartButton)
        ).click();
    }

    public void openCart() {
        getWait(10).until(
                ExpectedConditions.elementToBeClickable(cartButton)
        ).click();
    }

    public void clickCheckout() {
        getWait(10).until(
                ExpectedConditions.elementToBeClickable(checkoutLink)
        ).click();
    }
    private final By guestCheckoutRadio =
            By.cssSelector("input[name='account'][value='guest']");

    private final By accountContinueButton = By.id("button-account");

    public void selectGuestCheckout() {
        var guestOption = getWait(10)
                .until(ExpectedConditions.elementToBeClickable(guestCheckoutRadio));

        if (!guestOption.isSelected()) {
            guestOption.click();
        }
    }

    public void continueFromAccountStep() {
        getWait(10)
                .until(ExpectedConditions.elementToBeClickable(accountContinueButton))
                .click();
    }
    private final By firstNameField = By.id("input-payment-firstname");
    private final By lastNameField = By.id("input-payment-lastname");
    private final By emailField = By.id("input-payment-email");
    private final By telephoneField = By.id("input-payment-telephone");
    private final By addressField = By.id("input-payment-address-1");
    private final By cityField = By.id("input-payment-city");
    private final By postcodeField = By.id("input-payment-postcode");
    private final By countryDropdown = By.id("input-payment-country");
    private final By regionDropdown = By.id("input-payment-zone");
    private final By termsCheckbox = By.name("agree");
    private final By paymentContinueButton = By.id("button-payment-method");
    private final By confirmOrderButton = By.id("button-confirm");
    private final By returningCustomerEmail = By.id("input-email");
    private final By returningCustomerPassword = By.id("input-password");
    private final By returningCustomerLoginButton = By.id("button-login");
    private final By firstName = By.id("input-payment-firstname");
    private final By lastName = By.id("input-payment-lastname");
    private final By address1 = By.id("input-payment-address-1");
    private final By city = By.id("input-payment-city");
    private final By postCode = By.id("input-payment-postcode");
    private final By country = By.id("input-payment-country");
    private final By zone = By.id("input-payment-zone");
    private final By paymentAddressContinue = By.id("button-payment-address");
    private final By shippingAddressContinue = By.id("button-shipping-address");
    private final By flatRateShipping = By.cssSelector("input[name='shipping_method'][value='flat.flat']");
    private final By continueShippingMethodButton = By.id("button-shipping-method");
    private final By bankTransferPayment = By.cssSelector("input[name='payment_method'][value='bank_transfer']");
    private final By agreeTermsCheckbox = By.cssSelector("input[name='agree'][value='1']");
    private final By continuePaymentMethodButton = By.id("button-payment-method");
    private final By orderSuccessHeading = By.cssSelector("#content h1");



    public void selectFlatRateShipping() {
        getWait(10)
                .until(ExpectedConditions.elementToBeClickable(flatRateShipping))
                .click();
    }

    public void enterBillingPersonalDetails(
            String firstName,
            String lastName,
            String email,
            String telephone
    ) {
        type(getWait(10).until(
                ExpectedConditions.visibilityOfElementLocated(firstNameField)
        ), firstName);

        type(driver.findElement(lastNameField), lastName);
        type(driver.findElement(emailField), email);
        type(driver.findElement(telephoneField), telephone);
    }
    public void enterBillingAddress(String address, String city, String postcode) {
        type(getWait(10).until(
                ExpectedConditions.visibilityOfElementLocated(addressField)
        ), address);

        type(driver.findElement(cityField), city);
        type(driver.findElement(postcodeField), postcode);
    }

    public void selectRegion(String region) {
        Select select = new Select(
                getWait(10).until(
                        ExpectedConditions.visibilityOfElementLocated(regionDropdown)));
        select.selectByVisibleText(region);
    }
    private final By billingContinueButton = By.id("button-guest");

    public void continueFromBillingDetails() {
        getWait(10)
                .until(ExpectedConditions.elementToBeClickable(billingContinueButton))
                .click();
    }
    public void selectCountryAndRegion() {
        Select country = new Select(driver.findElement(countryDropdown));
        country.selectByVisibleText("Germany");

        Select region = new Select(
                getWait(10).until(
                        ExpectedConditions.visibilityOfElementLocated(regionDropdown)
                )
        );
        region.selectByVisibleText("Berlin");
    }
    public void continueFromPaymentMethod() {
        var terms = getWait(10)
                .until(ExpectedConditions.elementToBeClickable(termsCheckbox));

        if (!terms.isSelected()) {
            terms.click();
        }

        getWait(10)
                .until(ExpectedConditions.elementToBeClickable(paymentContinueButton))
                .click();
    }
    public void confirmOrder() {
        getWait(10)
                .until(ExpectedConditions.elementToBeClickable(confirmOrderButton))
                .click();
    }
    public void waitForOrderSuccess() {
        getWait(15).until(
                ExpectedConditions.urlContains("route=checkout/success"));
    }
    public void enterReturningCustomerCredentials(String email, String password) {
        getWait(10).until(
                ExpectedConditions.visibilityOfElementLocated(returningCustomerEmail)
        ).sendKeys(email);

        driver.findElement(returningCustomerPassword).sendKeys(password);
    }

    public void clickReturningCustomerLogin() {
        getWait(10).until(
                ExpectedConditions.elementToBeClickable(returningCustomerLoginButton)
        ).click();
    }
    public void fillBillingAddress() {
        type(getWait(10).until(ExpectedConditions.visibilityOfElementLocated(firstName)),
                "Test");
        type(getWait(10).until(ExpectedConditions.visibilityOfElementLocated(lastName)),
                "Kunde");
        type(getWait(10).until(ExpectedConditions.visibilityOfElementLocated(address1)),
                "Teststraße 5");
        type(getWait(10).until(ExpectedConditions.visibilityOfElementLocated(city)),
                "Berlin");
        type(getWait(10).until(ExpectedConditions.visibilityOfElementLocated(postCode)),
                "10115");

        Select countrySelect = new Select(
                getWait(10).until(ExpectedConditions.elementToBeClickable(country)));
        countrySelect.selectByVisibleText("Germany");

        getWait(10).until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//select[@id='input-payment-zone']/option[normalize-space()='Berlin']")));

        Select zoneSelect = new Select(
                getWait(10).until(ExpectedConditions.elementToBeClickable(zone)));
        zoneSelect.selectByVisibleText("Berlin");
    }

    public void continueFromBillingAddress() {
        getWait(10)
                .until(ExpectedConditions.elementToBeClickable(paymentAddressContinue))
                .click();
    }
    public void continueWithSavedShippingAddress() {
        getWait(10)
                .until(ExpectedConditions.elementToBeClickable(shippingAddressContinue))
                .click();
    }
    public void continueFromShippingMethod() {
        getWait(10)
                .until(ExpectedConditions.elementToBeClickable(continueShippingMethodButton))
                .click();
    }
    public void selectBankTransferPayment() {
        var payment = getWait(10)
                .until(ExpectedConditions.elementToBeClickable(bankTransferPayment));

        if (!payment.isSelected()) {
            payment.click();
        }
    }

    public void agreeToTerms() {
        var checkbox = getWait(10)
                .until(ExpectedConditions.elementToBeClickable(agreeTermsCheckbox));

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }
//    public void confirmOrder() {
//        getWait(10)
//                .until(ExpectedConditions.elementToBeClickable(confirmOrderButton))
//                .click();
//    }
//
//    public void waitForOrderSuccess() {
//        getWait(10).until(
//                ExpectedConditions.urlContains("route=checkout/success")
//        );
//    }

    public String getOrderSuccessMessage() {
        return getWait(10)
                .until(ExpectedConditions.visibilityOfElementLocated(orderSuccessHeading))
                .getText();
    }



}
//    public void continueFromPaymentMethod() {
//        getWait(10)
//                .until(ExpectedConditions.elementToBeClickable(continuePaymentMethodButton))
//                .click();
//    }