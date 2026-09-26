package de.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By emailInput = By.id("input-email");
    private By passwordInput = By.id("input-password");

    private By loginButton =
            By.cssSelector("input[value='Login']");

    private By registerButton =
            By.cssSelector("a[href*='account/register']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterEmail(String email) {
        type(driver.findElement(emailInput), email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(driver.findElement(passwordInput), password);
        return this;
    }

    public void clickLogin() {
        click(driver.findElement(loginButton));
    }

    public RegisterPage clickRegister() {
        click(driver.findElement(registerButton));
        return new RegisterPage(driver);
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }
}