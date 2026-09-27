package de.opencart.pages;

import de.opencart.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public RegisterPage enterUserData
            (String firstname, String lastname, String email, String telephone, String password, String confirm) {
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
        return isElementPresent(By.cssSelector("a[href*=\"route=account/logout\"]"));
        }

    public String newEmaile() {
        int i = (int) ((System.currentTimeMillis()/1000)%3600);
        String email = "Sw" + i + "@gmail.com";
        return email;
    }
    public boolean isErrorsPresent() {
        return isElementPresent(By.cssSelector(".alert.alert-danger.alert-dismissible"));
    }

    public boolean isTextDanger() {
        return isElementPresent(By.cssSelector(".text-danger"));
    }


}

