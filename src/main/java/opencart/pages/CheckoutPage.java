package opencart.pages;

import opencart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage extends BasePage {

    private final WebDriverWait wait;
    private final WebDriverWait karynaWait;

    private final By addToCartButton = By.id("button-cart");
    private final By cartButton = By.id("cart");
    private final By checkoutLink = By.cssSelector("a[href*='route=checkout/checkout']");
    private final By guestCheckoutRadioLocator = By.cssSelector("input[name='account'][value='guest']");
    private final By accountContinueButton = By.id("button-account");

    private final By firstNameField = By.id("input-payment-firstname");
    private final By lastNameField = By.id("input-payment-lastname");
    private final By emailField = By.id("input-payment-email");
    private final By telephoneField = By.id("input-payment-telephone");
    private final By addressField = By.id("input-payment-address-1");
    private final By cityField = By.id("input-payment-city");
    private final By postcodeField = By.id("input-payment-postcode");
    private final By countryDropdown = By.id("input-payment-country");
    private final By regionDropdown = By.id("input-payment-zone");

    private final By termsCheckboxLocator = By.name("agree");
    private final By paymentContinueButton = By.id("button-payment-method");
    private final By confirmOrderButton = By.id("button-confirm");

    private final By returningCustomerEmail = By.id("input-email");
    private final By returningCustomerPassword = By.id("input-password");
    private final By returningCustomerLoginButton = By.id("button-login");

    private final By registeredFirstName = By.id("input-payment-firstname");
    private final By registeredLastName = By.id("input-payment-lastname");
    private final By registeredAddress1 = By.id("input-payment-address-1");
    private final By registeredCity = By.id("input-payment-city");
    private final By registeredPostCode = By.id("input-payment-postcode");
    private final By registeredCountry = By.id("input-payment-country");
    private final By registeredZone = By.id("input-payment-zone");

    private final By paymentAddressContinue = By.id("button-payment-address");
    private final By shippingAddressContinue = By.id("button-shipping-address");
    private final By flatRateShipping = By.cssSelector("input[name='shipping_method'][value='flat.flat']");
    private final By continueShippingMethodButton = By.id("button-shipping-method");
    private final By bankTransferPayment = By.cssSelector("input[name='payment_method'][value='bank_transfer']");
    private final By agreeTermsCheckbox = By.cssSelector("input[name='agree'][value='1']");
    private final By billingContinueButton = By.id("button-guest");
    private final By orderSuccessHeading = By.cssSelector("#content h1");

    @FindBy(css = "input[name='account'][value='guest']")
    private WebElement guestCheckoutRadio;

    @FindBy(id = "button-account")
    private WebElement checkoutOptionContinueButton;

    @FindBy(id = "input-payment-firstname")
    private WebElement firstNameInput;

    @FindBy(id = "input-payment-lastname")
    private WebElement lastNameInput;

    @FindBy(id = "input-payment-email")
    private WebElement emailInput;

    @FindBy(id = "input-payment-telephone")
    private WebElement telephoneInput;

    @FindBy(id = "input-payment-address-1")
    private WebElement addressInput;

    @FindBy(id = "input-payment-city")
    private WebElement cityInput;

    @FindBy(id = "input-payment-postcode")
    private WebElement postcodeInput;

    @FindBy(id = "button-guest")
    private WebElement guestContinueButton;

    @FindBy(css = "input[name='payment_method']")
    private WebElement paymentMethodRadio;

    @FindBy(css = "input[name='agree']")
    private WebElement termsCheckbox;

    @FindBy(id = "button-payment-method")
    private WebElement paymentMethodContinueButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.karynaWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void startGuestCheckout() {
        driver.get("https://opencart.abstracta.us/index.php?route=product/product&path=24&product_id=40");
        driver.findElement(By.id("button-cart")).click();
        karynaWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
        driver.get("https://opencart.abstracta.us/index.php?route=checkout/checkout");
        karynaWait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[name='account'][value='guest']"))).click();
        driver.findElement(By.id("button-account")).click();
        karynaWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("input-payment-firstname")));
    }

    public void submitEmptyBillingAddress() {
        driver.findElement(By.id("button-guest")).click();
        karynaWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#collapse-payment-address .text-danger")));
    }

    public String billingErrors() {
        return driver.findElement(By.id("collapse-payment-address")).getText();
    }

    public void fillBillingAddress() {
        type("input-payment-firstname", "Karyna");
        type("input-payment-lastname", "Tester");
        type("input-payment-email", "karyna.qa@example.com");
        type("input-payment-telephone", "1234567890");
        type("input-payment-address-1", "10 Test Street");
        type("input-payment-city", "London");
        type("input-payment-postcode", "SW1A 1AA");
        chooseRegion("payment");

        WebElement sameAddress = driver.findElement(
                By.cssSelector("input[name='shipping_address']"));

        if (sameAddress.isSelected()) {
            sameAddress.click();
        }
    }

    public void continueFromBillingToDelivery() {
        driver.findElement(By.id("button-guest")).click();
        karynaWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("input-shipping-firstname")));
    }

    public void submitEmptyDeliveryAddress() {
        driver.findElement(By.id("button-guest-shipping")).click();
        karynaWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#collapse-shipping-address .text-danger")));
    }

    public String deliveryErrors() {
        return driver.findElement(By.id("collapse-shipping-address")).getText();
    }

    public void fillDeliveryAddress() {
        type("input-shipping-firstname", "Karyna");
        type("input-shipping-lastname", "Tester");
        type("input-shipping-address-1", "20 Sample Road");
        type("input-shipping-city", "London");
        type("input-shipping-postcode", "SW1A 1AA");
        chooseRegion("shipping");
    }

    public void continueFromDeliveryToShippingMethod() {
        driver.findElement(By.id("button-guest-shipping")).click();
        karynaWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[name='shipping_method'][value='flat.flat']")));
    }

    public String shippingMethodText() {
        return driver.findElement(By.id("collapse-shipping-method")).getText();
    }

    public void continueFromShippingToPaymentMethod() {
        driver.findElement(
                By.cssSelector("input[name='shipping_method'][value='flat.flat']")).click();
        driver.findElement(By.id("button-shipping-method")).click();
        karynaWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[name='payment_method'][value='cod']")));
    }

    public void selectCashOnDeliveryAndContinue() {
        driver.findElement(
                By.cssSelector("input[name='payment_method'][value='cod']")).click();
        driver.findElement(By.cssSelector("input[name='agree']")).click();
        driver.findElement(By.id("button-payment-method")).click();
        karynaWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#collapse-checkout-confirm table")));
    }

    public String orderSummary() {
        return driver.findElement(By.id("collapse-checkout-confirm")).getText();
    }

    private void type(String id, String value) {
        WebElement input = driver.findElement(By.id(id));
        input.clear();
        input.sendKeys(value);
    }

    private void chooseRegion(String section) {
        WebElement country = driver.findElement(
                By.id("input-" + section + "-country"));

        if (!new Select(country).getFirstSelectedOption().getText().equals("United Kingdom")) {
            new Select(country).selectByVisibleText("United Kingdom");
        }

        By region = By.id("input-" + section + "-zone");

        karynaWait.until(d ->
                new Select(d.findElement(region)).getOptions().size() > 1);

        new Select(driver.findElement(region))
                .selectByVisibleText("Greater London");
    }

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

    public CheckoutPage selectGuestCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(guestCheckoutRadio)).click();
        checkoutOptionContinueButton.click();
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        return this;
    }

    public void continueFromAccountStep() {
        if (!driver.findElements(firstNameField).isEmpty()
                && driver.findElement(firstNameField).isDisplayed()) {
            return;
        }

        getWait(10)
                .until(ExpectedConditions.elementToBeClickable(accountContinueButton))
                .click();
    }

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
        WebElement terms = getWait(10)
                .until(ExpectedConditions.elementToBeClickable(termsCheckboxLocator));

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

    public void fillRegisteredBillingAddress() {
        type(getWait(10).until(
                ExpectedConditions.visibilityOfElementLocated(registeredFirstName)), "Test");

        type(getWait(10).until(
                ExpectedConditions.visibilityOfElementLocated(registeredLastName)), "Kunde");

        type(getWait(10).until(
                ExpectedConditions.visibilityOfElementLocated(registeredAddress1)), "Teststraße 5");

        type(getWait(10).until(
                ExpectedConditions.visibilityOfElementLocated(registeredCity)), "Berlin");

        type(getWait(10).until(
                ExpectedConditions.visibilityOfElementLocated(registeredPostCode)), "10115");

        Select countrySelect = new Select(
                getWait(10).until(
                        ExpectedConditions.elementToBeClickable(registeredCountry)));

        countrySelect.selectByVisibleText("Germany");

        getWait(10).until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//select[@id='input-payment-zone']/option[normalize-space()='Berlin']")));

        Select zoneSelect = new Select(
                getWait(10).until(
                        ExpectedConditions.elementToBeClickable(registeredZone)));

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
        WebElement payment = getWait(10)
                .until(ExpectedConditions.elementToBeClickable(bankTransferPayment));

        if (!payment.isSelected()) {
            payment.click();
        }
    }

    public void agreeToTerms() {
        WebElement checkbox = getWait(10)
                .until(ExpectedConditions.elementToBeClickable(agreeTermsCheckbox));

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public String getOrderSuccessMessage() {
        return getWait(10)
                .until(ExpectedConditions.visibilityOfElementLocated(orderSuccessHeading))
                .getText();
    }

    public CheckoutPage openCheckout() {
        driver.get("https://opencart.abstracta.us/index.php?route=checkout/checkout");
        return this;
    }

    public CheckoutPage fillBillingDetails(
            String firstName,
            String lastName,
            String email,
            String telephone,
            String address,
            String city,
            String postcode
    ) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        emailInput.clear();
        emailInput.sendKeys(email);

        telephoneInput.clear();
        telephoneInput.sendKeys(telephone);

        addressInput.clear();
        addressInput.sendKeys(address);

        cityInput.clear();
        cityInput.sendKeys(city);

        postcodeInput.clear();
        postcodeInput.sendKeys(postcode);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("input-payment-country")));

        new Select(driver.findElement(
                By.id("input-payment-country")))
                .selectByVisibleText("United Kingdom");

        wait.until(driver -> {
            WebElement zoneElement =
                    driver.findElement(By.id("input-payment-zone"));

            if (!zoneElement.isEnabled()) {
                return false;
            }

            return new Select(zoneElement)
                    .getOptions()
                    .stream()
                    .anyMatch(option ->
                            option.getText().trim().equals("Bedfordshire"));
        });

        new Select(driver.findElement(
                By.id("input-payment-zone")))
                .selectByVisibleText("Bedfordshire");

        return this;
    }

    public CheckoutPage continueBillingDetails() {
        guestContinueButton.click();
        wait.until(ExpectedConditions.visibilityOf(paymentMethodContinueButton));
        return this;
    }

    public boolean isPaymentMethodSelected() {
        return paymentMethodRadio.isSelected();
    }

    public boolean isTermsAccepted() {
        return termsCheckbox.isSelected();
    }

    public CheckoutPage acceptTermsAndConditions() {
        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }

        return this;
    }

    public CheckoutPage uncheckTermsAndConditions() {
        if (termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }

        return this;
    }

    public CheckoutPage continuePaymentMethod() {
        paymentMethodContinueButton.click();
        return this;
    }

    public String getTermsErrorMessage() {
        WebElement warning = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("#collapse-payment-method .alert-danger")));

        return warning.getText().replace("×", "").trim();
    }

    public boolean isConfirmOrderOpened() {
        wait.until(driver -> {
            WebElement panel =
                    driver.findElement(By.id("collapse-checkout-confirm"));

            String classes = panel.getAttribute("class");
            String content = panel.getText().trim();

            return classes.contains("in") && !content.isEmpty();
        });

        return driver
                .findElement(By.id("collapse-checkout-confirm"))
                .getAttribute("class")
                .contains("in");
    }
}