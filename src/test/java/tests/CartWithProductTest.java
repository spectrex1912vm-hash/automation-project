package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.StorePage;

public class CartWithProductTest extends BaseTest {

    @Test
    public void verifyProductInCart() {

        StorePage storePage = new StorePage(driver);
        storePage.open();

        ProductPage productPage = storePage.openFirstProduct();
        Assert.assertNotNull(productPage, "Не удалось открыть товар");

        productPage.addToCart();

        HomePage homePage = new HomePage(driver);
        homePage.goToCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(
                cartPage.isProductDisplayed(0),
                "Product is not displayed in cart"
        );

        Assert.assertEquals(
                cartPage.getQuantityOfProduct(0),
                1,
                "Product quantity is incorrect"
        );
    }
}