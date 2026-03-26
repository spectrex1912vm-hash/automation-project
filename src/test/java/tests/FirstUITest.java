package tests;
import base.BaseTest;
import org.testng.annotations.Test;

public class FirstUITest extends BaseTest {

    @Test
    public void openHomePage() {
        System.out.println("Открой сайт: " + driver.getCurrentUrl());
    }
}
