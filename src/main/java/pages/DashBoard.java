package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DashBoard {

    WebDriver driver;

    public DashBoard(WebDriver driver) {
        this.driver = driver;
    }

    //  Locator
    By newMessageBtn = By.xpath("//button[normalize-space()='New message']");
    By emailAddress = By.cssSelector("input[placeholder='Email address']");
    By subject = By.cssSelector("input[placeholder='Subject']");
    By frame = By.xpath("//iframe[@title='Email composer']");
    By bodyEmail = By.cssSelector("div#rooster-editor");
    By sendBtn = By.cssSelector("button[data-testid='composer:send-button']");
    By profileBtn = By.cssSelector("span.myauto");
    By logoutBtn = By.cssSelector("button[data-testid*='logout']");
    By selectAllBtn = By.cssSelector("#idSelectAll");
    By delBtn = By.cssSelector("button[data-testid='toolbar:movetotrash']");
    By moreBtn = By.cssSelector("button[title='More']");
    By trashBtn = By.cssSelector("a[title*='Trash']");
    By delPermanentlyBtn = By.cssSelector("button[data-testid='toolbar:deletepermanently']");
    By confirmDelBtn = By.cssSelector(".button.w100.button-solid-danger");

    public void clickNewMessage() {
        driver.findElement(newMessageBtn).click();
    }

    public void enterEmailAddress(String email) {
        driver.findElement(emailAddress).sendKeys(email);
    }

    public void enterSubject(String sub) {
        driver.findElement(subject).sendKeys(sub);
    }

    public void enterBodyEmail(String body) {
        driver.switchTo().frame(driver.findElement(frame));
        driver.findElement(bodyEmail).click();
        driver.findElement(bodyEmail).clear();
        driver.findElement(bodyEmail).sendKeys(body);
        driver.switchTo().parentFrame();
    }

    public void clickSendEmail() {
        driver.findElement(sendBtn).click();
    }

    public void logout() {
        driver.findElement(profileBtn).click();
        driver.findElement(logoutBtn).click();
    }

    public boolean isReceive(String name, String sub) {
        By sender = By.cssSelector("span[title*='"+name.toLowerCase()+"@proton.me']");
        By subject = By.cssSelector("span[title*='"+sub+"']");
        List<WebElement> correctEmail = driver.findElements(subject);
        return (correctEmail.size() > 0);
    }

    public void clean() throws InterruptedException {
        driver.findElement(selectAllBtn).click();
        driver.findElement(delBtn).click();
        driver.findElement(moreBtn).click();
        driver.findElement(trashBtn).click();
        Thread.sleep(500);
        driver.findElement(selectAllBtn).click();
        driver.findElement(delPermanentlyBtn).click();
        driver.findElement(confirmDelBtn).click();
    }
}
