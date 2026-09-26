package de.opencart.tests;

import de.opencart.core.TestBase;
import de.opencart.pages.ProductPage;
import de.opencart.pages.ShoppingCartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingCartOperationsTest extends TestBase {

    private ShoppingCartPage prepareCart() {
        ShoppingCartPage cart = new ShoppingCartPage(driver);
        cart.openCart().clearCart();

        ProductPage productPage = new ProductPage(driver);
        productPage.openMacBook().setQuantity(1).addToCart();

        cart.openCart();
        return cart;
    }

    @Test
    public void addProductToCartTest() {
        ShoppingCartPage cart = prepareCart();
        Assert.assertTrue(cart.isProductInCart("MacBook"), "MacBook was not added to cart");
    }

    @Test
    public void updateQuantityTest() {
        ShoppingCartPage cart = prepareCart();
        cart.updateFirstItemQuantity(2);
        Assert.assertEquals(cart.getFirstItemQuantity(), "2", "Product quantity was not updated");
    }

    @Test
    public void removeProductTest() {
        ShoppingCartPage cart = prepareCart();
        Assert.assertTrue(cart.isProductInCart("MacBook"), "MacBook is not present before removal");
        cart.removeFirstItem();
        Assert.assertFalse(cart.isProductInCart("MacBook"), "MacBook was not removed from cart");
    }
}