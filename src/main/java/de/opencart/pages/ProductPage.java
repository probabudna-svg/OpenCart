package de.opencart.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#input-option218 input[type='radio']")
    private List<WebElement> radioButtons;

    @FindBy(css = "#input-option223 input[type='checkbox']")
    private List<WebElement> checkboxes;

    @FindBy(id = "input-option208")
    private WebElement textInput;

    @FindBy(id = "input-option217")
    private WebElement selectInput;

    @FindBy(id = "input-option209")
    private WebElement textareaInput;

    @FindBy(id = "button-upload222")
    private WebElement uploadFileButton;

    @FindBy(id = "input-option222")
    private WebElement uploadedFileValue;

    @FindBy(id = "input-option219")
    private WebElement dateInput;

    @FindBy(id = "input-option221")
    private WebElement timeInput;

    @FindBy(id = "input-option220")
    private WebElement dateTimeInput;

    @FindBy(id = "input-quantity")
    private WebElement quantityInput;

    @FindBy(id = "button-cart")
    private WebElement addToCartButton;

    @FindBy(css = "a[href='#tab-review']")
    private WebElement reviewsTab;

    @FindBy(id = "input-name")
    private WebElement reviewNameInput;

    @FindBy(id = "input-review")
    private WebElement reviewTextInput;

    @FindBy(css = "input[name='rating']")
    private List<WebElement> ratingButtons;

    @FindBy(id = "button-review")
    private WebElement reviewContinueButton;

    public ProductPage openAppleCinema() {
        driver.get("https://opencart.abstracta.us/index.php?route=product/product&product_id=42");
        return this;
    }

    public ProductPage openMacBook() {
        driver.get("https://opencart.abstracta.us/index.php?route=product/product&product_id=43");
        return this;
    }

    public boolean hasRadioOptions() {
        return !radioButtons.isEmpty();
    }

    public int getRadioOptionsCount() {
        return radioButtons.size();
    }

    public ProductPage selectFirstRadioOption() {
        if (!radioButtons.isEmpty()) radioButtons.get(0).click();
        return this;
    }

    public boolean isFirstRadioSelected() {
        return !radioButtons.isEmpty() && radioButtons.get(0).isSelected();
    }

    public int getCheckboxCount() {
        return checkboxes.size();
    }

    public ProductPage selectAllCheckboxes() {
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) checkbox.click();
        }
        return this;
    }

    public boolean areAllCheckboxesSelected() {
        if (checkboxes.isEmpty()) return false;
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) return false;
        }
        return true;
    }

    public ProductPage enterText(String text) {
        textInput.clear();
        textInput.sendKeys(text);
        return this;
    }

    public String getTextValue() {
        return textInput.getAttribute("value");
    }

    public ProductPage selectFirstAvailableOption() {
        new Select(selectInput).selectByIndex(1);
        return this;
    }

    public ProductPage selectOptionByValue(String value) {
        new Select(selectInput).selectByValue(value);
        return this;
    }

    public String getSelectedOption() {
        return new Select(selectInput).getFirstSelectedOption().getText().trim();
    }

    public ProductPage enterTextarea(String text) {
        textareaInput.clear();
        textareaInput.sendKeys(text);
        return this;
    }

    public String getTextareaValue() {
        return textareaInput.getAttribute("value");
    }

    public ProductPage uploadFile(String absoluteFilePath) {
        uploadFileButton.click();
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file'][name='file']")));
        fileInput.sendKeys(absoluteFilePath);
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception ignored) {
        }
        return this;
    }

    public boolean isFileUploaded() {
        String value = uploadedFileValue.getAttribute("value");
        return value != null && !value.isBlank();
    }

    public ProductPage enterDate(String date) {
        dateInput.clear();
        dateInput.sendKeys(date);
        return this;
    }

    public String getDateValue() {
        return dateInput.getAttribute("value");
    }

    public ProductPage enterTime(String time) {
        timeInput.clear();
        timeInput.sendKeys(time);
        return this;
    }

    public String getTimeValue() {
        return timeInput.getAttribute("value");
    }

    public ProductPage enterDateTime(String dateTime) {
        dateTimeInput.clear();
        dateTimeInput.sendKeys(dateTime);
        return this;
    }

    public String getDateTimeValue() {
        return dateTimeInput.getAttribute("value");
    }

    public ProductPage setQuantity(int quantity) {
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
        return this;
    }

    public String getQuantity() {
        return quantityInput.getAttribute("value");
    }

    public ProductPage addToCart() {
        addToCartButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#product-product .alert-success")));
        return this;
    }

    public String getAddToCartSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#product-product .alert-success"))).getText().trim();
    }

    public ProductPage openReviewsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(reviewsTab)).click();
        return this;
    }

    public ProductPage enterReviewName(String name) {
        reviewNameInput.clear();
        reviewNameInput.sendKeys(name);
        return this;
    }

    public ProductPage enterReviewText(String text) {
        reviewTextInput.clear();
        reviewTextInput.sendKeys(text);
        return this;
    }

    public ProductPage selectRating(int rating) {
        if (rating < 1 || rating > 5) throw new IllegalArgumentException("Rating must be between 1 and 5");
        for (WebElement ratingButton : ratingButtons) {
            if (String.valueOf(rating).equals(ratingButton.getAttribute("value"))) {
                ratingButton.click();
                break;
            }
        }
        return this;
    }

    public boolean isRatingSelected(int rating) {
        for (WebElement ratingButton : ratingButtons) {
            if (String.valueOf(rating).equals(ratingButton.getAttribute("value"))) return ratingButton.isSelected();
        }
        return false;
    }

    public ProductPage submitReview() {
        reviewContinueButton.click();
        return this;
    }

    public String getReviewSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#form-review .alert-success"))).getText().trim();
    }

    public String getReviewErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#form-review .alert-danger"))).getText().trim();
    }
}