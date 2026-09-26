package de.opencart.pages;

import de.opencart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContactUsPage extends BasePage {
    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    private final By pageHeading = By.cssSelector("#content h1");
    private final By nameField = By.id("input-name");
    private final By emailField = By.id("input-email");
    private final By enquiryField = By.id("input-enquiry");
    private final By submitButton = By.cssSelector("form input[type='submit'][value='Submit']");



    public void open() {
        driver.get("https://opencart.abstracta.us/index.php?route=information/contact");
    }

    public String getHeading() {
        return getWait(10)
                .until(ExpectedConditions.visibilityOfElementLocated(pageHeading))
                .getText();
    }
    public void fillContactForm(String name, String email, String message) {
        getWait(10).until(
                ExpectedConditions.visibilityOfElementLocated(nameField)
        );

        type(driver.findElement(nameField), name);
        type(driver.findElement(emailField), email);
        type(driver.findElement(enquiryField), message);
    }

    public void submitForm() {
        getWait(10).until(
                ExpectedConditions.elementToBeClickable(submitButton)
        ).click();
    }
}
