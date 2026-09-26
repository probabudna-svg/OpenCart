package de.opencart.core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    protected WebDriver driver;
    protected ApplicationManager app;

    @BeforeMethod
    public void setUp() {
        app = new ApplicationManager("chrome");
        driver = app.start();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }
}