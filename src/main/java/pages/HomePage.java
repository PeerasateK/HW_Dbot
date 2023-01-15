package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //  Locator
    By loginBtn = By.cssSelector(".btn-outlined-purple");

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }
}
