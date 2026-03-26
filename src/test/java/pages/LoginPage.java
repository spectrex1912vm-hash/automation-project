package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    By emailField = By.id("Email");
    By passwordField = By.id("Password");
    By loginButton = By.cssSelector("input.login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://demowebshop.tricentis.com/login");
    }

    public void login(String email, String password) {
        open(); // 👈 ВАЖНО

        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}