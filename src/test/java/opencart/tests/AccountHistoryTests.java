package opencart.tests;

import opencart.core.TestBase;
import opencart.pages.AccountPage;
import opencart.pages.DownloadsPage;
import opencart.pages.LoginPage;
import opencart.pages.OrderHistoryPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AccountHistoryTests extends TestBase {

    @Test
    public void customerAccountOrderHistoryAndDownloadsTest() {
        driver.get("https://opencart.abstracta.us/index.php?route=account/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Testergroup2@gmail.com", "123456789Abc!");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("route=account/account"));

        AccountPage accountPage = new AccountPage(driver);
        Assert.assertEquals(accountPage.getPageHeading(), "My Account", "Account page heading should be My Account");

        OrderHistoryPage orderHistoryPage = accountPage.clickOrderHistory();
        wait.until(ExpectedConditions.urlContains("route=account/order"));
        Assert.assertTrue(orderHistoryPage.isOrdersTablePresent(), "Orders history table should be present");

        orderHistoryPage.clickViewOrder();
        wait.until(ExpectedConditions.urlContains("route=account/order/info"));
        Assert.assertTrue(orderHistoryPage.isOrderDetailsDisplayed(), "Order Details section should be displayed");

        DownloadsPage downloadsPage = orderHistoryPage.clickDownloadsInSidebar();
        wait.until(ExpectedConditions.urlContains("route=account/download"));
        Assert.assertTrue(
                downloadsPage.getPageHeading().contains("Account") || driver.getTitle().contains("Downloads"),
                "User should be on Downloads page"
        );
        Assert.assertTrue(
                downloadsPage.isDownloadsTablePresent() || !downloadsPage.getContentMessage().isEmpty(),
                "Downloads table or empty notice message should be present"
        );

        AccountPage returnedAccountPage = downloadsPage.clickContinue();
        wait.until(ExpectedConditions.urlContains("route=account/account"));
        Assert.assertEquals(returnedAccountPage.getPageHeading(), "My Account", "User should return to My Account page");
    }
}
