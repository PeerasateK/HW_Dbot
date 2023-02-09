package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DashBoard;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://proton.me/mail");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //  test Param
        String email1, email2, subject, body;
        email1  = "testSelenium1";                  //Username same as pwd
        email2  = "testSelenium2";
        subject = "Congratulations";
        body    = "Almost! You can do it.";

        //  Create Object Pages
        HomePage home = new HomePage(driver);
        LoginPage login = new LoginPage(driver);
        DashBoard dashboard = new DashBoard(driver);

        //  Click Sign in at the home page
        home.clickLogin();

        //  Enter Username, Password and Sign in at the login page
        signIn(login, email1);

        //  Send the Email
        Thread.sleep(12000);
        dashboard.clickNewMessage();
        dashboard.enterEmailAddress(email2+"@proton.me");
        dashboard.enterSubject(subject);
        dashboard.enterBodyEmail(body);
        dashboard.clickSendEmail();
        Thread.sleep(40000);    //Email can be delay some time, so I put it to sleep for 25 sec

        //  Sign out
        dashboard.logout();

        //  Sign in another Email Address
        signIn(login, email2);

        //  Verify that the email2 was successfully received  "Test Case"
        boolean status = dashboard.isReceive(email1, subject);
        System.out.println(status);

        if (status) {
            //  Clean it up!
            dashboard.clean();
        }

        //  Sign out
        dashboard.logout();

    }

    public static void signIn(LoginPage login,String email) {
        login.enterUsername(email);
        login.enterPassword(email);
        login.clickLogin();
    }

}