package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.StorePage;

public class AddToCartTest extends BaseTest {

    @Test
    public void addFirstProductToCartTest() {

        // 1️⃣ Открываем страницу магазина
        StorePage storePage = new StorePage(driver);
        storePage.open();

        // 2️⃣ Открываем товар и добавляем в корзину
        ProductPage productPage = storePage.openFirstProduct(); // 👈 исправили
        productPage.addToCart(); // 👈 добавили

        // 3️⃣ Проверяем popup
        Assert.assertTrue(
                productPage.isProductAddedPopupVisible(),
                "Popup 'Product added to cart' не появился"
        );

        // 4️⃣ Ждём исчезновение popup
        productPage.waitForNotificationToDisappear();

        // 5️⃣ Переходим в корзину
        HomePage homePage = new HomePage(driver);
        homePage.goToCart();

        // 6️⃣ Проверяем корзину
        CartPage cartPage = new CartPage(driver);

        int cartQuantity = cartPage.getCartQuantity();

        Assert.assertTrue(
                cartQuantity > 0,
                "Количество товаров в корзине не увеличилось"
        );
    }
}