package de.opencart.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            element.click();
            element.clear();
            element.sendKeys(text);
        }
    }

    public void scrollWithJS(WebElement element) {
        js.executeScript(
                "arguments[0].scrollIntoView(true);",
                element
        );
    }

    public void clickWithJS(WebElement element) {
        scrollWithJS(element);
        click(element);
    }

    public void typeWithJS(WebElement element, String text) {
        scrollWithJS(element);
        type(element, text);
    }

    public WebDriverWait getWait(int time) {
        return new WebDriverWait(
                driver,
                Duration.ofSeconds(time)
        );
    }
}