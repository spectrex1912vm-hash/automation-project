package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private By cartIcon = By.cssSelector(".ico-cart");
    private By logoutButton = By.cssSelector(".ico-logout");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://demowebshop.tricentis.com/"); // ⚡ главная страница
    }

    public void goToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cartButton.click();
    }

    public boolean isLogoutVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
            return logout.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}