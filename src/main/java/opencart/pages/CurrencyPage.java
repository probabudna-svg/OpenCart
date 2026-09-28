package opencart.pages;

import opencart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CurrencyPage extends BasePage {

    private final By currencyDropdown =
            By.cssSelector("#form-currency button.dropdown-toggle");

    private final By productPrice =
            By.cssSelector("#content .product-thumb .caption p.price");

    public CurrencyPage(WebDriver driver) {
        super(driver);
    }

    public void selectCurrency(String currencyCode) {
        if (!currencyCode.equals("USD")
                && !currencyCode.equals("EUR")
                && !currencyCode.equals("GBP")) {
            throw new IllegalArgumentException(
                    "Unsupported currency: " + currencyCode);
        }

        getWait(10).until(
                ExpectedConditions.elementToBeClickable(currencyDropdown)
        ).click();

        By currencyOption = By.cssSelector(
                "#form-currency button[name='" + currencyCode + "']"
        );

        getWait(10).until(
                ExpectedConditions.elementToBeClickable(currencyOption)
        ).click();
    }

    public String getProductPriceText() {
        return getWait(10).until(
                ExpectedConditions.visibilityOfElementLocated(productPrice)
        ).getText();
    }
}