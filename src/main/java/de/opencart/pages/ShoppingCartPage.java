package de.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShoppingCartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By cartRows = By.cssSelector("#content .table-responsive tbody tr");
    private final By quantityInput = By.cssSelector("input[name^='quantity[']");

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[href='#collapse-coupon']")
    private WebElement couponPanel;

    @FindBy(id = "input-coupon")
    private WebElement couponInput;

    @FindBy(id = "button-coupon")
    private WebElement applyCouponButton;

    @FindBy(css = "a[href='#collapse-voucher']")
    private WebElement voucherPanel;

    @FindBy(id = "input-voucher")
    private WebElement voucherInput;

    @FindBy(id = "button-voucher")
    private WebElement applyVoucherButton;

    public ShoppingCartPage openCart() {
        driver.get("https://opencart.abstracta.us/index.php?route=checkout/cart");
        return this;
    }

    public int getCartItemCount() {
        return driver.findElements(cartRows).size();
    }

    public boolean isProductInCart(String productName) {
        List<WebElement> rows = driver.findElements(cartRows);
        for (WebElement row : rows) {
            if (row.getText().contains(productName)) return true;
        }
        return false;
    }

    public ShoppingCartPage updateFirstItemQuantity(int quantity) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
        WebElement row = input.findElement(By.xpath("./ancestor::tr"));
        input.clear();
        input.sendKeys(String.valueOf(quantity));
        row.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.stalenessOf(input));
        return this;
    }

    public String getFirstItemQuantity() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput)).getAttribute("value");
    }

    public ShoppingCartPage removeFirstItem() {
        List<WebElement> rows = driver.findElements(cartRows);
        if (rows.isEmpty()) return this;
        WebElement row = rows.get(0);
        row.findElement(By.cssSelector("button.btn-danger")).click();
        wait.until(ExpectedConditions.stalenessOf(row));
        return this;
    }

    public ShoppingCartPage clearCart() {
        while (!driver.findElements(cartRows).isEmpty()) removeFirstItem();
        return this;
    }

    public ShoppingCartPage applyCoupon(String coupon) {
        couponPanel.click();
        wait.until(ExpectedConditions.visibilityOf(couponInput));
        couponInput.clear();
        couponInput.sendKeys(coupon);
        applyCouponButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#checkout-cart > .alert-danger")));
        return this;
    }

    public ShoppingCartPage applyGiftCertificate(String voucher) {
        voucherPanel.click();
        wait.until(ExpectedConditions.visibilityOf(voucherInput));
        voucherInput.clear();
        voucherInput.sendKeys(voucher);
        applyVoucherButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#checkout-cart > .alert-danger")));
        return this;
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#checkout-cart > .alert-danger"))).getText().trim();
    }
}