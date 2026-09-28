package opencart.pages;

import opencart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By emailInput = By.id("input-email");
    private final By passwordInput = By.id("input-password");
    private final By loginButton = By.cssSelector("input[value='Login']");
    private final By registerButton =
            By.cssSelector("a[href*='account/register']");
    private final By errorMessage = By.cssSelector(".alert-danger");

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

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isErrorMessageDisplayed() {
        return getWait(10).until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).isDisplayed();
    }
}