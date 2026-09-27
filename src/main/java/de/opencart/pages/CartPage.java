package de.opencart.pages;

import de.opencart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }


    public CartPage clickShipping() {
        clickWithJS(driver.findElement(
                By.cssSelector(".accordion-toggle[href='#collapse-shipping']")));
        return this;
    }

    /*public CartPage selectCountry(String country) {
        Select select = new Select(
                driver.findElement(By.cssSelector("#input-country")));
        select.selectByVisibleText(country);
        return this;
    }

     */

    public CartPage selectCountry(String country) {
        By locator = By.cssSelector("#input-country");
        getWait(10).until(
                ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(country);
        return this;
    }

    public CartPage selectRegion(String region) {
        Select select = new Select(
                driver.findElement(By.cssSelector("#input-zone")));
        select.selectByVisibleText(region);
        return this;
    }

    public CartPage inputPostcode(String postcode) {
        typeWithJS(driver.findElement(
                By.cssSelector("#input-postcode")), postcode);
        return this;
    }

    public CartPage clickQuotes() {
        clickWithJS(driver.findElement(
                By.cssSelector("#button-quote")));
        return this;
    }


    public CartPage cliskFlatRate() {
        clickWithJS(driver.findElement(
                By.cssSelector("div[class='radio'] label")));
        return this;
    }


    public CartPage clickButtonShipping() {
        clickWithJS(driver.findElement(
                By.cssSelector("#button-shipping")));
        return this;
    }

    public boolean isShippigRatePresent() {
        return isElementPresent(By.xpath("//strong[normalize-space()='Flat Shipping Rate:']"));
    }

    public CartPage clickViewCart() {
        clickWithJS(driver.findElement(
                By.cssSelector("a:nth-child(1) strong:nth-child(1)")));
        return this;
    }
}
