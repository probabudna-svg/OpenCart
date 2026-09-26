package de.opencart.tests;

import de.opencart.core.TestBase;
import de.opencart.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends TestBase {

    @Test
    public void loginPositiveTest() {

        driver.get("https://opencart.abstracta.us/index.php?route=account/login");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "Testergroup2@gmail.com",
                "123456789Abc!"
        );

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        wait.until(
                ExpectedConditions.urlContains("route=account/account")
        );

        String actualTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("#content h2")
                )
        ).getText();

        Assert.assertEquals(
                actualTitle,
                "My Account"
        );
    }
}