package de.opencart.returns;

import de.opencart.core.KarynaTestBase;
import de.opencart.pages.ReturnPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ReturnRequestTests extends KarynaTestBase {

    @Test
    public void returnFormOpens() {
        ReturnPage returns = new ReturnPage(driver);
        returns.open();

        assertTrue(returns.formIsShown());
    }

    @Test
    public void emptyReturnFormShowsRequiredFieldErrors() {
        ReturnPage returns = new ReturnPage(driver);
        returns.open();
        returns.submit();
        returns.waitForErrors();

        String errors = returns.formText();
        assertTrue(errors.contains("First Name must be between"));
        assertTrue(errors.contains("Order ID required!"));
        assertTrue(errors.contains("Product Name must be greater"));
        assertTrue(errors.contains("You must select a return product reason!"));
    }

    @Test
    public void validReturnRequestShowsSuccessMessage() {
        ReturnPage returns = new ReturnPage(driver);
        returns.open();
        returns.fillValidRequest("QA-TEST-" + System.currentTimeMillis());
        returns.submit();
        returns.waitForSuccess();

        assertTrue(returns.successText().contains("Thank you for submitting your return request"));
    }
}
