package de.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReturnPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ReturnPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void open() {
        driver.get("https://opencart.abstracta.us/index.php?route=account/return/add");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname")));
    }

    public boolean formIsShown() {
        return driver.findElement(By.id("input-order-id")).isDisplayed()
                && driver.findElement(By.id("input-product")).isDisplayed()
                && driver.findElement(By.id("input-model")).isDisplayed();
    }

    public void submit() {
        driver.findElement(By.cssSelector("input[type='submit'][value='Submit']")).click();
    }

    public void waitForErrors() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".text-danger")));
    }

    public String formText() {
        return driver.findElement(By.id("content")).getText();
    }

    public void fillValidRequest(String orderId) {
        type("input-firstname", "Karyna");
        type("input-lastname", "Tester");
        type("input-email", "karyna.qa@example.com");
        type("input-telephone", "1234567890");
        type("input-order-id", orderId);
        type("input-product", "iPhone");
        type("input-model", "product 11");
        driver.findElement(By.cssSelector("input[name='return_reason_id'][value='3']")).click();
    }

    public void waitForSuccess() {
        wait.until(ExpectedConditions.urlContains("route=account/return/success"));
    }

    public String successText() {
        return driver.findElement(By.id("content")).getText();
    }

    private void type(String id, String value) {
        WebElement input = driver.findElement(By.id(id));
        input.clear();
        input.sendKeys(value);
    }
}
