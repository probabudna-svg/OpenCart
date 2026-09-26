package de.opencart.tests;

import de.opencart.core.TestBase;
import de.opencart.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class UserAuthenticationTest extends TestBase {

    @Test
    public void loginWithInvalidCredentials() {

        driver.get(
                "https://opencart.abstracta.us/index.php?route=account/login"
        );

        driver.findElement(By.id("input-email"))
                .sendKeys("invalid@example.com");

        driver.findElement(By.id("input-password"))
                .sendKeys("WrongPassword123");

        driver.findElement(By.cssSelector("input[type='submit']"))
                .click();

        boolean warningDisplayed = driver.findElement(
                By.cssSelector(".alert-danger")).isDisplayed();

        Assert.assertTrue(warningDisplayed,
                "Warning message was not displayed");
    }

    @Test
    public void loginWithValidCredentials() {


        driver.get("https://opencart.abstracta.us/index.php?route=account/login");


        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("Testergroup2@gmail.com",
                "123456789Abc!");

        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.urlContains("route=account/account"));

        String actualHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("#content h2"))).getText();

        Assert.assertEquals(actualHeading,
                "My Account",
                "The My Account page was not displayed");
    }
}
