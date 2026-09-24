package de.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void startGuestCheckout() {
        driver.get("https://opencart.abstracta.us/index.php?route=product/product&path=24&product_id=40");
        driver.findElement(By.id("button-cart")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
        driver.get("https://opencart.abstracta.us/index.php?route=checkout/checkout");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='account'][value='guest']"))).click();
        driver.findElement(By.id("button-account")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-payment-firstname")));
    }

    public void submitEmptyBillingAddress() {
        driver.findElement(By.id("button-guest")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
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

        WebElement sameAddress = driver.findElement(By.cssSelector("input[name='shipping_address']"));
        if (sameAddress.isSelected()) {
            sameAddress.click();
        }
    }

    public void continueFromBillingToDelivery() {
        driver.findElement(By.id("button-guest")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-shipping-firstname")));
    }

    public void submitEmptyDeliveryAddress() {
        driver.findElement(By.id("button-guest-shipping")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[name='shipping_method'][value='flat.flat']")));
    }

    public String shippingMethodText() {
        return driver.findElement(By.id("collapse-shipping-method")).getText();
    }

    public void continueFromShippingToPaymentMethod() {
        driver.findElement(By.cssSelector("input[name='shipping_method'][value='flat.flat']")).click();
        driver.findElement(By.id("button-shipping-method")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[name='payment_method'][value='cod']")));
    }

    public void selectCashOnDeliveryAndContinue() {
        driver.findElement(By.cssSelector("input[name='payment_method'][value='cod']")).click();
        driver.findElement(By.cssSelector("input[name='agree']")).click();
        driver.findElement(By.id("button-payment-method")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
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
        WebElement country = driver.findElement(By.id("input-" + section + "-country"));
        if (!new Select(country).getFirstSelectedOption().getText().equals("United Kingdom")) {
            new Select(country).selectByVisibleText("United Kingdom");
        }
        By region = By.id("input-" + section + "-zone");
        wait.until(d -> new Select(d.findElement(region)).getOptions().size() > 1);
        new Select(driver.findElement(region)).selectByVisibleText("Greater London");
    }
}
