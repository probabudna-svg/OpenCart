package opencart.pages;

import opencart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private final By firstNameInput = By.id("input-firstname");
    private final By lastNameInput = By.id("input-lastname");
    private final By emailInput = By.id("input-email");
    private final By telephoneInput = By.id("input-telephone");
    private final By passwordInput = By.id("input-password");
    private final By confirmPasswordInput = By.id("input-confirm");
    private final By privacyPolicyCheckbox = By.name("agree");
    private final By continueButton =
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

    public RegisterPage enterUserData(
            String firstname,
            String lastname,
            String email,
            String telephone,
            String password,
            String confirm
    ) {
        typeWithJS(driver.findElement(
                By.cssSelector("#input-firstname")), firstname);

        typeWithJS(driver.findElement(
                By.cssSelector("#input-lastname")), lastname);

        typeWithJS(driver.findElement(
                By.cssSelector("#input-email")), email);

        typeWithJS(driver.findElement(
                By.cssSelector("#input-telephone")), telephone);

        typeWithJS(driver.findElement(
                By.cssSelector("#input-password")), password);

        typeWithJS(driver.findElement(
                By.cssSelector("#input-confirm")), confirm);

        return this;
    }

    public RegisterPage checkPrivacyPolicy() {
        clickWithJS(driver.findElement(
                By.cssSelector("input[value='1'][name='agree']")));

        return this;
    }

    public RegisterPage clickOnContinue() {
        clickWithJS(driver.findElement(
                By.cssSelector("input[value='Continue']")));

        return this;
    }

    public boolean isLogoutButtonPresent() {
        return isElementPresent(
                By.cssSelector("a[href*=\"route=account/logout\"]"));
    }

    public String newEmaile() {
        int i =
                (int) ((System.currentTimeMillis() / 1000) % 3600);

        String email =
                "Sw" + i + "@gmail.com";

        return email;
    }

    public boolean isErrorsPresent() {
        return isElementPresent(
                By.cssSelector(".alert.alert-danger.alert-dismissible"));
    }

    public boolean isTextDanger() {
        return isElementPresent(
                By.cssSelector(".text-danger"));
    }
}