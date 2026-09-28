package opencart.tests;

import opencart.core.TestBase;
import opencart.pages.ContactUsPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactUsTests extends TestBase {

    @Test
    public void contactUsPageOpens() {
        ContactUsPage contactUsPage = new ContactUsPage(driver);

        contactUsPage.open();

        Assert.assertEquals(contactUsPage.getHeading(),
                "Contact Us",
                "Die Contact-Us-Seite wurde nicht geöffnet.");
    }
    @Test
    public void submitContactUsForm() {
        ContactUsPage contactUsPage = new ContactUsPage(driver);

        contactUsPage.open();

        contactUsPage.fillContactForm(
                "Test User",
                "test@example.com",
                "This is a test enquiry.");

        contactUsPage.submitForm();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("route=information/contact/success"),
                "Die Kontaktanfrage wurde nicht erfolgreich abgesendet.");

    }

}
