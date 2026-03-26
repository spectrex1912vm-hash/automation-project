package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Открыть страницу
    public void openUrl(String url) {
        driver.get(url);
    }

    // Клик по элементу
    public void click(By locator) {
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
        element.click();
    }

    // Ввод текста
    public void setInputValue(By locator, String value) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
        element.clear();
        element.sendKeys(value);
    }

    // Перегрузка метода для int
    public void setInputValue(By locator, int value) {
        setInputValue(locator, String.valueOf(value));
    }

    // Получить текст элемента
    public String getText(By locator) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
        return element.getText();
    }

    // Получить атрибут элемента
    public String getAttribute(By locator, String attribute) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
        return element.getAttribute(attribute);
    }

    // Ожидание появления элемента
    public void waitForVisibility(By locator) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    // Ожидание исчезновения элемента
    public void waitForInvisibility(By locator) {
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator)
        );
    }

    // Проверка что элемент отображается
    public boolean isVisible(By locator) {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator)
            );
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Проверка что текст содержит значение
    public boolean textContains(By locator, String text) {
        return getText(locator).contains(text);
    }
}