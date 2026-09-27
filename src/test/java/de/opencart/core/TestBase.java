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

   // protected static ApplicationManager app =
         //   new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));
/*
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

 */

    protected WebDriver driver;
@BeforeMethod
public void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.get("https://opencart.abstracta.us");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
}

    @AfterMethod(enabled = false)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
