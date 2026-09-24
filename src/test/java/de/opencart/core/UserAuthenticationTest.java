package de.opencart.core;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserAuthenticationTest extends TestBase{

    @Test
    public void loginWithInvalidCredentials() {

        // Open the login page
        driver.get(
                "https://opencart.abstracta.us/index.php?route=account/login"
        );

        // Enter invalid email
        driver.findElement(By.id("input-email"))
                .sendKeys("invalid@example.com");

        // Enter invalid password
        driver.findElement(By.id("input-password"))
                .sendKeys("WrongPassword123");

        // Click the Login button
        driver.findElement(By.cssSelector("input[type='submit']"))
                .click();

        // Verify that the warning message is displayed
        boolean warningDisplayed = driver.findElement(
                By.cssSelector(".alert-danger")
        ).isDisplayed();

        Assert.assertTrue(
                warningDisplayed,
                "Warning message was not displayed"
        );
    }
    @Test
    public void loginWithValidCredentials() {

        // Open the login page
        driver.get(
                "https://opencart.abstracta.us/index.php?route=account/login"
        );

        // Enter valid email
        driver.findElement(By.id("input-email"))
                .sendKeys("YOUR_VALID_EMAIL");

        // Enter valid password
        driver.findElement(By.id("input-password"))
                .sendKeys("YOUR_VALID_PASSWORD");

        // Click the Login button
        driver.findElement(By.cssSelector("input[type='submit']"))
                .click();

        // Print the current URL
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // Print the page title
        System.out.println("Page title: " + driver.getTitle());

        // Print the visible page text
        System.out.println("Page text: " + driver.findElement(
                By.tagName("body")
        ).getText());
    }
}
