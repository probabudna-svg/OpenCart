package opencart.core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class KarynaTestBase {

    protected WebDriver driver;
    protected ApplicationManager app;

    @BeforeMethod
    public void setUp() {
        app = new ApplicationManager(System.getProperty("browser", "chrome"));
        driver = app.start();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (app != null) app.stop();
    }
}