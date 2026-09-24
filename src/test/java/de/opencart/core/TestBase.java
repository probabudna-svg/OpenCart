package de.opencart.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class TestBase {

    protected static ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));

    // @BeforeMethod
    @BeforeSuite
    public void setUp() {
        app.start();
    }

    // @AfterMethod
    @AfterSuite(enabled = true)
    public void tearDown() {
        app.stop();
    }

}
