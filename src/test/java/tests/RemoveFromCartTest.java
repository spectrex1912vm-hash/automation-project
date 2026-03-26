package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.StorePage;
import pages.ProductPage;

public class RemoveFromCartTest extends BaseTest {

    @Test
    public void removeProductFromCartTest() {
        HomePage homePage = new HomePage(driver);
        homePage.open();

        StorePage storePage = new StorePage(driver);
        storePage.open();

        ProductPage productPage = storePage.openFirstProduct();

        // Добавляем товар
        Allure.step("Добавляем товар в корзину", () -> {
            productPage.addToCart();
            productPage.waitForNotificationToDisappear();
        });

        homePage.goToCart();
        CartPage cartPage = new CartPage(driver);

        Allure.step("Проверяем, что товар есть в корзине", () -> {
            Assert.assertTrue(cartPage.isProductDisplayed(0), "Товар не добавился");
        });

        Allure.step("Удаляем товар через установку количества = 0", () -> {
            cartPage.setQuantityForFirstProduct(0);
            cartPage.pressEnterOnQuantity(0);
            cartPage.waitForCartToUpdate();
        });

        Allure.step("Проверяем, что корзина пуста", () -> {
            Assert.assertEquals(cartPage.getCartQuantity(), 0, "Товар не удалился");
        });
    }
}