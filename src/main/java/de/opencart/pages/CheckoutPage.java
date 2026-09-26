package de.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

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

    public CheckoutPage openCheckout() {
        driver.get("https://opencart.abstracta.us/index.php?route=checkout/checkout");
        return this;
    }

    public CheckoutPage selectGuestCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(guestCheckoutRadio)).click();
        checkoutOptionContinueButton.click();
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        return this;
    }

    public CheckoutPage fillBillingDetails(String firstName, String lastName, String email, String telephone, String address, String city, String postcode) {
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

        wait.until(ExpectedConditions.elementToBeClickable(By.id("input-payment-country")));
        new Select(driver.findElement(By.id("input-payment-country"))).selectByVisibleText("United Kingdom");

        wait.until(driver -> {
            WebElement zoneElement = driver.findElement(By.id("input-payment-zone"));
            if (!zoneElement.isEnabled()) return false;
            return new Select(zoneElement).getOptions().stream().anyMatch(option -> option.getText().trim().equals("Bedfordshire"));
        });

        new Select(driver.findElement(By.id("input-payment-zone"))).selectByVisibleText("Bedfordshire");
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
        if (!termsCheckbox.isSelected()) termsCheckbox.click();
        return this;
    }

    public CheckoutPage uncheckTermsAndConditions() {
        if (termsCheckbox.isSelected()) termsCheckbox.click();
        return this;
    }

    public CheckoutPage continuePaymentMethod() {
        paymentMethodContinueButton.click();
        return this;
    }

    public String getTermsErrorMessage() {
        WebElement warning = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#collapse-payment-method .alert-danger")));
        return warning.getText().replace("×", "").trim();
    }

    public boolean isConfirmOrderOpened() {
        wait.until(driver -> {
            WebElement panel = driver.findElement(By.id("collapse-checkout-confirm"));
            String classes = panel.getAttribute("class");
            String content = panel.getText().trim();
            return classes.contains("in") && !content.isEmpty();
        });
        return driver.findElement(By.id("collapse-checkout-confirm")).getAttribute("class").contains("in");
    }
}