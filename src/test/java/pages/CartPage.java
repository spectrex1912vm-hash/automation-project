package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String getCartHeader() {
        return driver.findElement(By.cssSelector("span.cart-label")).getText();
    }

    private List<WebElement> getQuantityInputs() {
        return driver.findElements(By.cssSelector("input.qty-input"));
    }

    public int getQuantityOfProduct(int index) {
        try {
            String value = getQuantityInputs().get(index).getAttribute("value");
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }

    public void setQuantityOfProduct(int index, int quantity) {
        waitForQuantityInputs(); // ждем появления
        WebElement input = getQuantityInputs().get(index);
        input.clear();
        input.sendKeys(String.valueOf(quantity));
    }

    public boolean isProductDisplayed(int index) {
        return getQuantityInputs().size() > index;
    }

    public int getCartQuantity() {
        int total = 0;
        for (WebElement input : getQuantityInputs()) {
            try {
                total += Integer.parseInt(input.getAttribute("value"));
            } catch (Exception ignored) {}
        }
        return total;
    }

    public void setQuantityForFirstProduct(int quantity) {
        setQuantityOfProduct(0, quantity);
    }

    public void updateCart() {
        try {
            driver.findElement(By.cssSelector("button[name='updatecart']")).click();
        } catch (Exception e) {}
    }

    public void waitForCartToUpdate() {
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
    }

    public void pressEnterOnQuantity(int index) {
        waitForQuantityInputs(); // ждем появления
        getQuantityInputs().get(index).sendKeys(Keys.ENTER);
    }

    // 🔥 Новый метод: ждём хотя бы один input qty-input
    public void waitForQuantityInputs() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input.qty-input")));
    }
}