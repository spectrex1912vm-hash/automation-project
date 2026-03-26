package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Добавление товара в корзину
    public void addToCart() {
        WebElement addToCartButton = driver.findElement(By.cssSelector("input[value='Add to cart']"));
        addToCartButton.click();
    }

    // Проверка видимости popup "Product added to cart"
    public boolean isProductAddedPopupVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement popup = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bar-notification.success"))
            );
            return popup.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Ждём, пока уведомление исчезнет
    public void waitForNotificationToDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".bar-notification.success")));
    }
}
