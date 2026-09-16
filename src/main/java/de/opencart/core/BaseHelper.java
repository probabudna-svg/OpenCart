package de.opencart.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

public class BaseHelper {





    public abstract class BasePage  {
        protected WebDriver driver;
        public static JavascriptExecutor js;
        //public static Actions actions;
        //String browser;

        public BasePage(WebDriver driver) {
            this.driver = driver;
            js = (JavascriptExecutor) driver;
            //PageFactory.initElements(driver, this);
            //actions = new Actions(driver);
        }


        public void click (WebElement element){
            element.click();
        }

        public void type(WebElement element,String text){
            if (text != null){
                click(element);
                element.clear();
                element.sendKeys(text);
            }
        }

        public void scrollWithJS(WebElement element){
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        }

        public void clickWithJS(WebElement element){
            scrollWithJS(element);
            click(element);
        }

        public void typeWithJS(WebElement element, String text){
            scrollWithJS(element);
            type(element,text);
        }

        public WebDriverWait getWait(int time) {
            return new WebDriverWait(driver, Duration.ofSeconds(time));
        }

    }

}


