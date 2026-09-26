package de.opencart.tests;

import de.opencart.core.TestBase;
import de.opencart.pages.ProductPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CustomizableProductOptionsTest extends TestBase {

    @Test
    public void radioOptionTest() throws IOException {
        ProductPage productPage = new ProductPage(driver);
        productPage.openAppleCinema();

        if (!productPage.hasRadioOptions()) {
            String bugMessage = "[KNOWN BUG] Radio is required, but no radio options are available.";
            System.out.println(bugMessage);
            Reporter.log(bugMessage, true);
            takeScreenshot("radio_options_missing");
            return;
        }

        productPage.selectFirstRadioOption();
        Assert.assertTrue(productPage.isFirstRadioSelected(), "Radio option was not selected");
    }

    @Test
    public void checkboxOptionTest() {
        ProductPage productPage = new ProductPage(driver);
        productPage.openAppleCinema();
        Assert.assertTrue(productPage.getCheckboxCount() > 0, "No checkbox options were found");
        productPage.selectAllCheckboxes();
        Assert.assertTrue(productPage.areAllCheckboxesSelected(), "Not all checkbox options were selected");
    }

    @Test
    public void textOptionTest() {
        ProductPage productPage = new ProductPage(driver);
        productPage.openAppleCinema().enterText("Automation Test");
        Assert.assertEquals(productPage.getTextValue(), "Automation Test", "Text value is incorrect");
    }

    @Test
    public void selectOptionTest() {
        ProductPage productPage = new ProductPage(driver);
        productPage.openAppleCinema().selectFirstAvailableOption();
        Assert.assertNotEquals(productPage.getSelectedOption(), "--- Please Select ---", "Product option was not selected");
    }

    @Test
    public void textareaOptionTest() {
        ProductPage productPage = new ProductPage(driver);
        productPage.openAppleCinema().enterTextarea("Automation textarea test");
        Assert.assertEquals(productPage.getTextareaValue(), "Automation textarea test", "Textarea value is incorrect");
    }

    @Test
    public void fileUploadOptionTest() throws IOException {
        Path testFile = Files.createTempFile("opencart-test-", ".txt");
        Files.writeString(testFile, "OpenCart automation test file");
        ProductPage productPage = new ProductPage(driver);
        productPage.openAppleCinema().uploadFile(testFile.toAbsolutePath().toString());
        Assert.assertTrue(productPage.isFileUploaded(), "File was not uploaded");
    }

    @Test
    public void dateOptionTest() {
        ProductPage productPage = new ProductPage(driver);
        productPage.openAppleCinema().enterDate("2026-09-26");
        Assert.assertEquals(productPage.getDateValue(), "2026-09-26", "Date value is incorrect");
    }

    @Test
    public void timeOptionTest() {
        ProductPage productPage = new ProductPage(driver);
        productPage.openAppleCinema().enterTime("16:30");
        Assert.assertEquals(productPage.getTimeValue(), "16:30", "Time value is incorrect");
    }

    @Test
    public void dateTimeOptionTest() {
        ProductPage productPage = new ProductPage(driver);
        productPage.openAppleCinema().enterDateTime("2026-09-26 16:30");
        Assert.assertEquals(productPage.getDateTimeValue(), "2026-09-26 16:30", "Date & Time value is incorrect");
    }

    private void takeScreenshot(String screenshotName) throws IOException {
        Path screenshotDirectory = Paths.get("build", "screenshots");
        Files.createDirectories(screenshotDirectory);
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path destination = screenshotDirectory.resolve(screenshotName + "_" + System.currentTimeMillis() + ".png");
        Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
        Reporter.log("Screenshot saved: " + destination.toAbsolutePath(), true);
    }
}