package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;

public class CartTest extends BaseTest {

    @Test
    public void openCartTest() {
        HomePage homePage = new HomePage(driver);

        // ⚡ открываем главную страницу
        homePage.open();

        // ⚡ теперь элемент корзины должен появиться
        homePage.goToCart();

        CartPage cartPage = new CartPage(driver);
        String cartHeader = cartPage.getCartHeader();
        Assert.assertEquals(cartHeader, "Shopping cart");

        int itemCount = cartPage.getCartQuantity();
        Assert.assertTrue(itemCount >= 0, "Счетчик товаров меньше 0");
    }
}
