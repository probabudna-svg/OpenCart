package opencart.tests;

import opencart.core.TestBase;
import opencart.pages.CategoryPage;
import opencart.pages.LoginPage;
import opencart.pages.WishListPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class WishListTests extends TestBase {

    @Test
    public void wishListManagementForGuestAndAuthUserTest() {
        CategoryPage categoryPage = new CategoryPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        categoryPage.open("https://opencart.abstracta.us/index.php?route=product/category&path=20");
        categoryPage.addProductToWishList(0);

        String guestAlert = categoryPage.getAlertText();
        Assert.assertTrue(
                guestAlert.contains("You must") && (guestAlert.contains("login") || guestAlert.contains("create an account")),
                "Guest should see login/register requirement prompt"
        );
        categoryPage.clickWishListHeader();
        wait.until(ExpectedConditions.urlContains("route=account/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("route=account/login"), "Guest should be redirected to login page");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Testergroup2@gmail.com", "123456789Abc!");
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("route=account/wishlist"),
                ExpectedConditions.urlContains("route=account/account")
        ));

        categoryPage.open("https://opencart.abstracta.us/index.php?route=product/category&path=20");
        String productName = categoryPage.getProductTitle(0);
        categoryPage.addProductToWishList(0);

        String authAlert = categoryPage.getAlertText();
        Assert.assertTrue(
                authAlert.contains("Success: You have added") && authAlert.contains("wish list"),
                "Authenticated user should see success alert when adding to wish list"
        );

        WishListPage wishListPage = categoryPage.clickWishListHeader();
        wait.until(ExpectedConditions.urlContains("route=account/wishlist"));
        Assert.assertTrue(
                wishListPage.getPageHeading().contains("Wish List") || driver.getTitle().contains("Wish List"),
                "User should be on the Wish List page"
        );
        Assert.assertTrue(wishListPage.isWishListTableDisplayed(), "Wish List table should be displayed");
        Assert.assertTrue(
                wishListPage.getWishListProductNames().contains(productName),
                "Added product should be present in the Wish List table"
        );

          wishListPage.removeFirstProduct();
    }
}
