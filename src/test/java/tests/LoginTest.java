package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("testqa19@gmail.com","q12345q");

        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(
                homePage.isLogoutVisible(),
                "Кнопка Logout не отображается"
        );
    }
}
