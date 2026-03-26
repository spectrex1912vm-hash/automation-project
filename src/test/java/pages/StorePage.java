package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StorePage {

    WebDriver driver;

    By firstProduct = By.cssSelector(".product-item .product-title a");

    public StorePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://demowebshop.tricentis.com/books");
    }

    public ProductPage openFirstProduct() {
        if (driver.findElements(firstProduct).size() > 0) {
            driver.findElements(firstProduct).get(0).click();
            return new ProductPage(driver);
        }
        return null;
    }
}