package de.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private By firstNameInput = By.id("input-firstname");
    private By lastNameInput = By.id("input-lastname");
    private By emailInput = By.id("input-email");
    private By telephoneInput = By.id("input-telephone");
    private By passwordInput = By.id("input-password");
    private By confirmPasswordInput = By.id("input-confirm");

    private By privacyPolicyCheckbox =
            By.name("agree");

    private By continueButton =
            By.cssSelector("input[value='Continue']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public RegisterPage enterFirstName(String firstName) {
        type(driver.findElement(firstNameInput), firstName);
        return this;
    }

    public RegisterPage enterLastName(String lastName) {
        type(driver.findElement(lastNameInput), lastName);
        return this;
    }

    public RegisterPage enterEmail(String email) {
        type(driver.findElement(emailInput), email);
        return this;
    }

    public RegisterPage enterTelephone(String telephone) {
        type(driver.findElement(telephoneInput), telephone);
        return this;
    }

    public RegisterPage enterPassword(String password) {
        type(driver.findElement(passwordInput), password);
        return this;
    }

    public RegisterPage confirmPassword(String password) {
        type(driver.findElement(confirmPasswordInput), password);
        return this;
    }

    public RegisterPage acceptPrivacyPolicy() {

        if (!driver.findElement(privacyPolicyCheckbox).isSelected()) {
            click(driver.findElement(privacyPolicyCheckbox));
        }

        return this;
    }

    public void clickContinue() {
        click(driver.findElement(continueButton));
    }

    public void registerUser(
            String firstName,
            String lastName,
            String email,
            String telephone,
            String password
    ) {

        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterTelephone(telephone);
        enterPassword(password);
        confirmPassword(password);
        acceptPrivacyPolicy();
        clickContinue();
    }
}