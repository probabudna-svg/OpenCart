package de.opencart.tests;

import de.opencart.core.TestBase;
import de.opencart.pages.RegisterPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @Test
    public void registrationPositiveTest() {

        driver.get("https://opencart.abstracta.us/index.php?route=account/register");

        RegisterPage registerPage = new RegisterPage(driver);

        String email = "Testergroup2@gmail.com";

        registerPage.registerUser(
                "Testergroup2",
                "Testergroup2a",
                email,
                "+4912345678",
                "123456789Abc!"
        );

        String actualMessage = driver
                .findElement(By.cssSelector("#content p"))
                .getText();

        Assert.assertEquals(
                actualMessage,
                "Congratulations! Your new account has been successfully created!"
        );

        driver.findElement(By.cssSelector(".buttons .btn-primary")).click();

        String accountTitle = driver
                .findElement(By.cssSelector("#content h2"))
                .getText();

        Assert.assertEquals(accountTitle, "My Account");
    }
}